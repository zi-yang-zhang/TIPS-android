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

	public boolean isDateAndDurationComplete(){
		return fromSet && toSet;
	}

	public String getFromString(){
		StringBuilder sb = new StringBuilder();
		sb.append(from.get(Calendar.YEAR))
		.append("/")
		.append(from.get(Calendar.MONTH))
		.append("/")
		.append(from.get(Calendar.DAY_OF_MONTH));
		return sb.toString();
	}
	public String getToString(){
		StringBuilder sb = new StringBuilder();
		sb.append(to.get(Calendar.YEAR))
				.append("/")
				.append(to.get(Calendar.MONTH))
				.append("/")
				.append(to.get(Calendar.DAY_OF_MONTH));
		return sb.toString();
	}

	public Calendar getFrom() {
		return from;
	}

	public TravelDateAndDuration setFrom(Calendar from) {
		this.from = from;
		return this;
	}

	public Calendar getTo() {
		return to;
	}

	public TravelDateAndDuration setTo(Calendar to) {
		this.to = to;
		return this;
	}

	public boolean isFromSet() {
		return fromSet;
	}

	public TravelDateAndDuration setFromSet(boolean fromSet) {
		this.fromSet = fromSet;
		return this;
	}

	public boolean isToSet() {
		return toSet;
	}

	public TravelDateAndDuration setToSet(boolean toSet) {
		this.toSet = toSet;
		return this;
	}
}
