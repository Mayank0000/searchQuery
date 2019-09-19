package com.example.search.ui.movielist.datasource;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import com.example.search.model.Search;
import com.example.search.util.Constants;
import com.example.search.util.Repository;
import com.example.search.model.UserModelClass;
import java.util.ArrayList;

public class UserDataSourceClass extends PageKeyedDataSource<Integer, UserModelClass> {

    private Repository repository;
    private int sourceIndex;
    private MutableLiveData<String> progressLiveStatus;
    private String query;

    public UserDataSourceClass(Repository repository,String query) {
        this.repository = repository;
        progressLiveStatus = new MutableLiveData<>();
        this.query = query;
        sourceIndex = 1;
    }


    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModelClass> callback) {

        repository.getSearchResult(Constants.API_KEY,query,sourceIndex)
                .doOnSubscribe( disposable -> {
                            progressLiveStatus.postValue(Constants.LOADING);
                        }
                )
                .subscribe((Search searchList)->{
                    progressLiveStatus.postValue(Constants.LOADED);
                    ArrayList<UserModelClass> arrayList = new ArrayList<>();
                    for (int i = 0; i < searchList.getSearch().size(); i++) {
                        arrayList.add(new UserModelClass(searchList.getSearch().get(i).getTitle(),
                                searchList.getSearch().get(i).getYear(),
                                searchList.getSearch().get(i).getPoster(),
                                searchList.getSearch().get(i).getImdbID()));
                    }
                    sourceIndex = sourceIndex + 1;
                    callback.onResult(arrayList,null,sourceIndex);

                }, throwable -> {
                    progressLiveStatus.postValue(Constants.LOADED);
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModelClass> callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModelClass> callback) {
        repository.getSearchResult(Constants.API_KEY,query,sourceIndex)
                .doOnSubscribe( disposable -> {
                            progressLiveStatus.postValue(Constants.LOADING);
                        }
                )
                .subscribe((Search searchList)->{
                    progressLiveStatus.postValue(Constants.LOADED);
                    ArrayList<UserModelClass> arrayList = new ArrayList<>();
                    for (int i = 0; i < searchList.getSearch().size(); i++) {
                        arrayList.add(new UserModelClass(searchList.getSearch().get(i).getTitle(),
                                searchList.getSearch().get(i).getYear(),
                                searchList.getSearch().get(i).getPoster(),
                                searchList.getSearch().get(i).getImdbID()));
                    }
                    sourceIndex = sourceIndex + 1;
                    callback.onResult(arrayList,sourceIndex);

                }, throwable -> {
                    progressLiveStatus.postValue(Constants.LOADED);
                });
    }

}
