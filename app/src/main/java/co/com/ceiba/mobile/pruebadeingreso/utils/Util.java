package co.com.ceiba.mobile.pruebadeingreso.utils;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
