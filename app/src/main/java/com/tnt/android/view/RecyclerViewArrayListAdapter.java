package com.tnt.android.view;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by robertzzy on 10/04/16.
 */
public abstract class RecyclerViewArrayListAdapter<VH extends RecyclerView.ViewHolder, E> extends RecyclerView.Adapter<VH> {

	private final Object lock = new Object();
	protected ArrayList<E> list;
	private boolean notifyOnChange = true;

	public RecyclerViewArrayListAdapter(ArrayList<E> list){
		this.list = list;
	}


	public void add(E object) {
		synchronized (lock) {
			list.add(object);
		}

		if (notifyOnChange) notifyItemInserted(list.size() -1);
	}


	public void addAll(Collection<? extends E> collection) {
		synchronized (lock) {
			list.addAll(collection);
		}
		if (notifyOnChange) notifyItemRangeInserted(list.size() - collection.size() -1, collection.size() );
	}
	public void replaceAll(Collection<? extends E> collection) {
		synchronized (lock) {
			list.clear();
			list.addAll(collection);
		}
		if (notifyOnChange) notifyDataSetChanged();
	}

	public void addAll(E ... items) {
		synchronized (lock) {
			Collections.addAll(list, items);
		}
		if (notifyOnChange) notifyDataSetChanged();
	}

	public void insert(E object, int index) {
		synchronized (lock) {
			list.add(index, object);
		}
		if (notifyOnChange) notifyItemInserted(index);
	}

	public void remove(E object) {
		int locationIndex = list.indexOf(object);
		synchronized (lock) {
			list.remove(object);
		}
		if (notifyOnChange) notifyItemRemoved(locationIndex);
	}

	public void removeAll(Collection<E> object){
		synchronized (lock) {
			list.removeAll(object);
		}
		if (notifyOnChange) notifyDataSetChanged();
	}
	public void remove(int index){
		synchronized (lock) {
			list.remove(index);
		}
		if (notifyOnChange) notifyItemRemoved(index);
	}

	public void clear() {
		synchronized (lock) {
			list.clear();
		}
		if (notifyOnChange) notifyDataSetChanged();
	}

	public void sort(Comparator<? super E> comparator) {
		synchronized (lock) {
			Collections.sort(list, comparator);

		}
		if (notifyOnChange) notifyDataSetChanged();
	}




	public void setNotifyOnDataSetChanged(boolean notifyOnChange){
		this.notifyOnChange = notifyOnChange;
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	public E getItem(int position){
		return list.get(position);
	}
}
