package com.tnt.android.dagger;

import com.tnt.android.TNTActivity;
import com.tnt.android.TNTApplication;
import com.tnt.android.TNTDialogFragment;
import com.tnt.android.TNTFragment;
import com.tnt.android.main.MainActivity;

import dagger.Module;

/**
 * Created by robertzzy on 17/02/16.
 */

@Module(
		injects={
				TNTApplication.class,
				TNTActivity.class,
				MainActivity.class,
				TNTFragment.class,
				TNTDialogFragment.class

		},
		//Need dependencies from other modules
		complete = false,
		//Provides dependencies to other modules
		library = false


)
public class AndroidModule {


}
