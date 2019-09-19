package com.example.search.ui.movielist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.search.R;
import com.example.search.databinding.PagingDemoLayoutBinding;
import com.example.search.ui.details.DetailActivity;
import com.example.search.util.Constants;
import com.example.search.model.UserModelClass;
import com.example.search.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;
import java.util.Objects;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchActivity extends DaggerAppCompatActivity implements OnClickRowEvent {

    @Inject ViewModelProviderFactory viewModelFactory;
    @Inject DividerItemDecoration itemDecorator;
    private PageListAdapter adapter;
    private PagingDemoLayoutBinding binding;
    private SearchViewModel viewModel;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.paging_demo_layout);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
        init();

        //default search key
        viewModel.searchQuery("friend");
    }


    private void init() {
        adapter = new PageListAdapter(this,this);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));
        binding.list.addItemDecoration(itemDecorator);
        if (!Constants.checkInternetConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), Constants.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                    .show();
        }
        viewModel.getListLiveData().observe(this, pagedList -> adapter.submitList(pagedList));
        viewModel.getList().observe(this, userModelClasses -> adapter.setBookmarkListAdapter(userModelClasses));
        viewModel.getProgressLoadStatus().observe(this, status -> {
            if (Objects.requireNonNull(status).equalsIgnoreCase(Constants.LOADING)) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else if (status.equalsIgnoreCase(Constants.LOADED)) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.submitButton.setOnClickListener(view -> {
            if(binding.searchText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter some query",Toast.LENGTH_SHORT).show();
            }else{
                viewModel.searchQuery(binding.searchText.getText().toString());
            }
        });
    }

    @Override
    public void onBookMarkClick(UserModelClass userModelClass,int type) {
        if(type == 1)
            viewModel.insert(userModelClass);
        else
            viewModel.delete(userModelClass);

    }

    @Override
    public void onItemClick(String id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
