package com.greatapps.jatinthareja.todo.recyler_setup;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greatapps.jatinthareja.todo.R;

/**
 * Created by JatinThareja on 13-Feb-17.
 */

class TodoHolder extends RecyclerView.ViewHolder {
    TextView what,when;
    TodoHolder(View itemView) {
        super(itemView);
        what= (TextView) itemView.findViewById(R.id.todo_row_whattextview);
        when= (TextView) itemView.findViewById(R.id.todo_row_whentextview);
    }
}
