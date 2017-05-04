package com.zowie.zowieevents.items;

import android.content.Context;

import com.zowie.zowieevents.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Didek on 03/05/2017.
 */

public class Item {
    private static final String TAG = MainActivity.class.getSimpleName();
    private String title;
    private String image;
    private String url;
    private String description;
    private String availability;

    public Item() {}

    public Item(String title, String description, String image, String url, String availability) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
        this.availability = availability;
    }

    public static ArrayList<Item> getMonitorsItemsFromFile(Context context, String filename) {
        final ArrayList<Item> monitors = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset(context, filename);
            JSONObject json = new JSONObject(jsonString);
            JSONObject items = json.getJSONObject("items");
            JSONArray monitorsJS = items.getJSONArray("monitors");

            for (int i = 0; i < monitorsJS.length(); i++) {
                Item item = new Item();

                item.setTitle(monitorsJS.getJSONObject(i).getString("title"));
                item.setDescription(monitorsJS.getJSONObject(i).getString("description"));
                item.setImage(monitorsJS.getJSONObject(i).getString("image"));
                item.setUrl(monitorsJS.getJSONObject(i).getString("url"));
                item.setAvailability(monitorsJS.getJSONObject(i).getString("availability"));

                monitors.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return monitors;
    }

    public static ArrayList<Item> getMousepadsItemsFromFile(Context context, String filename) {
        final ArrayList<Item> mousepads = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset(context, filename);
            JSONObject json = new JSONObject(jsonString);
            JSONObject items = json.getJSONObject("items");
            JSONArray mousepadsJS = items.getJSONArray("mousepads");

            for (int i = 0; i < mousepadsJS.length(); i++) {
                Item item = new Item();

                item.setTitle(mousepadsJS.getJSONObject(i).getString("title"));
                item.setDescription(mousepadsJS.getJSONObject(i).getString("description"));
                item.setImage(mousepadsJS.getJSONObject(i).getString("image"));
                item.setUrl(mousepadsJS.getJSONObject(i).getString("url"));
                item.setAvailability(mousepadsJS.getJSONObject(i).getString("availability"));

                mousepads.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mousepads;
    }

    public static ArrayList<Item> getMousesItemsFromFile(Context context, String filename) {
        final ArrayList<Item> mouses = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset(context, filename);
            JSONObject json = new JSONObject(jsonString);
            JSONObject items = json.getJSONObject("items");
            JSONArray mousesJS = items.getJSONArray("mouses");

            for (int i = 0; i < mousesJS.length(); i++) {
                Item item = new Item();

                item.setTitle(mousesJS.getJSONObject(i).getString("title"));
                item.setDescription(mousesJS.getJSONObject(i).getString("description"));
                item.setImage(mousesJS.getJSONObject(i).getString("image"));
                item.setUrl(mousesJS.getJSONObject(i).getString("url"));
                item.setAvailability(mousesJS.getJSONObject(i).getString("availability"));

                mouses.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mouses;
    }

    public static ArrayList<Item> getAllItemsFromFile(Context context, String filename) {
        final ArrayList<Item> itemList = new ArrayList<>();

        itemList.addAll(getMonitorsItemsFromFile(context, filename));
        itemList.addAll(getMousepadsItemsFromFile(context, filename));
        itemList.addAll(getMousesItemsFromFile(context, filename));

        return itemList;
    }

    private static String loadJsonFromAsset(Context context, String filename) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
