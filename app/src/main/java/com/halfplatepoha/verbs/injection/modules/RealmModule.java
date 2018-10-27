package com.halfplatepoha.verbs.injection.modules;

import com.halfplatepoha.verbs.Constants;
import com.halfplatepoha.verbs.data.Migrator;
import com.halfplatepoha.verbs.data.modules.AppDbModule;
import com.halfplatepoha.verbs.data.modules.VerbDbModule;
import com.halfplatepoha.verbs.injection.AppScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public abstract class RealmModule {

    @Named(DataModule.CONFIG_APP_DB)
    @AppScope
    @Provides
    static RealmConfiguration appRealmConfig(Migrator migrator, AppDbModule appDbModule) {
        return new RealmConfiguration.Builder()
                .migration(migrator)
                .modules(appDbModule)
                .schemaVersion(Constants.APP_DB_VERSION)
                .build();
    }

    @Named(DataModule.CONGIF_VERB_DB)
    @AppScope
    @Provides
    static RealmConfiguration verbRealmConfig(VerbDbModule verbDbModule) {
        return new RealmConfiguration.Builder()
                .assetFile("verb.realm")
                .modules(verbDbModule)
                .schemaVersion(Constants.VERB_DB_VERSION)
                .build();
    }

    @Provides
    @AppScope
    static Migrator migrator() {
        return new Migrator();
    }

    @Provides
    @AppScope
    static AppDbModule appDbModule() {
        return new AppDbModule();
    }

    @Provides
    @AppScope
    static VerbDbModule verbDbModule() {
        return new VerbDbModule();
    }
}
