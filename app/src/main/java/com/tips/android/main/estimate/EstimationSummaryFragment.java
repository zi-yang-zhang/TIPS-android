package com.tips.android.main.estimate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tips.android.TNTFragment;
import com.tips.android.network.EstimateManager;
import com.tnt.android.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimationSummaryFragment extends TNTFragment {

	@Bind(R.id.estimate)
	TextView estimate;

	@Bind(R.id.budget)
	TextView budget;

	@Bind(R.id.go_to_plan)
	Button goToPlan;

	@Bind(R.id.car_rental_check)
	CheckBox carRentalCheck;

	@Inject
	EstimateManager manager;

	ProgressDialog progressDialog;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.layout_summary, container, false);
		ButterKnife.bind(this, content);
		return content;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setIndeterminate(true);
		carRentalCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(manager.getEstimateResponse() == null) return;
					double estimates = manager.getEstimateResponse().getTotalExpense();
					double newEstimates = estimates - manager.getEstimateResponse().carRental.price;
					estimate.setText(String.valueOf(newEstimates));
					if(manager.getBudget().budget > manager.getEstimateResponse().getTotalExpense()){
						estimate.setTextColor(getResources().getColor(R.color.green));
					}else{
						estimate.setTextColor(getResources().getColor(R.color.red));
					}
				}
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();
		budget.setText(String.valueOf(manager.getBudget().budget));
		goToPlan.setVisibility(View.GONE);


	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(isVisibleToUser){
			progressDialog.show();
			manager.getEstimates().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<Double>() {
				@Override
				public void call(Double estimates) {
					progressDialog.dismiss();
					estimate.setText(String.valueOf(Math.round(estimates)));
					if(estimates >0.0){
						goToPlan.setVisibility(View.VISIBLE);
					}else{
						Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
					}
					if(manager.getBudget().budget > estimates){
						estimate.setTextColor(getResources().getColor(R.color.green));
					}else{
						estimate.setTextColor(getResources().getColor(R.color.red));
					}
				}
			});
		}
	}
}
