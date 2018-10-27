package com.halfplatepoha.verbs.injection.modules;

import android.app.Application;
import android.content.Context;

import com.halfplatepoha.verbs.App;
import com.halfplatepoha.verbs.injection.AppScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    @AppScope
    abstract Context application(App app);

}
