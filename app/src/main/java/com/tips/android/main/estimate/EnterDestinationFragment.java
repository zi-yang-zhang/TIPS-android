package com.tips.android.main.estimate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.tips.android.TNTFragment;
import com.tips.android.UIResultBus;
import com.tips.android.network.EstimateManager;
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

	@Inject
	GoogleApiClient googleApiClient;

	@Inject
	EstimateManager estimateManager;

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

		destinationField.setThreshold(3);
		destinationField.setAdapter(new DestinationSuggestionAdapter(getActivity(), estimateManager));
		destinationField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				destination.setText(parent.getAdapter().getItem(position).toString());
				destination.setVisibility(View.VISIBLE);
				destinationField.setVisibility(View.GONE);
				estimateManager.setDestination(new Destination(parent.getAdapter().getItem(position).toString()));
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(destinationField.getWindowToken(), 0);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		if(estimateManager.getCurrentEstimation().to == null){
			destinationField.setVisibility(View.VISIBLE);
			destination.setVisibility(View.GONE);
		}else{
			destination.setText(estimateManager.getDestination().toString());
			destination.setVisibility(View.VISIBLE);
			destinationField.setVisibility(View.GONE);
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(destinationField.getWindowToken(), 0);
		}
	}
}
