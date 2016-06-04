package com.tnt.android.activities;

import android.content.Intent;
import android.os.Bundle;

import com.tnt.android.R;
import com.tnt.android.TNTActivity;
import com.tnt.android.main.MainActivity;

/**
 * Created by robertzzy on 19/02/16.
 */
public class DebugActivity extends TNTActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_debug_activity);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);


	}
}
