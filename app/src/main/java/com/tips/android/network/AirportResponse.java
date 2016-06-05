package com.tips.android.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class AirportResponse {
	@Expose
	@SerializedName("tags")
	Tags tags;

	@Expose
	@SerializedName("name")
	String name;

	public String getName() {
		return name;
	}

	public Tags getTags() {
		return tags;
	}

	public static class Tags{
		@Expose
		@SerializedName("iata")
		IATA iata;
		public static class IATA{
			@Expose
			@SerializedName("airportCode")
			AirportCode airportCode;
			public static class AirportCode{
				@Expose
				@SerializedName("id")
				Long id;
				@Expose
				@SerializedName("value")
				String value;

				public Long getId() {
					return id;
				}

				public String getValue() {
					return value;
				}
			}

			public AirportCode getAirportCode() {
				return airportCode;
			}
		}


		public IATA getIata() {
			return iata;
		}
	}

}
