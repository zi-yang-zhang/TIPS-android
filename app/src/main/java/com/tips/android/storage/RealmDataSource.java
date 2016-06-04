package com.tips.android.storage;

import android.content.Context;

import com.tips.android.datasource.LocalDataSource;

import javax.inject.Inject;

/**
 * Created by robertzzy on 15/05/16.
 */
public class RealmDataSource implements LocalDataSource {
	@Inject
	Context context;

	@Inject
	public RealmDataSource(){}



}
