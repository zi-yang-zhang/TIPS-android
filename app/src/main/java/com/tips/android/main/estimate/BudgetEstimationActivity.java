package com.tips.android.main.estimate;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.tips.android.TNTActivity;
import com.tips.android.UIResultBus;
import com.tips.android.network.EstimateManager;
import com.tnt.android.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class BudgetEstimationActivity extends TNTActivity {

	@Bind(R.id.estimate_pager)
	ViewPager estimatePager;

	@Inject
	UIResultBus bus;

	Subscription busSubscription;

	@Inject
	GoogleApiClient googleApiClient;

	@Inject
	FusedLocationProviderApi locationAPI;

	@Inject
	EstimateManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_budget_estimation);
		ButterKnife.bind(this);
		estimatePager.setAdapter(new EstimateTabAdapter(getSupportFragmentManager()));
		estimatePager.setPageTransformer(true, new DepthPageTransformer());
	}

	@Override
	protected void onResume() {
		super.onResume();

		busSubscription = bus.getBus().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
			@Override
			public void call(Object o) {

			}
		});


	}

	@Override
	protected void onPause() {
		super.onPause();
		busSubscription.unsubscribe();
	}

	@Override
	protected void onStart() {
		super.onStart();
		googleApiClient.connect();

	}

	@Override
	protected void onStop() {
		super.onStop();
		googleApiClient.disconnect();

	}

	public static class DepthPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.75f;

		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();

			if (position < -1) { // [-Infinity,-1)
				// This page is way off-screen to the left.
				view.setAlpha(0);

			} else if (position <= 0) { // [-1,0]
				// Use the default slide transition when moving to the left page
				view.setAlpha(1);
				view.setTranslationX(0);
				view.setScaleX(1);
				view.setScaleY(1);

			} else if (position <= 1) { // (0,1]
				// Fade the page out.
				view.setAlpha(1 - position);

				// Counteract the default slide transition
				view.setTranslationX(pageWidth * -position);

				// Scale the page down (between MIN_SCALE and 1)
				float scaleFactor = MIN_SCALE
						+ (1 - MIN_SCALE) * (1 - Math.abs(position));
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

			} else { // (1,+Infinity]
				// This page is way off-screen to the right.
				view.setAlpha(0);
			}
		}
	}

	@Override
	public void onBackPressed() {
		if(estimatePager.getCurrentItem() > 0){
			estimatePager.setCurrentItem(estimatePager.getCurrentItem() - 1);
		} else{
			super.onBackPressed();
		}
	}
}
