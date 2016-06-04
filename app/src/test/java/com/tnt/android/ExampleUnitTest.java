package com.tnt.android;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tnt.model.domain.Gym;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
	@Test
	public void testObservable() {
		Observable<Integer> emitter = Observable.range(0,100).flatMap(new Func1<Integer, Observable<Integer>>() {
			@Override
			public Observable<Integer> call(Integer integer) {
				return Observable.just(integer).delay(3000, TimeUnit.MILLISECONDS);
			}
		});
		emitter.toBlocking().subscribe(new Action1<Integer>() {
			@Override
			public void call(Integer integer) {
				System.out.println(integer);
			}
		});
	}
	@Test
	public void testJsonObject(){
		Gym gym1=new Gym();
		gym1.setName("力美健");
		gym1.setDetail("本周瑜伽有团购活动");
		JsonObject geo=new JsonObject();
		geo.addProperty("lat",22.5638242);
		geo.addProperty("lng",114.1628496);
		gym1.setGeoLocation(geo.toString());
		System.out.println(geo.toString());
		System.out.println(new JsonParser().parse(geo.toString()));
	}
}