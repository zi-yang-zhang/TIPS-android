package com.tnt.android.dagger;

import dagger.Module;

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

}
