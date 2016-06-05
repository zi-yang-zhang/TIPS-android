package com.tips.android.main.estimate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


	}

	@Override
	public void onResume() {
		super.onResume();
		budget.setText(String.valueOf(manager.getBudget().budget));


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
					estimate.setText(estimates.toString());
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
