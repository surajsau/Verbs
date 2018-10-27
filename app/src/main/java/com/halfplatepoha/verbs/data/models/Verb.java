package com.halfplatepoha.verbs.data.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Verb extends RealmObject {

    public String firstForm;
    public String secondForm;
    public String reading;
    public String romaji;
    public String firstVerb;
    public String firstVerbReading;
    public String firstVerbRomaji;
    public String firstVerbMasu;
    public String firstVerbMasuReading;
    public String firstVerbMasuRomaji;
    public String secondVerb;
    public String secondVerbReading;
    public String secondVerbRomaji;
    public String transitive;
    public String usagePattern;
    public RealmList<Meaning> meanings;
    public String seeAlso;
    public String pronounce;
    public String noun;
    public String synonymn;
    public String antonym;
    public String remarks;
    public String nlbLink;

}
