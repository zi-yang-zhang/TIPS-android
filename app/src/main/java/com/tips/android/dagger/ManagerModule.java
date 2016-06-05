package com.tips.android.dagger;

import com.tips.android.network.EstimateManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robertzzy on 17/02/16.
 */

@Module(
		//Need dependencies from other modules
		complete = false,
		//Provides dependencies to other modules
		library = true
)
public class ManagerModule {

	@Provides
	@Singleton
	EstimateManager providesEstimateManager(){
		return new EstimateManager();
	}

}
