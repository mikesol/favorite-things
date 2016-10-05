package uk.ivanc.archimvvm.converter;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import braque.RESTOperation;
import braque.braqued.Deserializer;
import braque.braqued.StringProvisioner;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import uk.ivanc.archimvvm.Utils;
import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;

public class BraqueResponseBodyConverter implements Converter<ResponseBody, Collection<? extends RESTOperation>> {

    static class BraqueProperty {
        final String receivedName;
        final String usedName;
        BraqueProperty(String receivedName, String usedName) {
            this.receivedName = receivedName.toLowerCase();
            this.usedName = usedName.toLowerCase();
        }
    }

    static private final BraqueProperty[] repoProperties = new BraqueProperty[]{
            new BraqueProperty("stargazers_count", StringProvisioner.propStars()),
            new BraqueProperty("name", StringProvisioner.propName()),
            new BraqueProperty("forks_count", StringProvisioner.propForks()),
            new BraqueProperty("watchers_count", StringProvisioner.propWatchers()),
            new BraqueProperty("language", StringProvisioner.propLanguage())
    };
    static private final BraqueProperty[] ownerProperties = new BraqueProperty[] {
            new BraqueProperty("name", StringProvisioner.propOwner()),
            new BraqueProperty("avatar_url", StringProvisioner.propAvatarURL())
    };

    @Override public Collection<? extends RESTOperation> convert(ResponseBody value) throws IOException {
        try {
            Log.d("BraqueConverter", "working");
            JSONObject jsonObject = new JSONObject(value.string());
            JSONArray items = jsonObject.getJSONArray("items");
            Map<String, Object> serialized = new HashMap<>();

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                if (!item.has("id")) {
                    continue;
                }
                String id = Integer.toString(item.getInt("id"));
                serialized.put(Utils.toBraquePath(StringProvisioner.pathBrowseGithubs(), id, "id"), id);
                for (BraqueProperty property : repoProperties) {
                    if (item.has(property.receivedName)) {
                        serialized.put(Utils.toBraquePath(StringProvisioner.pathBrowseGithubs(), id, property.usedName),
                                item.get(property.receivedName));
                    }
                }
                if (item.has("owner")) {
                    JSONObject owner = item.getJSONObject("owner");
                    for (BraqueProperty property : ownerProperties) {
                        if (owner.has(property.receivedName)) {
                            serialized.put(Utils.toBraquePath(StringProvisioner.pathBrowseGithubs(), id, property.usedName),
                                    owner.get(property.receivedName));
                        }
                    }
                }
            }//for (Map.Entry<String,Object> entry : serialized.entrySet()){Log.d("BraqueConverter", entry.getKey()+" "+entry.getValue());}
            Collection<? extends RESTOperation> coll = Deserializer.deserialize(serialized, BrowseGithubsShowGithubThing.class);
            Log.d("BraqueConverter", "size="+coll.size());
            return coll;
        } catch (JSONException e) {
            Log.d("BraqueConverter", e.toString());
            return null;
        } finally {
            value.close();
        }
    }
}