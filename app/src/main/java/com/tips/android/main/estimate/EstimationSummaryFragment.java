package com.tips.android.main.estimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tips.android.TNTFragment;
import com.tnt.android.R;

import butterknife.ButterKnife;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimationSummaryFragment extends TNTFragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.layout_summary, container, false);
		ButterKnife.bind(this, content);
		return content;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
