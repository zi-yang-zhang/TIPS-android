package com.tips.android.main.estimate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimateTabAdapter extends FragmentPagerAdapter {

	public EstimateTabAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position){
			case 0:
				return new EnterBudgetFragment();
			case 1:
				return new EnterDestinationFragment();
			case 2:
				return new EnterDateFragment();
			case 3:
				return new EnterHotelFragment();
			case 4:
				return new EstimationSummaryFragment();
		}
		return new Fragment();
	}

	@Override
	public int getCount() {
		return 5;
	}
}
