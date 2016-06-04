package com.tips.android.main.estimate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimateRequest {
	@Expose
	@SerializedName("from")
	String from;
	@Expose
	@SerializedName("to")
	String to;

	@Expose
	@SerializedName("from_date")
	Date fromDate;
	@Expose
	@SerializedName("to_date")
	Date toDate;
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
		Integer starRating;
	}

}
