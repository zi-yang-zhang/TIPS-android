package com.tnt.android.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tnt.android.TNTFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertzzy on 03/03/16.
 */
public class MainTabAdapter extends FragmentPagerAdapter{
	private List<TNTFragment> mainFragmentList = new ArrayList<>();
	private List<String> mainFragmentTitleList = new ArrayList<>();
	private boolean enableTitle = false;

	public MainTabAdapter addFragment(TNTFragment fragment, String title){
		this.mainFragmentList.add(fragment);
		this.mainFragmentTitleList.add(title);
		return this;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		if(enableTitle){
			return mainFragmentTitleList.get(position);
		}else{
			return super.getPageTitle(position);
		}
	}

	public MainTabAdapter(FragmentManager fm, boolean enableTitle) {
		super(fm);
		this.enableTitle = enableTitle;
	}

	@Override
	public int getItemPosition(Object object) {
		if(object instanceof String){
		return mainFragmentTitleList.indexOf(object);
		}
		return super.getItemPosition(object);
	}

	@Override
	public Fragment getItem(int position) {
		return mainFragmentList.get(position);
	}

	@Override
	public int getCount() {
		return mainFragmentList.size();
	}
}
