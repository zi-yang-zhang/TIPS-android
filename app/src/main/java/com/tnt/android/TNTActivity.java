package com.tnt.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.ObjectGraph;

/**
 * Created by robertzzy on 17/02/16.
 */
public class TNTActivity extends AppCompatActivity {
	private ObjectGraph objectGraph;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		objectGraph = ((TNTApplication)getApplication()).getApplicationGraph();
		objectGraph.inject(this);
	}

	@Override
	protected void onDestroy() {
		objectGraph = null;

		super.onDestroy();
	}

	public void inject(Object object){
		objectGraph.inject(object);
	}
}
