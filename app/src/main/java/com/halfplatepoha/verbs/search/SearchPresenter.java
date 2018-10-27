package com.halfplatepoha.verbs.search;

import com.halfplatepoha.verbs.base.BasePresenter;
import com.halfplatepoha.verbs.data.models.Meaning;
import com.halfplatepoha.verbs.data.models.Verb;
import com.halfplatepoha.verbs.data.provider.ISearchProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private ISearchProvider searchProvider;

    private Disposable searchDisposable;

    @Inject
    public SearchPresenter(SearchContract.View view,
                           ISearchProvider searchProvider) {
        super(view);
        this.searchProvider = searchProvider;
    }

    @Override
    public void onSearchTextChange(String searchText) {
        searchDisposable = Flowable.just(searchText)
                .filter(s -> s != null && s.length() != 0)
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(s -> searchProvider.search(s))
                .filter(results -> results != null && results.size() > 0)
                .subscribe(results -> {
                    view.clearResults();

                    List<Model> models = new ArrayList<>();

                    for(Verb result : results) {
                        Model model = new Model();
                        model.firstForm = result.firstForm;
                        model.reading = result.reading;
                        for(Meaning meaning : result.meanings) {
                            if(Meaning.LANG_KOR.equals(meaning.language)) {
                                model.firstMeaning = meaning.meaning;
                                break;
                            }
                        }
                        models.add(model);
                    }

                    view.addResults(models);
                });
    }

    @Override
    public void onSearchRowClicked(String tag) {
        view.openVerbDetails(tag);
    }

    @Override
    public void onDestroy() {
        if(searchDisposable != null && !searchDisposable.isDisposed())
            searchDisposable.dispose();
        super.onDestroy();
    }

    public static class Model {
        public String firstForm;
        public String firstMeaning;
        public String reading;
    }

}
