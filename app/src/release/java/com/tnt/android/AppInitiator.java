package com.tnt.android;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by robertzzy on 01/06/16.
 */
public class AppInitiator {

	public static void init(Context context){

		Fabric.with(context, new Crashlytics.Builder().core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build());
		Fresco.initialize(context);
		Timber.plant(new Timber.DebugTree());
	}
}
