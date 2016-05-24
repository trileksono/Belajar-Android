package com.example.tri.basicauthexample.service;

import com.example.tri.basicauthexample.model.Anggota;
import com.example.tri.basicauthexample.model.Buku;
import com.example.tri.basicauthexample.model.Pagging;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tri on 5/15/16.
 */
public interface API {
    @GET("/api")
    Call<Object> basicLogin();

    @GET("/api/buku")
    Call<Pagging<Buku>> getAllBuku();

    @GET("/api/buku/{query}/{key}")
    Call<Buku> getBukuByAuthor(@Path("query") String query,@Path("key") String key);

    @GET("/api/anggota")
    Call<Pagging<Anggota>> getAllAnggota();


}
