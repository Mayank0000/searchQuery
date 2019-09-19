package com.example.search.di;

import android.app.Application;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.search.R;
import com.example.search.db.BookMarkDB;
import com.example.search.di.search.SearchScope;
import com.example.search.network.SearchApi;
import com.example.search.util.Constants;
import com.example.search.util.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


// If we are  making different classes for interface then we should shift this to activity module class
    @Singleton
    @Provides
    static SearchApi provideSessionApi(Retrofit retrofit){
        return retrofit.create(SearchApi.class);
    }

    @Singleton
    @Provides
    static Repository getRepositoryInstance(SearchApi searchApi,BookMarkDB bookMarkDB){
        return new Repository(searchApi,bookMarkDB);
    }

    @Singleton
    @Provides
    static BookMarkDB provideDb(Application application){
        return Room.databaseBuilder(application,
                BookMarkDB.class, "register_user").build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }


}
