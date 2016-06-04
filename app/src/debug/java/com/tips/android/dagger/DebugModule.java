package com.tips.android.dagger;


import com.tips.android.DebugActivity;

import dagger.Module;

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
