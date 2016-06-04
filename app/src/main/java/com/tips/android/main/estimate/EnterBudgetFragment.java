package com.tips.android.main.estimate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.tips.android.TNTFragment;
import com.tips.android.UIResultBus;
import com.tnt.android.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangziy on 2016-06-04.
 */
public class EnterBudgetFragment extends TNTFragment {

	@Bind(R.id.enter_budge_field)
	EditText enterBudgeField;

	@Bind(R.id.budget)
	TextView budget;

	@Inject
	UIResultBus bus;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.layout_enter_budget, container, false);
		ButterKnife.bind(this, content);
		return content;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		budget.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				enterBudgeField.setVisibility(View.VISIBLE);
				budget.setVisibility(View.GONE);
			}
		});
		enterBudgeField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE){
					bus.publish(new Budget(Double.valueOf(v.getText().toString())));
					budget.setText(v.getText());
					budget.setVisibility(View.VISIBLE);
					enterBudgeField.setVisibility(View.GONE);
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
