package com.tips.android.main.estimate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.tips.android.TNTFragment;
import com.tips.android.UIResultBus;
import com.tnt.android.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EnterDestinationFragment extends TNTFragment {
	@Bind(R.id.destination)
	TextView destination;
	@Bind(R.id.destination_field)
	AutoCompleteTextView destinationField;

	@Inject
	UIResultBus bus;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.layout_enter_destination, container, false);
		ButterKnife.bind(this, content);
		return content;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		destination.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				destinationField.setVisibility(View.VISIBLE);
				destination.setVisibility(View.GONE);
			}
		});
		destinationField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE){
					bus.publish(new Object());
					destination.setText(v.getText());
					destination.setVisibility(View.VISIBLE);
					destinationField.setVisibility(View.GONE);
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					return true;
				}
				return false;
			}
		});
	}


}
