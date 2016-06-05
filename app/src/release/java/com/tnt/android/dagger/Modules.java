package com.tips.android.dagger;

import com.tnt.android.TNTApplication;

import java.util.List;

/**
 * Created by robertzzy on 17/02/16.
 */
public final class Modules {

	public static List<Object> getModules(TNTApplication application){
		List<Object> modules = MainModules
				.getModules(application);
		return modules;
	}
}
