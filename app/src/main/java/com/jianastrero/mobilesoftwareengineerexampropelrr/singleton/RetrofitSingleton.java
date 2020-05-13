package com.jianastrero.mobilesoftwareengineerexampropelrr.singleton;

import com.jianastrero.mobilesoftwareengineerexampropelrr.api.Mocky;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.mocky.io/v2/")
        .build();

    public static Mocky getMocky() {
        return retrofit.create(Mocky.class);
    }
}
