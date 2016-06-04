package com.tnt.android;

import android.content.Context;
import android.support.v4.app.DialogFragment;

/**
 * Created by robertzzy on 15/05/16.
 */
public class TNTDialogFragment extends DialogFragment {

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
