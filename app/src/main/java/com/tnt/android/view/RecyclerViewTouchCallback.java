package com.tnt.android.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by robertzzy on 16/04/16.
 */
public class RecyclerViewTouchCallback extends ItemTouchHelper.Callback {

	private AdapterCallback callback;

	public RecyclerViewTouchCallback(AdapterCallback callback){
		this.callback  = callback;
	}
	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		int swipeFlag = ItemTouchHelper.RIGHT;
		return makeMovementFlags(dragFlag, swipeFlag);
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		callback.onItemMoved(recyclerView, viewHolder, target);
		return true;
	}

	@Override
	public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
		callback.onItemSwiped(viewHolder.getAdapterPosition());
	}


	public interface AdapterCallback{
		void onItemSwiped(int position);
		void onItemMoved(int from, int to);
		void onItemMoved(RecyclerView recyclerView, RecyclerView.ViewHolder from, RecyclerView.ViewHolder to);
	}
}
