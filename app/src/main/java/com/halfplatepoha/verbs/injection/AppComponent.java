package com.halfplatepoha.verbs.injection;

import com.halfplatepoha.verbs.App;
import com.halfplatepoha.verbs.injection.modules.ActivityInjectionModule;
import com.halfplatepoha.verbs.injection.modules.AppModule;
import com.halfplatepoha.verbs.injection.modules.DataModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {ActivityInjectionModule.class, AppModule.class, DataModule.class})
@AppScope
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }

    void inject(App app);
}
