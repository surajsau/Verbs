package com.halfplatepoha.verbs.search;

import com.halfplatepoha.verbs.base.IPresenter;
import com.halfplatepoha.verbs.base.IView;

import java.util.List;

public interface SearchContract {

    interface View extends IView {

        void clearResults();

        void addResults(List<SearchPresenter.Model> models);

        void openVerbDetails(String tag);

    }

    interface Presenter extends IPresenter {

        void onSearchTextChange(String searchText);

        void onSearchRowClicked(String tag);

    }

}
