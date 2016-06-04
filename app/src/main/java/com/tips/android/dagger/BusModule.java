package com.tips.android.dagger;

import com.tips.android.UIResultBus;

import dagger.Module;

/**
 * Created by robertzzy on 15/05/16.
 */
@Module(
		injects = {
				UIResultBus.class
		},
		complete = true,
		library = true
)
public class BusModule {
}
