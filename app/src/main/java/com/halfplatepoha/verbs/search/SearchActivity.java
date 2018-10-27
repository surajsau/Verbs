package com.halfplatepoha.verbs.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.halfplatepoha.verbs.R;
import com.halfplatepoha.verbs.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchContract.Presenter> implements SearchContract.View {

    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.rlSearchResults)
    RecyclerView rlSearchResults;

    @Inject
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchAdapter.setListener(tag -> presenter.onSearchRowClicked(tag));

        rlSearchResults.setLayoutManager(new LinearLayoutManager(this));
        rlSearchResults.setAdapter(searchAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString();
                presenter.onSearchTextChange(searchText);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void clearResults() {
        searchAdapter.clearItems();
    }

    @Override
    public void addResults(List<SearchPresenter.Model> models) {
        searchAdapter.addItems(models);
    }

    @Override
    public void openVerbDetails(String tag) {

    }
}
