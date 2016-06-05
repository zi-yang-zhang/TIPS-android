package com.tips.android.main.estimate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.tips.android.TNTFragment;
import com.tips.android.UIResultBus;
import com.tips.android.network.EstimateManager;
import com.tnt.android.R;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EnterDateFragment extends TNTFragment implements View.OnClickListener{

	@Bind(R.id.from_button)
	ImageView fromButton;
	@Bind(R.id.to_button)
	ImageView toButton;

	@Bind(R.id.from_date)
	TextView fromDate;
	@Bind(R.id.to_date)
	TextView toDate;
	@Inject
	UIResultBus bus;

	@Inject
	EstimateManager manager;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.layout_enter_date, container, false);
		ButterKnife.bind(this, content);
		return content;
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		fromButton.setOnClickListener(this);
		toButton.setOnClickListener(this);


	}

	@Override
	public void onResume() {
		super.onResume();
		if(manager.getDuration().fromSet){
			fromDate.setText(manager.getDuration().getFromString());
		}
		if(manager.getDuration().toSet){
			toDate.setText(manager.getDuration().getToString());
		}
	}

	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		Calendar newCalendar = Calendar.getInstance();
		switch (viewId){
			case R.id.from_button:
				DatePickerDialog from = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						manager.setDurationFrom(year,monthOfYear+1,dayOfMonth);
						fromDate.setText(manager.getDuration().getFromString());
					}
				},newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
				from.show();
				break;
			case R.id.to_button:
				DatePickerDialog to = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						manager.setDurationTo(year,monthOfYear+1,dayOfMonth);
						toDate.setText(manager.getDuration().getToString());
					}
				},newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
				to.show();
				break;
		}
	}






}
