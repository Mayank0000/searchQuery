package com.example.search.ui.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.search.R;
import com.example.search.databinding.RowLayoutBinding;
import com.example.search.model.UserModelClass;

import java.util.List;

public class PageListAdapter extends PagedListAdapter<UserModelClass, RecyclerView.ViewHolder> implements OnClickHorizontalRow{

    private OnClickRowEvent onClickRowEvent;
    private BookmarkListAdapter bookmarkListAdapter;
    private Context context;

    public PageListAdapter(Context context,OnClickRowEvent onClickRowEvent) {
        super(UserModelClass.DIFF_CALLBACK);
        this.context = context;
        this.onClickRowEvent = onClickRowEvent;
        bookmarkListAdapter = new BookmarkListAdapter(this);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==1){
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recyclerviews, parent, false);
            return new ViewHolder(view);

        }else {
            RowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.row_layout, parent, false);
            return new MyViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {
        if(position==0){
            ViewHolder holder = (ViewHolder) holder1;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false);
            holder.rvHorizontal.setLayoutManager(linearLayoutManager);
            holder.rvHorizontal.setAdapter(bookmarkListAdapter);
        }else {
            MyViewHolder holder = (MyViewHolder) holder1;
            holder.binding.setModel(getItem(position));
            holder.binding.bookmark.setOnClickListener(view ->
                    onClickRowEvent.onBookMarkClick(holder.binding.getModel(),1));
            holder.itemView.setOnClickListener(view ->
                    onClickRowEvent.onItemClick(holder.binding.getModel().getImDbId()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public void setBookmarkListAdapter( List<UserModelClass> mQuizList){
        bookmarkListAdapter.setQuizList(mQuizList);
    }

    @Override
    public void onClick(String imDbID) {
        onClickRowEvent.onItemClick(imDbID);
    }

    @Override
    public void onBookMarkClick(UserModelClass userModelClass) {
        onClickRowEvent.onBookMarkClick(userModelClass,2);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding binding;

        MyViewHolder(RowLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvHorizontal;
        ViewHolder(View itemView) {
            super(itemView);
            rvHorizontal = itemView.findViewById(R.id.horizontalList);
        }
    }
}
