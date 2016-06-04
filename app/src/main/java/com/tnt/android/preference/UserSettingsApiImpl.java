package com.tnt.android.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.tnt.android.datasource.UserSettingsApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robertzzy on 01/03/16.
 */
@Singleton
public class UserSettingsApiImpl implements UserSettingsApi {
	private SharedPreferences sharedPreferences;
	private static final String USER_PREF = "user.pref";
	@Inject
	public UserSettingsApiImpl(Context context){
		sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
	}
}
