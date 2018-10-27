package com.halfplatepoha.verbs.injection.modules;

import com.halfplatepoha.verbs.injection.ActivityScope;
import com.halfplatepoha.verbs.search.SearchActivity;
import com.halfplatepoha.verbs.search.SearchModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ActivityInjectionModule {

    @ContributesAndroidInjector(modules = {SearchModule.class})
    @ActivityScope
    abstract SearchActivity searchActivity();
}
