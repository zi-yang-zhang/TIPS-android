package com.tips.android.main.estimate;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class Destination {
	private String airportCode;
	private String city;
	private String payload;

	public Destination(String payload){
		this.payload = payload;
		String[] result = payload.split(",");
		airportCode = result[0];
		city =result[1];

	}

	public String getAirportCode() {
		return airportCode;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return payload;
	}


}
