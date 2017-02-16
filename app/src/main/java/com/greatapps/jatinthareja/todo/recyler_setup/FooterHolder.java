package com.greatapps.jatinthareja.todo.recyler_setup;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greatapps.jatinthareja.todo.Interfaces.ButtonListener;
import com.greatapps.jatinthareja.todo.R;

/**
 * Created by JatinThareja on 14-Feb-17.
 */

class FooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ButtonListener buttonListener;
    FooterHolder(View itemView, ButtonListener listerner) {
        super(itemView);
        Button add_button = (Button) itemView.findViewById(R.id.footer_button);
        buttonListener =listerner;
        add_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        buttonListener.add();
    }
}
