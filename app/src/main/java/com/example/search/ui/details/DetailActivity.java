package com.example.search.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.search.R;
import com.example.search.databinding.ActivityDetailsBinding;
import com.example.search.model.Details;
import com.example.search.util.Constants;
import com.example.search.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    private DetailViewModel viewModel;
    @Inject
    RequestManager requestManager;
    @Inject
    ViewModelProviderFactory providerFactory;
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        viewModel = ViewModelProviders.of(this, providerFactory).get(DetailViewModel.class);
        viewModel.getDetailList(getIntent().getStringExtra("id"));
        setProgressBar(true);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthUser().observe(this, details -> {
            if (details != null) {
                binding.year.setText(details.getYear());
                binding.title.setText(details.getTitle());
                binding.releaseDate.setText(details.getReleased());
                binding.runtime.setText(details.getRuntime());
                binding.actors.setText(details.getActors());
                binding.genre.setText(details.getGenre());
                binding.director.setText(details.getDirector());
                binding.plot.setText(details.getPlot());
                binding.language.setText(details.getLanguage());
                requestManager.load(details.getPoster()).into(binding.imagePoster);
                setProgressBar(false);
            }else{
                if (!Constants.checkInternetConnection(this)) {
                    Snackbar.make(findViewById(android.R.id.content), Constants.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void setProgressBar(boolean flag) {
        if (flag) {
            binding.mainLayout.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.mainLayout.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}