package com.tips.android.dagger;

import com.tips.android.TNTActivity;
import com.tips.android.TNTApplication;
import com.tips.android.TNTDialogFragment;
import com.tips.android.TNTFragment;
import com.tips.android.main.MainActivity;
import com.tips.android.main.estimate.BudgetEstimationActivity;
import com.tips.android.main.estimate.EnterBudgetFragment;
import com.tips.android.main.estimate.EnterDateFragment;
import com.tips.android.main.estimate.EnterDestinationFragment;
import com.tips.android.main.estimate.EnterHotelFragment;
import com.tips.android.main.estimate.EstimationSummaryFragment;
import com.tips.android.main.plan.PlanActivity;

import dagger.Module;

/**
 * Created by robertzzy on 17/02/16.
 */

@Module(
		injects={
				BudgetEstimationActivity.class,
				EnterBudgetFragment.class,
				EnterDateFragment.class,
				EnterDestinationFragment.class,
				EnterHotelFragment.class,
				EstimationSummaryFragment.class,
				PlanActivity.class,



				TNTApplication.class,
				TNTActivity.class,
				MainActivity.class,
				TNTFragment.class,
				TNTDialogFragment.class

		},
		//Need dependencies from other modules
		complete = false,
		//Provides dependencies to other modules
		library = false


)
public class AndroidModule {


}
