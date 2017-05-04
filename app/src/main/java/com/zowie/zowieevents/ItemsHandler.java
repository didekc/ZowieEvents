package com.zowie.zowieevents;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Didek on 03/05/2017.
 */

public class ItemsHandler {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Item> monitors;
    private ArrayList<Item> mousepads;
    private ArrayList<Item> mouses;

    public ItemsHandler() {
    }

    public ItemsHandler(ArrayList<Item> monitors, ArrayList<Item> mousepads, ArrayList<Item> mouses) {
        this.monitors = monitors;
        this.mousepads = mousepads;
        this.mouses = mouses;
    }

    public ArrayList<Item> getItems() {
        final ArrayList<Item> itemList = new ArrayList<>();

        for (Item item : monitors) {
            itemList.add(item);
        }

        for (Item item : mousepads) {
            itemList.add(item);
        }

        for (Item item : mouses) {
            itemList.add(item);
        }

        return itemList;
    }

    public ArrayList<Item> getItemsFromFile(Context context, String filename) {
        final ArrayList<Item> itemList = new ArrayList<>();
        final ArrayList<Item> monitors = new ArrayList<>();
        final ArrayList<Item> mousepads = new ArrayList<>();
        final ArrayList<Item> mouses = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset(filename, context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray items = json.getJSONArray("items");
            JSONArray monitorsJS = items.getJSONObject(0).getJSONArray("monitors");
            JSONArray mousepadsJS = items.getJSONObject(0).getJSONArray("mousepads");
            JSONArray mousesJS = items.getJSONObject(0).getJSONArray("mouses");

            for (int i = 0; i < monitorsJS.length(); i++) {
                Item item = new Item();

                item.setTitle(monitorsJS.getJSONObject(i).getString("title"));
                item.setDescription(monitorsJS.getJSONObject(i).getString("description"));
                item.setImage(monitorsJS.getJSONObject(i).getString("image"));
                item.setUrl(monitorsJS.getJSONObject(i).getString("url"));
                item.setAvailability(monitorsJS.getJSONObject(i).getString("availability"));

                monitors.add(item);
            }

            for (int i = 0; i < mousepadsJS.length(); i++) {
                Item item = new Item();

                item.setTitle(mousepadsJS.getJSONObject(i).getString("title"));
                item.setDescription(mousepadsJS.getJSONObject(i).getString("description"));
                item.setImage(mousepadsJS.getJSONObject(i).getString("image"));
                item.setUrl(mousepadsJS.getJSONObject(i).getString("url"));
                item.setAvailability(mousepadsJS.getJSONObject(i).getString("availability"));

                mouses.add(item);
            }

            for (int i = 0; i < mousesJS.length(); i++) {
                Item item = new Item();

                item.setTitle(mousesJS.getJSONObject(i).getString("title"));
                item.setDescription(mousesJS.getJSONObject(i).getString("description"));
                item.setImage(mousesJS.getJSONObject(i).getString("image"));
                item.setUrl(mousesJS.getJSONObject(i).getString("url"));
                item.setAvailability(mousesJS.getJSONObject(i).getString("availability"));

                mousepads.add(item);
            }

            itemList.addAll(monitors);
            itemList.addAll(mouses);
            itemList.addAll(mousepads);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    public ItemsHandler getFromFirebase(String jsonString) {
        final ArrayList<Item> monitors = new ArrayList<>();
        final ArrayList<Item> mousepads = new ArrayList<>();
        final ArrayList<Item> mouses = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray items = json.getJSONArray("items");
            JSONArray monitorsJS = items.getJSONObject(0).getJSONArray("monitors");
            JSONArray mousepadsJS = items.getJSONObject(0).getJSONArray("mousepads");
            JSONArray mousesJS = items.getJSONObject(0).getJSONArray("mouses");

            for (int i = 0; i < monitorsJS.length(); i++) {
                Item item = new Item();

                item.setTitle(monitorsJS.getJSONObject(i).getString("title"));
                item.setDescription(monitorsJS.getJSONObject(i).getString("description"));
                item.setImage(monitorsJS.getJSONObject(i).getString("image"));
                item.setUrl(monitorsJS.getJSONObject(i).getString("url"));
                item.setAvailability(monitorsJS.getJSONObject(i).getString("availability"));

                monitors.add(item);
            }

            for (int i = 0; i < mousepadsJS.length(); i++) {
                Item item = new Item();

                item.setTitle(mousepadsJS.getJSONObject(i).getString("title"));
                item.setDescription(mousepadsJS.getJSONObject(i).getString("description"));
                item.setImage(mousepadsJS.getJSONObject(i).getString("image"));
                item.setUrl(mousepadsJS.getJSONObject(i).getString("url"));
                item.setAvailability(mousepadsJS.getJSONObject(i).getString("availability"));

                mouses.add(item);
            }

            for (int i = 0; i < mousesJS.length(); i++) {
                Item item = new Item();

                item.setTitle(mousesJS.getJSONObject(i).getString("title"));
                item.setDescription(mousesJS.getJSONObject(i).getString("description"));
                item.setImage(mousesJS.getJSONObject(i).getString("image"));
                item.setUrl(mousesJS.getJSONObject(i).getString("url"));
                item.setAvailability(mousesJS.getJSONObject(i).getString("availability"));

                mousepads.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ItemsHandler(monitors, mousepads, mouses);
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public List<Item> getMonitors() {
        return monitors;
    }

    public void setMonitors(ArrayList<Item> monitors) {
        this.monitors = monitors;
    }

    public List<Item> getMousepads() {
        return mousepads;
    }

    public void setMousepads(ArrayList<Item> mousepads) {
        this.mousepads = mousepads;
    }

    public List<Item> getMouses() {
        return mouses;
    }

    public void setMouses(ArrayList<Item> mouses) {
        this.mouses = mouses;
    }
}
