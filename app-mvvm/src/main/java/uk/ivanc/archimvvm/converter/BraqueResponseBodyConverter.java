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

public class BraqueResponseBodyConverter implements Converter<ResponseBody, Collection<? extends RESTOperation>> {

    private final Class<? extends RESTOperation> deserializingTo;
    private final String idProp;
    private final BraqueProperty[] properties;

    BraqueResponseBodyConverter(Class<? extends RESTOperation> deserializingTo, String idProp, BraqueProperty[] properties) {
        this.deserializingTo = deserializingTo;
        this.idProp = idProp;
        this.properties = properties;
    }

    static class BraqueProperty {
        final String receivedName;
        final String usedName;
        BraqueProperty(String receivedName, String usedName) {
            this.receivedName = receivedName.toLowerCase();
            this.usedName = usedName.toLowerCase();
        }
    }

    @Override public Collection<? extends RESTOperation> convert(ResponseBody value) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(value.string());
            JSONArray items = jsonObject.getJSONArray("items");
            Map<String, Object> serialized = new HashMap<>();

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                if (!item.has(idProp)) {
                    Log.e("BraqueConverter", "item lacks "+idProp);
                    continue;
                }
                String id = Integer.toString(item.getInt(idProp));
                serialized.put(Utils.toBraquePath(StringProvisioner.pathBrowseGithubs(), id, "id"), id);
                for (BraqueProperty property : properties) {
                    JSONObject thisItem = item;
                    String[] splitPath = property.receivedName.split("\\.");
                    int j = 0;
                    for (; j < splitPath.length - 1; j++) {
                        if (thisItem.has(splitPath[j])) {
                            thisItem = thisItem.getJSONObject(splitPath[j]);
                        } else {
                            break;
                        }
                    }
                    if (j < (splitPath.length - 1)) {
                        continue;
                    }
                    if (thisItem.has(splitPath[j])) {
                        serialized.put(Utils.toBraquePath(StringProvisioner.pathBrowseGithubs(), id, property.usedName),
                                thisItem.get(splitPath[j]));
                    }
                }
            }//for (Map.Entry<String,Object> entry : serialized.entrySet()){Log.d("BraqueConverter", entry.getKey()+" "+entry.getValue());}
            Collection<? extends RESTOperation> coll = Deserializer.deserialize(serialized, deserializingTo);
            return coll;
        } catch (JSONException e) {
            Log.e("BraqueConverter", e.toString());
            return null;
        } finally {
            value.close();
        }
    }
}