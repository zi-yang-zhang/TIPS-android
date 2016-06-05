package com.tips.android.datasource;

import com.tips.android.main.estimate.EstimateRequest;
import com.tips.android.main.estimate.EstimateResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by robertzzy on 01/03/16.
 */
public interface NetworkApi {

	@POST("/estimate")
	Observable<EstimateResponse> getEstimates(@Body EstimateRequest request);

}
