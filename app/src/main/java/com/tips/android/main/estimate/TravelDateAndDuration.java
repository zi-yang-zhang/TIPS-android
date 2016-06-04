package com.tips.android.main.estimate;

import java.util.Calendar;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class TravelDateAndDuration {

	Calendar from = Calendar.getInstance();
	Calendar to = Calendar.getInstance();

	boolean fromSet = false;
	boolean toSet = false;


	public TravelDateAndDuration setupFrom(int year, int month, int day){
		from.set(year, month, day);
		fromSet = true;
		return this;
	}
	public TravelDateAndDuration setupTo(int year, int month, int day){
		to.set(year, month, day);
		toSet = true;
		return this;
	}

	public boolean isDateAndDurationCompelete(){
		return fromSet && toSet;
	}
}
