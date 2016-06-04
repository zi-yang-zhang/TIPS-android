package com.tnt.android.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by robertzzy on 30/04/16.
 */
public abstract class ExpandableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	protected boolean expandable = false;
	protected boolean expanded = false;
	ExpandViewListener expandViewListener;
	protected RecyclerView.AdapterDataObserver observer;
	public ExpandableViewHolder(View itemView) {
		super(itemView);
		observer = new RecyclerView.AdapterDataObserver() {
			@Override
			public void onChanged() {
				if(expandViewListener != null && expandable){
					if(expanded) {
						expandViewListener.collapse(ExpandableViewHolder.this);
						expanded = false;
					}
				}
			}
		};
	}

	public ExpandableViewHolder setExpandable(boolean expandable){
		this.expandable = expandable;
		return this;
	}

	public ExpandableViewHolder addExpandViewListener(ExpandViewListener expandViewListener){
		this.expandViewListener = expandViewListener;
		return this;
	}

	@Override
	public void onClick(View v) {
		if(expandViewListener != null && expandable){
			if(expanded){
				expandViewListener.collapse(this);
			}else{
				expandViewListener.expand(this);
			}
			expanded = !expanded;
		}
	}

	public interface ExpandViewListener{
		void expand(ExpandableViewHolder viewHolder);
		void collapse(ExpandableViewHolder viewHolder);
	}

	public void resetState(){
		expanded = false;
		expandable = false;
	}

	public RecyclerView.AdapterDataObserver getObserver() {
		return observer;
	}
}
