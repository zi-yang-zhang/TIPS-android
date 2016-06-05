package com.tips.android.main.estimate;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.tips.android.network.AirportResponse;
import com.tips.android.network.EstimateManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class DestinationSuggestionAdapter extends ArrayAdapter<String>{
	private List<String> networkData = new ArrayList<>();
	private EstimateManager manager;


	public DestinationSuggestionAdapter(Context context, EstimateManager manager){
		super(context, android.R.layout.select_dialog_item);
		this.manager = manager;
		setNotifyOnChange(true);
	}

	@Override
	public Filter getFilter() {
		return new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				if(constraint != null){
					try {
						List<AirportResponse> responses = manager.getAirportCodeByNameSync(constraint.toString());

						List<String> results = new ArrayList<>(responses.size());
						for(AirportResponse airportResponse : responses){
							Timber.d("airport code found: %s", airportResponse.getTags().getIata().getAirportCode().getValue());
							StringBuilder sb= new StringBuilder();
							sb.append(airportResponse.getTags().getIata().getAirportCode().getValue())
									.append(",")
									.append(airportResponse.getName());
							results.add(sb.toString());

						}
						networkData = results;
					} catch (IOException e) {
						Timber.e(e, "Error fetching airport codes");
					}
					filterResults.values = networkData;
					filterResults.count = networkData.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				if(results != null && results.count > 0) {
					clear();
					addAll((Collection<? extends String>) results.values);
					notifyDataSetChanged();
				}
				else {
					notifyDataSetInvalidated();
				}
			}
		};
	}
}
