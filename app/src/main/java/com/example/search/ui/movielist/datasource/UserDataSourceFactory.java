package com.example.search.ui.movielist.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.example.search.util.Repository;
import com.example.search.model.UserModelClass;


public class UserDataSourceFactory extends DataSource.Factory<Integer, UserModelClass> {

    private MutableLiveData<UserDataSourceClass> liveData;
    private Repository repository;
    private String query;

    public UserDataSourceFactory(Repository repository,String query) {
        this.repository = repository;
        this.query = query;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserDataSourceClass> getMutableLiveData() {
        return liveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, UserModelClass> create() {
        UserDataSourceClass dataSourceClass = new UserDataSourceClass(repository,query);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
