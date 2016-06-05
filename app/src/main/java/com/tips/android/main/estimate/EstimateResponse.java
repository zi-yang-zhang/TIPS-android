package com.tips.android.main.estimate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangziy on 2016-06-04.
 */

//{
//		"estimate_response": {
//		"currency_config": {
//		"currency_sigh": "$",
//		"currency_type": "CAD"
//		},
//		"hotel": {
//		"name": "Hotel Arista at CityGate Centre",
//		"star_rating": null,
//		"review_rating": 4,
//		"price": 1472
//		},
//		"flight": {
//		"departure_agency": "WestJet",
//		"return_agency": "WestJet",
//		"review_rating": 4.5,
//		"departure_price": "250.65",
//		"return_price": "166.85"
//		},
//		"floating_daily_expense": 402.98967355096596,
//		"car_rental": {
//		"cost_of_car_rental": 155,
//		"car_model": "Kia Rio",
//		"thumbnail_url": null
//		},
//		"total_expense": 2447.489673550966
//		}
//		}
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
	@SerializedName("floating_daily_expense")
	Double floatingDailyExpenses;

	@Expose
	@SerializedName("car_rental")
	CarRental carRental;

	@Expose
	@SerializedName("total_expense")
	Double totalExpense;

	public static class CurrencyConfiguration{
		@Expose
		@SerializedName("currency_sign")
		String sign;
		@Expose
		@SerializedName("currency_type")
		String type;

		public String getSign() {
			return sign;
		}

		public String getType() {
			return type;
		}
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

		public String getName() {
			return name;
		}

		public Double getStarRating() {
			return starRating;
		}

		public Double getReviewRating() {
			return reviewRating;
		}

		public Double getPrice() {
			return price;
		}
	}


	public static class Flight{
		@Expose
		@SerializedName("departure_agency")
		String departureAgency;
		@Expose
		@SerializedName("departure_price")
		Double departurePrice;
		@Expose
		@SerializedName("review_rating")
		Double reviewRating;

		@Expose
		@SerializedName("return_agency")
		String returnAgency;
		@Expose
		@SerializedName("return_price")
		Double returnPrice;

		public String getDepartureAgency() {
			return departureAgency;
		}

		public Double getDeparturePrice() {
			return departurePrice;
		}

		public Double getReviewRating() {
			return reviewRating;
		}

		public String getReturnAgency() {
			return returnAgency;
		}

		public Double getReturnPrice() {
			return returnPrice;
		}
	}

	public static class CarRental{
		@Expose
		@SerializedName("cost_of_car_rental")
		Double price;
		@Expose
		@SerializedName("car_model")
		String model;

		@Expose
		@SerializedName("thumbnail_url")
		String thumbnail;

		public Double getPrice() {
			return price;
		}

		public String getModel() {
			return model;
		}

		public String getThumbnail() {
			return thumbnail;
		}
	}

	public CurrencyConfiguration getCurrencyConfiguration() {
		return currencyConfiguration;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public Flight getFlight() {
		return flight;
	}

	public Double getFloatingDailyExpenses() {
		return floatingDailyExpenses;
	}

	public CarRental getCarRental() {
		return carRental;
	}

	public Double getTotalExpense() {
		return totalExpense;
	}
}
