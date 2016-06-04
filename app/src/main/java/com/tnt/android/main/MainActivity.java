package com.tnt.android.main;

import android.os.Bundle;

import com.tnt.android.R;
import com.tnt.android.TNTActivity;

import butterknife.ButterKnife;

/**
 * Created by robertzzy on 19/02/16.
 */
public class MainActivity extends TNTActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		ButterKnife.bind(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}


}
