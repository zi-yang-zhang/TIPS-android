package com.tnt.android.dagger;

import android.content.Context;


import com.tnt.android.activities.DebugActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robertzzy on 23/02/16.
 */
@Module(
		injects = {
				DebugActivity.class

		},
		//Need dependencies from other modules
		complete = false,
		//Provides dependencies to other modules
		library = true
)
public class DebugModule {}
