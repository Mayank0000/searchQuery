package com.example.search.di;

import com.example.search.di.detail.DetailModule;
import com.example.search.di.detail.DetailScope;
import com.example.search.di.detail.DetailViewModelsModule;
import com.example.search.ui.details.DetailActivity;
import com.example.search.ui.details.DetailViewModel;
import com.example.search.ui.movielist.SearchActivity;
import com.example.search.di.search.SearchModule;
import com.example.search.di.search.SearchScope;
import com.example.search.di.search.SearchViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @SearchScope
    @ContributesAndroidInjector(
            modules = {SearchViewModelsModule.class, SearchModule.class}
    )
    abstract SearchActivity contributeAuthActivity();

    @DetailScope
    @ContributesAndroidInjector(
            modules = {DetailViewModelsModule.class, DetailModule.class}

    )
    abstract DetailActivity contributeActivity();

}
