package com.tnt.android;


import android.app.Application;

import com.tnt.android.dagger.Modules;

import dagger.ObjectGraph;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by robertzzy on 06/02/16.
 */
public class TNTApplication extends Application {
	private ObjectGraph applicationGraph;

	private RealmConfiguration appConfig;


	@Override
	public void onCreate() {
		super.onCreate();
		AppInitiator.init(this);
		applicationGraph = ObjectGraph.create(Modules.getModules(this).toArray());
		applicationGraph.inject(this);
		appConfig = new RealmConfiguration.Builder(this).name(BuildConfig.REALM_FILE_NAME).schemaVersion(BuildConfig.REALM_VERSION).build();

	}

	public Realm getAppRealm(){
		return Realm.getInstance(appConfig);
	}

	public ObjectGraph getApplicationGraph(){
		return applicationGraph;
	}
}
