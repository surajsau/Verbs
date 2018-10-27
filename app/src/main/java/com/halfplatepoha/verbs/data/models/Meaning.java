package com.halfplatepoha.verbs.data.models;

import io.realm.RealmObject;

public class Meaning extends RealmObject {

    public static final String LANG_ENG = "eng";
    public static final String LANG_JAP = "jap";
    public static final String LANG_KOR = "kor";
    public static final String LANG_CHINESE_1= "ch1";
    public static final String LANG_CHINESE_2 = "ch2";

    public String language;
    public String meaning;
    public String example;
    public String reading;

}
