package com.greatapps.jatinthareja.todo.recyler_setup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greatapps.jatinthareja.todo.Interfaces.ClickListerner;
import com.greatapps.jatinthareja.todo.R;

/**
 * Created by JatinThareja on 13-Feb-17.
 */

class TodoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView what,when;
    private ClickListerner listerner;
    Context mContext;

    TodoHolder(View itemView, ClickListerner clickListerner) {
        super(itemView);
        mContext=itemView.getContext();
        listerner= clickListerner;
        itemView.setOnClickListener(this);
        what= (TextView) itemView.findViewById(R.id.todo_row_whattextview);
        when= (TextView) itemView.findViewById(R.id.todo_row_whentextview);
    }

    @Override
    public void onClick(View v) {
      listerner.onClick(getAdapterPosition());
    }

    public void setBackground(boolean completed) {
        Drawable drawable;
        if (completed){
            drawable= ContextCompat.getDrawable(mContext, R.color.row_bg_lighter);
        }
        else {
            drawable= ContextCompat.getDrawable(mContext, R.drawable.row_background_selector);
        }
        itemView.setBackground(drawable);

    }
}
