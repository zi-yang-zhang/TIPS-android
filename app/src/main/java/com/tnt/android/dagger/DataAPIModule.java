package com.tnt.android.dagger;

import com.tnt.android.network.NetworkApiImpl;
import com.tnt.android.preference.UserSettingsApiImpl;
import com.tnt.android.datasource.NetworkApi;
import com.tnt.android.datasource.UserSettingsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robertzzy on 01/03/16.
 */
@Module(
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
	NetworkApi provideNetworkApi(NetworkApiImpl networkApiImpl){
		return networkApiImpl;
	}
}