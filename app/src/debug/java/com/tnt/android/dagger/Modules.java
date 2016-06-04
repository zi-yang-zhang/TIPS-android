package com.tnt.android.dagger;

import com.tnt.android.TNTApplication;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robertzzy on 17/02/16.
 */
public final class Modules {

	public static List<Object> getModules(TNTApplication application){
		List<Object> modules = MainModules
				.getModules(application);
		modules.add(Arrays.asList(new DebugModule()));
		return modules;
	}
}
