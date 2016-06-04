package com.tips.android.dagger;

import com.tips.android.datasource.LocalDataSource;
import com.tips.android.storage.RealmDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robertzzy on 25/02/16.
 */

@Module(
		//Need dependencies from other modules
		complete = false,
		//Provides dependencies to other modules
		library = true
)
public class DataSourceModule {

	@Provides
	@Singleton
	LocalDataSource provideLocalDataSource(RealmDataSource realmDataSource){
		return realmDataSource;
	}
}
