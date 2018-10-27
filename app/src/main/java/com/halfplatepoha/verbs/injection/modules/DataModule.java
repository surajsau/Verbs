package com.halfplatepoha.verbs.injection.modules;

import com.halfplatepoha.verbs.data.provider.ISearchProvider;
import com.halfplatepoha.verbs.data.provider.SearchProvider;
import com.halfplatepoha.verbs.injection.AppScope;

import dagger.Binds;
import dagger.Module;

@Module(includes = RealmModule.class)
public abstract class DataModule {

    public static final String CONGIF_VERB_DB = "verb_config";
    public static final String CONFIG_APP_DB = "app_config";

    @Binds
    @AppScope
    abstract ISearchProvider searchProvider(SearchProvider searchProvider);

}
