package com.example.search.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.search.db.model.Movie;
import com.example.search.ui.movielist.datasource.UserDataSourceClass;
import com.example.search.ui.movielist.datasource.UserDataSourceFactory;
import com.example.search.util.Repository;
import com.example.search.model.UserModelClass;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private static final String TAG = "SearchViewModel";
    private UserDataSourceFactory userDataSourceFactory;
    private LiveData<String> progressLoadStatus = new MutableLiveData<>();
    private Repository repository;
    private PagedList.Config pagedListConfig;
    private MediatorLiveData<PagedList<UserModelClass>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SearchViewModel(Repository repository,PagedList.Config pagedListConfig) {
        this.repository = repository;
        this.pagedListConfig = pagedListConfig;
    }

    public void searchQuery(String query){
        userDataSourceFactory = new UserDataSourceFactory(repository,query);
        initializePaging();
    }

    public void insert(UserModelClass userModelClass){
        Movie movie = new Movie();
        movie.imDbID = userModelClass.getImDbId();
        movie.imageUrl = userModelClass.getImageUrl();
        movie.year = userModelClass.getYear();
        movie.title = userModelClass.getName();
        repository.insert(movie);
    }


    public void delete(UserModelClass userModelClass){
        Movie movie = new Movie();
        movie.imDbID = userModelClass.getImDbId();
        movie.imageUrl = userModelClass.getImageUrl();
        movie.year = userModelClass.getYear();
        movie.title = userModelClass.getName();
        repository.delete(movie);
    }

    public LiveData<List<UserModelClass>> getList(){
        return  repository.getList();
    }

    private void initializePaging() {
        final LiveData<PagedList<UserModelClass>> source = new LivePagedListBuilder<>(userDataSourceFactory, pagedListConfig)
                .build();
        progressLoadStatus = Transformations.switchMap(userDataSourceFactory.getMutableLiveData(),
                UserDataSourceClass::getProgressLiveStatus);

        cachedUser.setValue((PagedList<UserModelClass>) null);
        cachedUser.addSource(source, userSendMessageResource -> {
            cachedUser.setValue(userSendMessageResource);
            cachedUser.removeSource(source);
        });

    }

    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    public LiveData<PagedList<UserModelClass>> getListLiveData() {
        return cachedUser;
    }
}

