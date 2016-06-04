package com.tnt.android;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by robertzzy on 17/02/16.
 */
public class TNTFragment extends Fragment {

	private boolean firstAttach = true;
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if(firstAttach){
			((TNTApplication)context.getApplicationContext()).getApplicationGraph().inject(this);
			firstAttach = false;
		}
	}
}
