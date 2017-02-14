package com.greatapps.jatinthareja.todo.recyler_setup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greatapps.jatinthareja.todo.R;
import com.greatapps.jatinthareja.todo.ToDo;

import io.realm.RealmResults;

/**
 * Created by JatinThareja on 13-Feb-17.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoHolder>{
    private LayoutInflater minflater;
    private RealmResults<ToDo> mResults;


    public TodoAdapter(Context context,RealmResults<ToDo> data) {
        minflater=LayoutInflater.from(context);
        update(data);

    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=minflater.inflate(R.layout.todo_row,parent,false);
        return new TodoHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        ToDo todo=mResults.get(position);
        holder.what.setText(todo.getWhat());
        String s = "Now";
        holder.when.setText(s);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void update(RealmResults<ToDo> data) {
        mResults=data;
        notifyDataSetChanged();
    }
}
