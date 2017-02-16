package com.greatapps.jatinthareja.todo.recyler_setup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by JatinThareja on 14-Feb-17.
 */

public class TodoRecyclerView extends RecyclerView {
    List<View> show_when_list_empty = Collections.emptyList();
    List<View> hide_when_list_empty = Collections.emptyList();
    AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }

        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {

            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }
    };

    public TodoRecyclerView(Context context) {
        super(context);
    }

    public TodoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TodoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void toggleViews() {
        if (getAdapter() != null && !show_when_list_empty.isEmpty() && !hide_when_list_empty.isEmpty()) {
            if (getAdapter().getItemCount() == 0) {
                setVisibility(View.GONE);
                for (View view : show_when_list_empty) {
                    view.setVisibility(View.VISIBLE);
                }
                for (View view : hide_when_list_empty) {
                    view.setVisibility(View.GONE);
                }
            } else {
                setVisibility(View.VISIBLE);
                for (View view : show_when_list_empty) {
                    view.setVisibility(View.GONE);
                }
                for (View view : hide_when_list_empty) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
            observer.onChanged();
        }

    }

    public void viewstohide(View... v) {
        hide_when_list_empty = Arrays.asList(v);
    }

    public void viewstoshow(View... v) {
        show_when_list_empty = Arrays.asList(v);
    }
}
