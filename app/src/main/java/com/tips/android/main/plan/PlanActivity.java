package com.tips.android.main.plan;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tips.android.TNTActivity;
import com.tips.android.network.EstimateManager;
import com.tnt.android.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class PlanActivity extends TNTActivity {

	@Bind(R.id.depart_flight_name)
	TextView departFlightName;
	@Bind(R.id.depart_flight_price)
	TextView departFlightPrice;
	@Bind(R.id.return_flight_name)
	TextView returnFlightName;
	@Bind(R.id.return_flight_price)
	TextView returnFlightPrice;

	@Bind(R.id.hotel_name)
	TextView hotelName;
	@Bind(R.id.hotel_price)
	TextView hotelPrice;
	@Bind(R.id.hotel_star_rating)
	TextView hotelStarRating;

	@Bind(R.id.daily_expense_estimates)
	TextView dailyExpenses;

	@Bind(R.id.total)
	TextView total;

	@Bind(R.id.difference)
	TextView difference;

	@Bind(R.id.from_date)
	TextView fromDate;

	@Bind(R.id.to_date)
	TextView toDate;

	@Bind(R.id.car_rental_check)
	CheckBox carRentalCheck;
	@Bind(R.id.car_rental_thumbnail)
	SimpleDraweeView carRentalThumbnail;

	@Bind(R.id.swipe_layout)
	SwipeRefreshLayout swipeRefreshLayout;

	@Inject
	EstimateManager manager;

	ProgressDialog progressDialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_plan);
		ButterKnife.bind(this);
		progressDialog = new ProgressDialog(this);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Updating estimates...");

	}


	@Override
	protected void onResume() {
		super.onResume();
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				Timber.d("Start refresh");
				progressDialog.show();

				manager.getEstimates().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Double>() {
					@Override
					public void call(Double aDouble) {
						Timber.d("Refreshed");
						progressDialog.dismiss();
						swipeRefreshLayout.setRefreshing(false);
						Toast.makeText(PlanActivity.this, "Estimate updated", Toast.LENGTH_SHORT).show();
						setupView();
					}
				});
			}
		});
		setupView();
		carRentalCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Double totalExpense = (double) Math.round(manager.getEstimateResponse().getTotalExpense());
				if(!isChecked){
					totalExpense = totalExpense - manager.getEstimateResponse().getCarRental().getPrice();
				}
				Double priceDifference = Math.abs(totalExpense - manager.getBudget().getBudget());

				difference.setText(priceDifference.toString().concat("$"));
				total.setText(totalExpense.toString().concat("$"));
			}
		});
	}

	private void setupView(){
		departFlightName.setText(manager.getEstimateResponse().getFlight().getDepartureAgency());
		departFlightPrice.setText(manager.getEstimateResponse().getFlight().getDeparturePrice().toString().concat("$"));
		returnFlightName.setText(manager.getEstimateResponse().getFlight().getReturnAgency());
		returnFlightPrice.setText(manager.getEstimateResponse().getFlight().getReturnPrice().toString().concat("$"));
		hotelName.setText(manager.getEstimateResponse().getHotel().getName());
		hotelPrice.setText(manager.getEstimateResponse().getHotel().getPrice().toString().concat("$"));
		hotelStarRating.setText(manager.getEstimateResponse().getHotel().getStarRating().toString());
		dailyExpenses.setText(manager.getEstimateResponse().getFloatingDailyExpenses().toString().concat("$"));
		total.setText(String.valueOf(Math.round(manager.getEstimateResponse().getTotalExpense())).concat("$"));
		Double priceDifference = (double) Math.round(Math.abs(manager.getEstimateResponse().getTotalExpense() - manager.getBudget().getBudget()));
		difference.setText(priceDifference.toString().concat("$"));
		if(manager.getEstimateResponse().getTotalExpense() - manager.getBudget().getBudget() > 0){
			difference.setTextColor(getResources().getColor(R.color.red));
		}else{
			difference.setTextColor(getResources().getColor(R.color.green));
		}

		fromDate.setText(manager.getDuration().getFromString());
		toDate.setText(manager.getDuration().getToString());
		if(!TextUtils.isEmpty(manager.getEstimateResponse().getCarRental().getThumbnail())){
			carRentalThumbnail.setAdjustViewBounds(true);
			carRentalThumbnail.setImageURI(Uri.parse(manager.getEstimateResponse().getCarRental().getThumbnail()));

		}
	}
}
