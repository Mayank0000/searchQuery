package com.example.search.di.detail;

import androidx.lifecycle.ViewModel;

import com.example.search.di.ViewModelKey;
import com.example.search.ui.details.DetailViewModel;
import com.example.search.ui.movielist.SearchViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    public abstract ViewModel bindAuthViewModel(DetailViewModel viewModel);

}
