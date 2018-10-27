package com.halfplatepoha.verbs;

import com.halfplatepoha.verbs.injection.AppComponent;
import com.halfplatepoha.verbs.injection.DaggerAppComponent;
import com.halfplatepoha.verbs.injection.modules.DataModule;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends DaggerApplication {

    @Named(DataModule.CONFIG_APP_DB)
    @Inject
    Lazy<RealmConfiguration> defaultConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(defaultConfiguration.get());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);
        return appComponent;
    }
}
