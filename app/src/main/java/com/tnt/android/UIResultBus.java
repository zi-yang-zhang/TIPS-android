package com.tnt.android;

import com.jakewharton.rxrelay.PublishRelay;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by robertzzy on 15/05/16.
 */
@Singleton
public class UIResultBus {


	@Inject
	public UIResultBus(){}
	PublishRelay<Object> relay = PublishRelay.create();


	public void publish(Object object){
		Timber.d("Publish %s", object.toString());
		relay.call(object);
	}

	public Observable<Object> getBus(){
		return relay;
	}
}
