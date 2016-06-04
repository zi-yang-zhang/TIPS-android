package com.tnt.android.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;

/**
 * Created by robertzzy on 28/05/16.
 */
public class ChipSpan extends ClickableSpan{

	private final Object tag;
	private ImageSpan imageSpan;
	private ImageSpan spaceSpan;
	private OnChipClickedListener chipClickedListener;
	int start, end;

	public interface OnChipClickedListener{
		void onChipClicked(Object content);
	}
	public ChipSpan(Drawable d, Object tag, Drawable space, int start, int end, @Nullable OnChipClickedListener chipClickedListener) {
		this.tag = tag;
		this.imageSpan = new ImageSpan(d);
		this.chipClickedListener = chipClickedListener;
		this.spaceSpan = new ImageSpan(space);
		this.start = start;
		this.end = end;
	}


	public void attachSpanToSpannableString(SpannableStringBuilder spannableStringBuilder, boolean withSpace){
		spannableStringBuilder.setSpan(imageSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		if(chipClickedListener != null){
			spannableStringBuilder.setSpan(this, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if(start != 0 && withSpace ){
			spannableStringBuilder.setSpan(spaceSpan, start, start + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

	}

	@Override
	public void onClick(View widget) {
		chipClickedListener.onChipClicked(tag);
	}
}
