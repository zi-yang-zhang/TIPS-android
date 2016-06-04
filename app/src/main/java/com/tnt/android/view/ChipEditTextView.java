package com.tnt.android.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.tnt.android.R;

import timber.log.Timber;

/**
 * Created by robertzzy on 25/05/16.
 */

public class ChipEditTextView extends MultiAutoCompleteTextView{

	private int chipMargin;
	private int chipTextView;
	private ChipSpan.OnChipClickedListener chipClickedListener;

	public ChipEditTextView(Context context) {
		super(context);
		init(context, null);

	}

	public ChipEditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);

	}

	public ChipEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ChipEditTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context, attrs);

	}

	public void setOnClipClickedListener(ChipSpan.OnChipClickedListener chipClickedListener){
		this.chipClickedListener = chipClickedListener;
		this.setMovementMethod(new LinkMovementMethod());
	}

	private void init(Context context, AttributeSet attrs){
		if(attrs != null){
			TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ChipEditTextView, 0, 0);

			try {
				chipMargin = attributes.getDimensionPixelSize(R.styleable.ChipEditTextView_chipMargin, context.getResources().getDimensionPixelSize(R.dimen.material_default_spacing));
				chipTextView = attributes.getResourceId(R.styleable.ChipEditTextView_chipTextViewStyle, R.layout.component_chip_view);
			} finally {
				attributes.recycle();
			}
		}
		setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

		addTextChangedListener(new TextWatcher() {
			boolean addChip = false;
			boolean removeChip = false;

			int[] toBeRemovedRange = new int[2];
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				if(removeChip){
					removeChip = false;
					return;
				}
				if(after < count){
					Timber.d("Removing %s", s.subSequence(start, start + count));
					if(s.charAt(start) == ','){
						toBeRemovedRange[1] = start;
						for (int i = start - 1; i >= 0; i--) {
							if(s.charAt(i) == ','){
								break;
							}else{
								toBeRemovedRange[0] = i;
							}
						}
						Timber.d("Should remove %s", s.subSequence(toBeRemovedRange[0], toBeRemovedRange[1]));
						removeChip = true;
					}
				}
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(addChip){
					addChip = false;
					return;
				}
				if(count > before){
					Timber.d("Adding %s", s.subSequence(start, start + count));
					if(s.charAt(start) == ',' || s.charAt(start+count-1) == ','){
						addChip = true;
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				if(removeChip){
					s.delete(toBeRemovedRange[0], toBeRemovedRange[1]);
				}
				if(addChip){
					refreshChip();
				}
			}
		});
	}

	private void refreshChip(){
		String[] chips = getText().toString().trim().split(",");
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getText());
		if(chips.length > 0){
			int startIndex = 0;
			for(String chip : chips){
				Timber.d("Adding chip: %s", chip);
				View view = LayoutInflater.from(getContext()).inflate(chipTextView, null);
				setupView(view, chip);
				Drawable space = new ColorDrawable(Color.TRANSPARENT);
				space.setBounds(0, 0, chipMargin,0);
				setupChip(spannableStringBuilder, chip, convertViewToDrawable(view),space, startIndex );
				startIndex = startIndex + chip.length() + 1;
			}
		}
		setText(spannableStringBuilder);
		setSelection(getText().length());
	}

	private BitmapDrawable convertViewToDrawable(View view) {
		int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		view.measure(spec, spec);
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		c.translate(-view.getScrollX(), -view.getScrollY());
		view.draw(c);
		view.setDrawingCacheEnabled(true);
		Bitmap cacheBmp = view.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		view.destroyDrawingCache();
		BitmapDrawable drawable = new BitmapDrawable(view.getContext().getResources(), viewBmp);
		drawable.setBounds(0, 0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
		return drawable;

	}

	public void addChip(String chip){
		if(getText().length() > 0){
			if(getText().charAt(getText().length() -1) != ','){
				append(",".concat(chip));
			}else{
				append(chip);
			}
		}else{
			setText(chip);
		}
	}


	protected void setupView(View view, String chip){
		if(view instanceof TextView){
			((TextView) view).setText(chip);
		}
	}

	protected void setupChip(SpannableStringBuilder chipSet, String chip, Drawable chipDrawable, Drawable space, int startIndex){
		ChipSpan chipSpan = new ChipSpan(chipDrawable, chip, space, startIndex, startIndex + chip.length()+1, chipClickedListener);
		chipSpan.attachSpanToSpannableString(chipSet, true);
	}


}
