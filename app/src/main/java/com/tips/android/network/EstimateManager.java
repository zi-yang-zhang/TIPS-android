package com.tips.android.network;

import com.tips.android.datasource.ExpediaAPI;
import com.tips.android.datasource.NetworkApi;
import com.tips.android.main.estimate.Budget;
import com.tips.android.main.estimate.Destination;
import com.tips.android.main.estimate.EstimateRequest;
import com.tips.android.main.estimate.EstimateResponse;
import com.tips.android.main.estimate.TravelDateAndDuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EstimateManager {


	ExpediaAPI expediaAPI;
	NetworkApi jiAPI;

	EstimateRequest currentEstimation = new EstimateRequest();
	Destination destination;

	Budget budget = new Budget(0);


	TravelDateAndDuration duration =new TravelDateAndDuration();
	EstimateResponse estimateResponse;

	public TravelDateAndDuration getDuration() {
		return duration;
	}

	public EstimateManager setDurationFrom(int year, int month, int day) {
		duration.setupFrom(year, month, day);
		currentEstimation.setFromDate(duration.getFrom());
		return this;
	}
	public EstimateManager setDurationTo(int year, int month, int day) {
		duration.setupTo(year, month, day);
		currentEstimation.setToDate(duration.getTo());
		return this;
	}

	@Inject
	public EstimateManager(){
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(0, TimeUnit.MILLISECONDS).readTimeout(0,TimeUnit.MILLISECONDS).addInterceptor(interceptor).build();

		expediaAPI =  new Retrofit.Builder()
				.client(client)
				.baseUrl("http://terminal2.expedia.com")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build().create(ExpediaAPI.class);
		jiAPI = new Retrofit.Builder()
				.client(client)
				.baseUrl("http://tips-angelhack.herokuapp.com")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build().create(NetworkApi.class);
	}


	public Observable<List<AirportResponse.Tags.IATA.AirportCode>> getAirportCode(double lat, double longt){
		Map<String, String> params = new HashMap<>();
		params.put("type", "airport");
		params.put("within", "20km");
		params.put("lng", String.valueOf(longt));
		params.put("lat", String.valueOf(lat));
		params.put("verbose", "3");
		params.put("apikey", "nN87vFttxU4RucEfGanJi3R9DwnEkATX");
		return expediaAPI.getAirportCodes(params).map(new Func1<List<AirportResponse>, List<AirportResponse.Tags.IATA.AirportCode>>() {
			@Override
			public List<AirportResponse.Tags.IATA.AirportCode> call(List<AirportResponse> airportResponses) {
				List<AirportResponse.Tags.IATA.AirportCode> airportCodes = new ArrayList<>(airportResponses.size());
				for(AirportResponse airportResponse : airportResponses){
					airportCodes.add(airportResponse.tags.iata.airportCode);

				}
				return airportCodes;
			}
		}).subscribeOn(Schedulers.io()).onErrorReturn(new Func1<Throwable, List<AirportResponse.Tags.IATA.AirportCode>>() {
			@Override
			public List<AirportResponse.Tags.IATA.AirportCode> call(Throwable throwable) {
				Timber.e(throwable, "Retrofit error");
				return Collections.emptyList();
			}
		});
	}

	public Observable<List<AirportResponse.Tags.IATA.AirportCode>> getAirportCodeByName(String name){
		Map<String, String> params = new HashMap<>();
		params.put("type", "airport");
		params.put("ln.op", "cn");
		params.put("ln.value", name);
		params.put("limit", "15");
		params.put("verbose", "3");
		params.put("apikey", "nN87vFttxU4RucEfGanJi3R9DwnEkATX");
		return expediaAPI.getAirportCodes(params).map(new Func1<List<AirportResponse>, List<AirportResponse.Tags.IATA.AirportCode>>() {
			@Override
			public List<AirportResponse.Tags.IATA.AirportCode> call(List<AirportResponse> airportResponses) {
				List<AirportResponse.Tags.IATA.AirportCode> airportCodes = new ArrayList<>(airportResponses.size());
				for(AirportResponse airportResponse : airportResponses){
					airportCodes.add(airportResponse.tags.iata.airportCode);

				}
				return airportCodes;
			}
		}).subscribeOn(Schedulers.io()).onErrorReturn(new Func1<Throwable, List<AirportResponse.Tags.IATA.AirportCode>>() {
			@Override
			public List<AirportResponse.Tags.IATA.AirportCode> call(Throwable throwable) {
				Timber.e(throwable, "Retrofit error");
				return Collections.emptyList();
			}
		});

	}


	public List<AirportResponse> getAirportCodeByNameSync(String name) throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("type", "airport");
		params.put("ln.op", "cn");
		params.put("ln.value", name);
		params.put("limit", "15");
		params.put("verbose", "3");
		params.put("apikey", "nN87vFttxU4RucEfGanJi3R9DwnEkATX");

		return expediaAPI.getAirportCodesSync(params).execute().body();

	}

	public EstimateRequest getCurrentEstimation() {
		return currentEstimation;
	}

	public Budget getBudget() {
		return budget;
	}

	public Destination getDestination() {
		return destination;
	}

	public EstimateManager setDestination(Destination destination) {
		this.destination = destination;
		currentEstimation.setTo(destination.getAirportCode());
		return this;
	}

	public Observable<Double> getEstimates(){
		return jiAPI.getEstimates(currentEstimation)
				.doOnNext(new Action1<EstimateResponse>() {
					@Override
					public void call(EstimateResponse response) {
						estimateResponse = response;
					}
				}).onErrorReturn(new Func1<Throwable, EstimateResponse>() {
			@Override
			public EstimateResponse call(Throwable throwable) {
				Timber.e(throwable, "Retrofit error");
				return null;
			}
		}).map(new Func1<EstimateResponse, Double>() {
			@Override
			public Double call(EstimateResponse estimateResponse) {
				if(estimateResponse == null) return 0.0;
				return estimateResponse.getTotalExpense();
			}
		});
	}

	public EstimateResponse getEstimateResponse() {
		return estimateResponse;
	}
}
