package com.halfplatepoha.verbs.search;

import com.halfplatepoha.verbs.injection.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SearchModule {

    @Binds
    @ActivityScope
    abstract SearchContract.View view(SearchActivity activity);

    @Binds
    @ActivityScope
    abstract SearchContract.Presenter presenter(SearchPresenter presenter);

    @Provides
    @ActivityScope
    static SearchAdapter searchAdapter() {
        return new SearchAdapter();
    }

}
