package com.tips.android.dagger;

import com.tips.android.TNTApplication;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robertzzy on 17/02/16.
 */
public final class MainModules {

	public static List<Object> getModules(TNTApplication application){
		return Arrays
				.asList(
						new CoreModule(application),
						new AndroidModule(),
						new ManagerModule(),
						new DataSourceModule(),
						new BusModule()
				);
	}
}
