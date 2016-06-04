package com.tnt.android.network;

import com.tnt.android.BuildConfig;
import com.tnt.android.datasource.NetworkApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robertzzy on 01/03/16.
 */
@Singleton
public class NetworkApiImpl implements NetworkApi {
	Endpoints endpoints;
	@Inject
	public NetworkApiImpl(){
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.ENDPOINT).addConverterFactory(GsonConverterFactory.create()).build();
		endpoints = retrofit.create(Endpoints.class);
	}

}
