package org.jeonfeel.http_practice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface upbitService {

    @GET("https://api.upbit.com/v1/ticker")
    Call<List<Model>> getCoin(@Query("markets") String coin);

}
