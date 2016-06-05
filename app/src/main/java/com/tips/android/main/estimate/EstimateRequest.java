package com.tips.android.main.estimate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimateRequest {



	@Expose
	@SerializedName("from")
	String from = "YUL";
	@Expose
	@SerializedName("to")
	String to;

	@Expose
	@SerializedName("from_date")
	String fromDate;
	@Expose
	@SerializedName("to_date")
	String toDate;
	@Expose
	@SerializedName("currency_type")
	String currencyType = "CAD";

	@Expose
	@SerializedName("hotel_preference")
	HotelPreference hotelPreference;

	@Expose
	@SerializedName("rent_car")
	Boolean rentCar;


	public static class HotelPreference{
		@Expose
		@SerializedName("star_rating")
		Float starRating;

		public HotelPreference(){
			starRating = 0F;
		}
	}

	public EstimateRequest setFrom(String from) {
		this.from = from;
		return this;
	}

	public EstimateRequest setTo(String to) {
		this.to = to;
		return this;
	}

	public EstimateRequest setFromDate(Calendar fromDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(fromDate.get(Calendar.YEAR)).append("-");
		if(fromDate.get(Calendar.MONTH) < 10){
			sb.append(0);
		}
		sb.append(fromDate.get(Calendar.MONTH)).append("-");
		if(fromDate.get(Calendar.DAY_OF_MONTH) < 10){
			sb.append(0);
		}
		sb.append(fromDate.get(Calendar.DAY_OF_MONTH));
		this.fromDate = sb.toString();
		return this;
	}

	public EstimateRequest setToDate(Calendar toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(toDate.get(Calendar.YEAR)).append("-");
		if(toDate.get(Calendar.MONTH) < 10){
			sb.append(0);
		}
		sb.append(toDate.get(Calendar.MONTH)).append("-");
		if(toDate.get(Calendar.DAY_OF_MONTH) < 10){
			sb.append(0);
		}
		sb.append(toDate.get(Calendar.DAY_OF_MONTH));
		this.toDate = sb.toString();
		return this;
	}

	public EstimateRequest setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
		return this;
	}

	public EstimateRequest setHotelPreference(HotelPreference hotelPreference) {
		this.hotelPreference = hotelPreference;
		return this;
	}

	public EstimateRequest setRentCar(Boolean rentCar) {
		this.rentCar = rentCar;
		return this;
	}

	public EstimateRequest(){
		hotelPreference = new HotelPreference();
	}
}
