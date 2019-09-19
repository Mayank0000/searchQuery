package com.example.search.ui.movielist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.search.R;
import com.example.search.databinding.HorizontalRowLayoutBinding;
import com.example.search.databinding.RowLayoutBinding;
import com.example.search.model.UserModelClass;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.ViewHolder> {

    private List<UserModelClass> mQuizList;
    private OnClickHorizontalRow onClickHorizontalRow;

    public BookmarkListAdapter(OnClickHorizontalRow onClickHorizontalRow) {
        this.onClickHorizontalRow = onClickHorizontalRow;
        mQuizList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HorizontalRowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.horizontal_row_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.binding.setModel(mQuizList.get(position));
        holder.binding.bookmark.setOnClickListener(view ->
                onClickHorizontalRow.onBookMarkClick(holder.binding.getModel()));
        holder.itemView.setOnClickListener(view -> onClickHorizontalRow.onClick(holder.binding.getModel().getImDbId()));
    }

    public void setQuizList( List<UserModelClass> mQuizList){
        this.mQuizList = mQuizList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mQuizList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        HorizontalRowLayoutBinding binding;

        ViewHolder(HorizontalRowLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }



    }
}
