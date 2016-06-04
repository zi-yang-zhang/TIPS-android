package com.tips.android.main.estimate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimateResponse {
	@Expose
	@SerializedName("currency_config")
	CurrencyConfiguration currencyConfiguration;

	@Expose
	@SerializedName("hotel")
	Hotel hotel;

	@Expose
	@SerializedName("flight")
	Flight flight;


	@Expose
	@SerializedName("floating_daily_expenses")
	Double floatingDailyExpenses;

	@Expose
	@SerializedName("car_rental")
	Double carRental;

	public static class CurrencyConfiguration{
		@Expose
		@SerializedName("currency_sign")
		String sign;
		@Expose
		@SerializedName("currency_type")
		String type;
	}


	public static class Hotel{
		@Expose
		@SerializedName("name")
		String name;
		@Expose
		@SerializedName("star_rating")
		Double starRating;

		@Expose
		@SerializedName("review_rating")
		Double reviewRating;

		@Expose
		@SerializedName("price")
		Double price;
	}


	public static class Flight{
		@Expose
		@SerializedName("agency")
		String agency;
		@Expose
		@SerializedName("review_rating")
		Double reviewRating;
		@Expose
		@SerializedName("price")
		Double price;

	}
}
