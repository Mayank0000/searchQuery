package com.example.search.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import com.example.search.db.BookMarkDB;
import com.example.search.db.model.Movie;
import com.example.search.model.Details;
import com.example.search.model.Search;
import com.example.search.model.UserModelClass;
import com.example.search.network.SearchApi;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class Repository {

    private SearchApi searchApi;
    private BookMarkDB bookMarkDB;

    @Inject
    public Repository(SearchApi searchApi,BookMarkDB bookMarkDB){
        this.searchApi = searchApi;
        this.bookMarkDB = bookMarkDB;
    }

    public Flowable<Search> getSearchResult(String apiKey,
                                            String query,int index){
        return searchApi.getSearchList(apiKey,query,index);
    }

    public void insert(Movie movie){
        Completable.fromAction(() -> bookMarkDB.daoAccess().insert(movie))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void delete(Movie movie){
        Completable.fromAction(() -> bookMarkDB.daoAccess().delete(movie))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }


    public LiveData<List<UserModelClass>> getList(){
        return LiveDataReactiveStreams.fromPublisher(bookMarkDB.daoAccess().getList()
                .onErrorReturn(throwable -> null)
                .map(user -> {
                    List<UserModelClass> list = new ArrayList<>();
                    for(int i=0;i<user.size();i++){
                        list.add( new UserModelClass(user.get(i).title,
                                user.get(i).year,user.get(i).imageUrl,user.get(i).imDbID));
                    }
                    return list;
                })
                .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<Details> queryUserId(final String id){

        return LiveDataReactiveStreams.fromPublisher(
                searchApi.getMovieDetail(Constants.API_KEY,id)
                        .onErrorReturn(throwable -> {
                            return null;
                        })
                        .map(user -> user)
                        .subscribeOn(Schedulers.io()));
    }
}
