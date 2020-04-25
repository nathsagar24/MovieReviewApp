package com.example.rockstarmoviereview;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitAPIProvider {
    private static  tmdbAPI api;
    private static final String BASE_URL="https://api.themoviedb.org/3/";
    private static Retrofit retrofit;

    public static tmdbAPI getRetrofitAPI(){
        if(api!=null)return api;
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api=retrofit.create(tmdbAPI.class);
        return api;
    }

}
