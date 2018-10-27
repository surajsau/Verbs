package com.halfplatepoha.verbs.data.modules;

import com.halfplatepoha.verbs.data.models.Meaning;
import com.halfplatepoha.verbs.data.models.Verb;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {Verb.class, Meaning.class})
public class VerbDbModule {}
