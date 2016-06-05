package com.tips.android.dagger;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.tips.android.datasource.ExpediaAPI;
import com.tips.android.datasource.UserSettingsApi;
import com.tips.android.preference.UserSettingsApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by robertzzy on 01/03/16.
 */
@Module(
//		injects = {
//				GoogleApiClient.class
//		},
		//Need dependencies from other modules
		complete = false,
		//Provides dependencies to other modules
		library = true
)
public class DataAPIModule {
	@Provides
	@Singleton
	UserSettingsApi provideUserSettingsApi(UserSettingsApiImpl userSettingsApiImpl){
		return userSettingsApiImpl;
	}


	@Provides
	@Singleton
	GoogleApiClient providesGoogleApiClient(Context context){
		return new GoogleApiClient.Builder(context)
				.addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
					@Override
					public void onConnected(@Nullable Bundle bundle) {
						Timber.d("Google api connected");

					}

					@Override
					public void onConnectionSuspended(int i) {
						Timber.d("Google api suspended");

					}
				})
				.addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
					@Override
					public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
						Timber.d("Google api failed");
					}
				})
				.addApi(LocationServices.API)
				.build();

	}
	@Provides
	@Singleton
	FusedLocationProviderApi providesLocationApi(){
		return LocationServices.FusedLocationApi;
	}
	@Provides
	@Nullable
	Location providesLocation(FusedLocationProviderApi locationProviderApi, GoogleApiClient googleApiClient){
		return locationProviderApi.getLastLocation(googleApiClient);
	}


//	@Provides
//	@Singleton
//	ExpediaAPI providesExpediaAPI(){
//		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//		interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//		Retrofit retrofit = new Retrofit.Builder()
//				.client(client)
//				.baseUrl("http://terminal2.expedia.com")
//				.addConverterFactory(GsonConverterFactory.create())
//				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//				.build();
//		return retrofit.create(ExpediaAPI.class);
//	}
}