package com.greatapps.jatinthareja.todo.recyler_setup;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.greatapps.jatinthareja.todo.Interfaces.SwipeListerner;

/**
 * Created by JatinThareja on 15-Feb-17.
 */

public class CustomizedCallback extends ItemTouchHelper.Callback {
    private SwipeListerner mlisterner;

    public CustomizedCallback(SwipeListerner listerner) {
        mlisterner = listerner;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.END | ItemTouchHelper.START);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (viewHolder.getItemViewType()==TodoAdapter.ITEM)
        mlisterner.onSwipe(viewHolder.getAdapterPosition());
    }
}
