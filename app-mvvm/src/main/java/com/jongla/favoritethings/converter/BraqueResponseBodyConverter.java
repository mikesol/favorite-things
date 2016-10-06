package com.jongla.favoritethings.converter;

import android.util.Log;

import com.jongla.favoritethings.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import braque.RESTShow;
import braque.braqued.Deserializer;
import braque.braqued.StringProvisioner;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class BraqueResponseBodyConverter implements Converter<ResponseBody, Collection<? extends RESTShow>> {

    private final Class<? extends RESTShow> deserializingTo;
    private final BraqueProperty[] properties;
    private final String arrayAddress;
    private final String topLevelPath;

    BraqueResponseBodyConverter(Class<? extends RESTShow> deserializingTo, String topLevelPath, BraqueProperty[] properties) {
        this(deserializingTo, topLevelPath, properties, null);
    }

    BraqueResponseBodyConverter(Class<? extends RESTShow> deserializingTo, String topLevelPath, BraqueProperty[] properties, String arrayAddress) {
        this.deserializingTo = deserializingTo;
        this.topLevelPath = topLevelPath;
        this.properties = properties;
        this.arrayAddress = arrayAddress;
    }

    static class BraqueProperty {
        final String receivedName;
        final String usedName;
        BraqueProperty(String receivedName, String usedName) {
            this.receivedName = receivedName;
            this.usedName = usedName.toLowerCase();
        }
    }

    @Override public Collection<? extends RESTShow> convert(ResponseBody value) throws IOException {
        try {
            JSONArray items;
            if (arrayAddress == null) {
                items = new JSONArray(value.string());
            } else {
                JSONObject jsonObject = new JSONObject(value.string());
                String[] addressSplit = arrayAddress.split("\\.");
                int i = 0;
                for (; i < addressSplit.length - 1; i++) {
                    jsonObject = jsonObject.getJSONObject(addressSplit[i]);
                }
                items = jsonObject.getJSONArray(addressSplit[i]);
            }
            Map<String, Object> serialized = new HashMap<>();
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String id = null;
                boolean first = true;
                for (BraqueProperty property : properties) {
                    if (first && !property.usedName.equals(StringProvisioner.propId().toLowerCase())) {
                        throw new IllegalStateException("id must come first");
                    }
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
                        JSONArray maybeArray = thisItem.optJSONArray(splitPath[j]);
                        if (maybeArray != null) {
                            for (int k = 0; k < maybeArray.length(); k++) {
                                serialized.put(Utils.toBraquePath(topLevelPath, id,
                                        property.usedName, Integer.toString(k)),
                                        maybeArray.get(k));
                            }
                        } else {
                            Object serializedValue;
                            if (first) {
                                serializedValue = id = thisItem.get(splitPath[j]).toString();
                                first = false;
                            } else {
                                serializedValue = thisItem.get(splitPath[j]).toString();
                            }
                            serialized.put(Utils.toBraquePath(topLevelPath, id, property.usedName), serializedValue);
                        }
                    }
                }
            }
            Collection<? extends RESTShow> coll = Deserializer.deserialize(serialized, deserializingTo);
            return coll;
        } catch (JSONException e) {
            Log.e("Braque", e.toString());
            return null;
        } finally {
            value.close();
        }
    }
}