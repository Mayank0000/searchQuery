package com.example.search.di.search;

import androidx.lifecycle.ViewModel;

import com.example.search.ui.movielist.SearchViewModel;
import com.example.search.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SearchViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    public abstract ViewModel bindAuthViewModel(SearchViewModel viewModel);

}
