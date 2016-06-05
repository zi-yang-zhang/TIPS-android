package com.tips.android.main.estimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.tips.android.TNTFragment;
import com.tips.android.network.EstimateManager;
import com.tnt.android.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EnterHotelFragment extends TNTFragment {

	@Bind(R.id.rating)
	RatingBar ratingBar;

	@Inject
	EstimateManager manager;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.layout_enter_hotel, container, false);
		ButterKnife.bind(this, content);
		return content;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				manager.getCurrentEstimation().hotelPreference.starRating = rating;
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();
		ratingBar.setRating(manager.getCurrentEstimation().hotelPreference.starRating);
	}

}
