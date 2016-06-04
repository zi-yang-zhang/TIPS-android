package com.tips.android.dagger;

import com.tips.android.network.NetworkApiImpl;
import com.tips.android.preference.UserSettingsApiImpl;
import com.tips.android.datasource.NetworkApi;
import com.tips.android.datasource.UserSettingsApi;

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