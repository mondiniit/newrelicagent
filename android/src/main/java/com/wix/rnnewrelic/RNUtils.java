package com.wix.rnnewrelic;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rotemm on 29/06/2016.
 * Update by Dmondini - CastoMolina 14/05/2019
 */
public class RNUtils {
    /**
     * ReadableMap to HashMap, this has been implemented already in RN 0.26's ReadableMap, kill it when possible
     * @param ReadableMap
     * @return
     */
    public static HashMap<String, Object> toHashMap(ReadableMap ReadableMap) {
        ReadableMapKeySetIterator iterator = ReadableMap.keySetIterator();
        HashMap<String, Object> hashMap = new HashMap<>();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (ReadableMap.getType(key)) {
                case Null:
                    hashMap.put(key, null);
                    break;
                case Boolean:
                    hashMap.put(key, ReadableMap.getBoolean(key));
                    break;
                case Number:
                    hashMap.put(key, ReadableMap.getDouble(key));
                    break;
                case String:
                    hashMap.put(key, ReadableMap.getString(key));
                    break;
                case Map:
                    hashMap.put(key, toHashMap(ReadableMap.getMap(key)));
                    break;
                case Array:
                    hashMap.put(key, toArrayList(ReadableMap.getArray(key)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return hashMap;
    }

    /**
     * RedeableArray to ArrayList, this has been implemented already in RN 0.26's ReadableMap, kill it when possible
     * @param RedeableArray
     * @return
     */
    public static ArrayList<Object> toArrayList(RedeableArray RedeableArray) {
        ArrayList<Object> arrayList = new ArrayList<>();

        for (int i = 0; i < RedeableArray.size(); i++) {
            switch (RedeableArray.getType(i)) {
                case Null:
                    arrayList.add(null);
                    break;
                case Boolean:
                    arrayList.add(RedeableArray.getBoolean(i));
                    break;
                case Number:
                    arrayList.add(RedeableArray.getDouble(i));
                    break;
                case String:
                    arrayList.add(RedeableArray.getString(i));
                    break;
                case Map:
                    arrayList.add(toHashMap(RedeableArray.getMap(i)));
                    break;
                case Array:
                    arrayList.add(toArrayList(RedeableArray.getArray(i)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + i + ".");
            }
        }
        return arrayList;
    }
}
