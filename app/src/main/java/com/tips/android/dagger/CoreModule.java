package com.tips.android.dagger;

import android.content.Context;

import com.tips.android.TNTApplication;

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
public class CoreModule {
	private final TNTApplication application;
	public CoreModule(TNTApplication application){
		this.application = application;
	}

	@Provides @Singleton
	Context providesContext(){
		return this.application.getApplicationContext();
	}
}
