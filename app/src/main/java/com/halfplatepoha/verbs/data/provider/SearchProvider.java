package com.halfplatepoha.verbs.data.provider;

import com.halfplatepoha.verbs.Constants;
import com.halfplatepoha.verbs.data.models.Verb;
import com.halfplatepoha.verbs.injection.modules.DataModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SearchProvider implements ISearchProvider {

    private Realm realm;

    @Inject
    public SearchProvider(@Named(DataModule.CONGIF_VERB_DB)RealmConfiguration verbConfig) {
        this.realm = Realm.getInstance(verbConfig);
    }

    @Override
    public List<Verb> search(String search) {
        RealmResults<Verb> results = realm.where(Verb.class)
                .contains(Constants.Schema.Verb.READING, search)
                .or()
                .contains(Constants.Schema.Verb.FIRST_FORM, search)
                .distinct(Constants.Schema.Verb.FIRST_FORM)
                .findAll();
        return results;
    }
}
