package com.tnt.android;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Created by robertzzy on 01/06/16.
 */
public class AppInitiator {

	public static void init(Context context){
		Stetho.initialize(
				Stetho.newInitializerBuilder(context)
						.enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
						.enableWebKitInspector(RealmInspectorModulesProvider.builder(context).databaseNamePattern(Pattern.compile(".+\\.realm")).build())
						.build());
		Fresco.initialize(context);
		Timber.plant(new Timber.DebugTree());
	}
}
