package com.example.search.di.search;


import android.app.Application;

import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SearchModule {

    @SearchScope
    @Provides
    static PagedList.Config getPageList() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(100).build();
    }

    @SearchScope
    @Provides
    static DividerItemDecoration getDecoration(Application application){
        return new DividerItemDecoration(application, DividerItemDecoration.VERTICAL);
    }

//    @SearchScope
//    @Provides
//    static SearchListAdapter getAdapter(){
//        return new SearchListAdapter();
//}
}


















