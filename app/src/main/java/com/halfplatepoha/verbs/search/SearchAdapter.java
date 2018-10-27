package com.halfplatepoha.verbs.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.halfplatepoha.verbs.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<SearchPresenter.Model> items;

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_search_result, viewGroup, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(items.get(i).firstForm);
        viewHolder.tvFirstForm.setText(items.get(i).firstForm);
        viewHolder.tvMeaning.setText(items.get(i).firstMeaning);
        viewHolder.tvReading.setText(items.get(i).reading);
        viewHolder.itemView.setOnClickListener(v -> listener.onRowClick(v.getTag().toString()));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void addItems(List<SearchPresenter.Model> items) {
        if(this.items == null)
            this.items = new ArrayList<>();
        int previousLength = this.items.size();
        this.items.addAll(items);

        notifyItemRangeInserted(previousLength, items.size());
    }

    public void clearItems() {
        if(items != null) {
            int size = items.size();
            items.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvFirstForm)
        TextView tvFirstForm;

        @BindView(R.id.tvMeaning)
        TextView tvMeaning;

        @BindView(R.id.tvReading)
        TextView tvReading;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Listener {
        void onRowClick(String tag);
    }
}
