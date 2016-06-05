package com.tips.android.datasource;

import com.tips.android.network.AirportResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhangziy on 2016-06-04.
 */
public interface ExpediaAPI {
	@GET("/x/geo/features")
	Observable<List<AirportResponse>> getAirportCodes(@QueryMap Map<String, String> options);

	@GET("/x/geo/features")
	Call<List<AirportResponse>> getAirportCodesSync(@QueryMap Map<String, String> options);
}
