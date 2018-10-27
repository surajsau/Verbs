package com.halfplatepoha.verbs.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ExtractUtils {

    public static String readFromAsset(Context context, final String fileName) {
        String text = "";
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static List<UtilMeaning> extractEnglishMeanings(String meaningString) {
        if(meaningString == null)
            return null;

        List<UtilMeaning> utilMeanings = new ArrayList<>();
        String[] meanings = meaningString.split("\\|");

        for(String meaning : meanings) {
            Log.e("ENG", meaning);
            UtilMeaning utilMeaning = new UtilMeaning();
            String[] split = meaning.split("E\\.g\\.:");
            utilMeaning.meaning = split[0];

            if(split.length > 1)
                utilMeaning.example = split[1];

            utilMeanings.add(utilMeaning);
        }

        return utilMeanings;
    }

    public static List<UtilMeaning> extractKoreanMeanings(String meaningString) {
        if(meaningString == null)
            return null;

        List<UtilMeaning> utilMeanings = new ArrayList<>();
        String[] meanings = meaningString.split("\\|");

        for(String meaning : meanings) {
            Log.e("KOR", meaning);
            UtilMeaning utilMeaning = new UtilMeaning();
            String[] split = meaning.split("例::");
            utilMeaning.meaning = split[0];

            if(split.length > 1)
                utilMeaning.example = split[1];

            utilMeanings.add(utilMeaning);
        }

        return utilMeanings;
    }

    public static List<UtilMeaning> extractChineseMeanings(String meaningString) {
        if(meaningString == null)
            return null;

        List<UtilMeaning> utilMeanings = new ArrayList<>();
        String[] meanings = meaningString.split("\\|");

        for(String meaning : meanings) {
            Log.e("CHI", meaning);
            UtilMeaning utilMeaning = new UtilMeaning();
            String[] split = meaning.split("例::");
            utilMeaning.meaning = split[0];

            if(split.length > 1)
                utilMeaning.example = split[1];

            utilMeanings.add(utilMeaning);
        }

        return utilMeanings;
    }

    public static List<UtilMeaning> extractJapaneseMeanings(String meaningString) {
        if(meaningString == null)
            return null;

        List<UtilMeaning> utilMeanings = new ArrayList<>();
        String[] meanings = meaningString.split("\\|");

        for(String meaning : meanings) {
            Log.e("JAP", meaning);
            UtilMeaning utilMeaning = new UtilMeaning();
            String[] split = meaning.split("例::");
            utilMeaning.meaning = split[0];

            if(split.length > 1) {
                String example = split[1];

                String[] exampleSplit = example.split("//");
                utilMeaning.example = exampleSplit[0];
                utilMeaning.reading = exampleSplit[1];
            }

            utilMeanings.add(utilMeaning);
        }

        return utilMeanings;
    }

    public static void extract(Realm realm, Context context) {
        try {
            final File file = new File(Environment.getExternalStorageDirectory().getPath().concat("/verb.realm"));
            if (file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }

            realm.writeCopyTo(file);
            Toast.makeText(context, "Success export realm file", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            realm.close();
            e.printStackTrace();
        }
    }
}
