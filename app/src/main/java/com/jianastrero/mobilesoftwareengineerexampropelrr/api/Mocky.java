package com.jianastrero.mobilesoftwareengineerexampropelrr.api;

import com.jianastrero.mobilesoftwareengineerexampropelrr.model.MockyResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Mocky {

    @GET("5ebb7c6c36000026def7e702")
    public Call<MockyResponse> get();
}
