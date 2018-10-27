package com.halfplatepoha.verbs.extraction;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.halfplatepoha.verbs.R;
import com.halfplatepoha.verbs.data.models.Meaning;
import com.halfplatepoha.verbs.data.models.Verb;
import com.halfplatepoha.verbs.utils.ExtractUtils;
import com.halfplatepoha.verbs.utils.UtilMeaning;
import com.halfplatepoha.verbs.utils.UtilVerb;

import java.util.List;

import io.realm.Realm;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ExtractionActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extraction);

        realm = Realm.getDefaultInstance();

        findViewById(R.id.btnExtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EasyPermissions.hasPermissions(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ExtractUtils.extract(realm, view.getContext());
                } else {
                    EasyPermissions.requestPermissions(ExtractionActivity.this, "Need permission", 101, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            }
        });

        findViewById(R.id.btnGenerate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Download verbs.json from https://drive.google.com/file/d/1_69wx0XTj--cJgDitj1sQBtKptQ2DzZY/view?usp=sharing
                 * Download verbs.realm from https://drive.google.com/file/d/1WuQJK_0WfjeItys4WJCqPwhchJ_I-_jY/view?usp=sharing
                 */
                String json = ExtractUtils.readFromAsset(view.getContext(), "verbs.json");
                Gson gson = new Gson();
                UtilVerb[] utilVerbs = gson.fromJson(json, UtilVerb[].class);

                realm.beginTransaction();

                for(UtilVerb utilVerb : utilVerbs) {
                    Verb verb = realm.createObject(Verb.class);
                    verb.antonym = utilVerb.antonym;
                    verb.firstForm = utilVerb.form_1;
                    verb.firstVerb = utilVerb.v1;
                    verb.firstVerbMasu = utilVerb.v1_add;
                    verb.firstVerbReading = utilVerb.v1_read;
                    verb.firstVerbRomaji = utilVerb.v1_romaji;
                    verb.firstVerbMasuReading = utilVerb.v1_add_read;
                    verb.firstVerbMasuRomaji = utilVerb.v1_add_romaji;
                    verb.secondForm = utilVerb.form_2;
                    verb.secondVerb = utilVerb.v2;
                    verb.secondVerbReading = utilVerb.v2_read;
                    verb.secondVerbRomaji = utilVerb.v2_romaji;
                    verb.nlbLink = utilVerb.nlb_link;
                    verb.noun = utilVerb.noun;
                    verb.remarks = utilVerb.remarks;
                    verb.reading = utilVerb.read;
                    verb.romaji = utilVerb.romaji;
                    verb.usagePattern = utilVerb.pattern;
                    verb.synonymn = utilVerb.synonymn;
                    verb.pronounce = utilVerb.pronounce;
                    verb.seeAlso = utilVerb.see_also;
                    verb.transitive = utilVerb.transitive;

                    List<UtilMeaning> japaneseMeanings = ExtractUtils.extractJapaneseMeanings(utilVerb.meaning_ex);
                    List<UtilMeaning> englishMeanings = ExtractUtils.extractEnglishMeanings(utilVerb.english);
                    List<UtilMeaning> koreanMeanings = ExtractUtils.extractKoreanMeanings(utilVerb.korean);
                    List<UtilMeaning> chinese1Meanings = ExtractUtils.extractChineseMeanings(utilVerb.chinese_1);
                    List<UtilMeaning> chinese2Meanings = ExtractUtils.extractChineseMeanings(utilVerb.chinese_2);

                    for(UtilMeaning japaneseMeaning : japaneseMeanings) {
                        Meaning meaning = realm.createObject(Meaning.class);
                        meaning.language = Meaning.LANG_JAP;
                        meaning.example = japaneseMeaning.example;
                        meaning.reading = japaneseMeaning.reading;
                        meaning.meaning = japaneseMeaning.meaning;

                        verb.meanings.add(meaning);
                    }

                    for(UtilMeaning englishMeaning : englishMeanings) {
                        Meaning meaning = realm.createObject(Meaning.class);
                        meaning.language = Meaning.LANG_ENG;
                        meaning.example = englishMeaning.example;
                        meaning.reading = englishMeaning.reading;
                        meaning.meaning = englishMeaning.meaning;

                        verb.meanings.add(meaning);
                    }

                    for(UtilMeaning koreanMeaning : koreanMeanings) {
                        Meaning meaning = realm.createObject(Meaning.class);
                        meaning.language = Meaning.LANG_KOR;
                        meaning.example = koreanMeaning.example;
                        meaning.reading = koreanMeaning.reading;
                        meaning.meaning = koreanMeaning.meaning;

                        verb.meanings.add(meaning);
                    }

                    for(UtilMeaning chinese1Meaning : chinese1Meanings) {
                        Meaning meaning = realm.createObject(Meaning.class);
                        meaning.language = Meaning.LANG_CHINESE_2;
                        meaning.example = chinese1Meaning.example;
                        meaning.reading = chinese1Meaning.reading;
                        meaning.meaning = chinese1Meaning.meaning;

                        verb.meanings.add(meaning);
                    }

                    for(UtilMeaning chinese2Meaning : chinese2Meanings) {
                        Meaning meaning = realm.createObject(Meaning.class);
                        meaning.language = Meaning.LANG_CHINESE_2;
                        meaning.example = chinese2Meaning.example;
                        meaning.reading = chinese2Meaning.reading;
                        meaning.meaning = chinese2Meaning.meaning;

                        verb.meanings.add(meaning);
                    }


                }

                realm.commitTransaction();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @AfterPermissionGranted(101)
    public void onResult() {
        ExtractUtils.extract(realm, this);
    }
}
