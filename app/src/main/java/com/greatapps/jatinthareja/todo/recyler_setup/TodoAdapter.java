package com.greatapps.jatinthareja.todo.recyler_setup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greatapps.jatinthareja.todo.Interfaces.ButtonListener;
import com.greatapps.jatinthareja.todo.Interfaces.ClickListerner;
import com.greatapps.jatinthareja.todo.Interfaces.SwipeListerner;
import com.greatapps.jatinthareja.todo.R;
import com.greatapps.jatinthareja.todo.ToDo;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by JatinThareja on 13-Feb-17.
 */

public class TodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SwipeListerner {
    static final int ITEM = 0;
    private static final int HEADER = 1;
    private LayoutInflater minflater;
    private RealmResults<ToDo> mResults;
    private ButtonListener buttonListener;
    private Realm mRealm;
    private ClickListerner clickListerner;


    public TodoAdapter(Context context, RealmResults<ToDo> data, Realm realm, ClickListerner clickListerner) {
        mRealm = realm;
        minflater = LayoutInflater.from(context);
        mResults = data;
        notifyDataSetChanged();
        this.clickListerner = clickListerner;


    }

    public void setAddlistener(ButtonListener listerner) {
        buttonListener = listerner;
    }


    @Override
    public int getItemViewType(int position) {
        if (position < mResults.size()) {
            return ITEM;
        } else {
            return HEADER;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == HEADER) {
            v = minflater.inflate(R.layout.footer_button, parent, false);
            return new FooterHolder(v, buttonListener);
        } else {
            v = minflater.inflate(R.layout.todo_row, parent, false);
            return new TodoHolder(v, clickListerner);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mResults.size()) {
            ToDo todo = mResults.get(position);
            TodoHolder todoHolder = (TodoHolder) holder;
            todoHolder.what.setText(todo.getWhat());
            String s = "Now";
            todoHolder.when.setText(s);
            todoHolder.setBackground(todo.isCompleted());
        }
    }

    @Override
    public int getItemCount() {
        if (mResults == null || mResults.size() == 0)
            return 0;
        else
            return mResults.size() + 1;
    }

    public void update(RealmResults<ToDo> data) {
        mResults = data;
        notifyItemInserted(mResults.size() - 1);

    }


    @Override
    public void onSwipe(int position) {

        mRealm.beginTransaction();
        mResults.deleteFromRealm(position);
        mRealm.commitTransaction();
        notifyDataSetChanged();
    }

    public void onMarked(int position) {
        if (position < mResults.size()) {
            mRealm.beginTransaction();
            mResults.get(position).setCompleted(true);
            mRealm.commitTransaction();
            notifyDataSetChanged();
        }
    }
}
