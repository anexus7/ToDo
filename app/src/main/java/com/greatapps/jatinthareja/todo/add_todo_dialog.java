package com.greatapps.jatinthareja.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import io.realm.Realm;

/**
 * Created by JatinThareja on 12-Feb-17.
 */

public class Add_Todo_Dialog extends DialogFragment {
    ImageButton dialog_close_button;
    Button add_button;
    EditText what_edittext;
    private View.OnClickListener btn_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            if (id==R.id.dialog_addit_button){
                add_todo();

            }
            dismiss();

        }
    };
//TODO process date
    private void add_todo() {
        
        String what=what_edittext.getText().toString();
        long time=System.currentTimeMillis();
        ToDo todo=new ToDo(what,time,false);
        Realm.init(getContext());
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(todo);
        realm.commitTransaction();
        realm.close();
    }

    public Add_Todo_Dialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_drop_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        what_edittext= (EditText) view.findViewById(R.id.dialog_title_edittext);
        add_button= (Button) view.findViewById(R.id.dialog_addit_button);
        dialog_close_button= (ImageButton) view.findViewById(R.id.dialog_close_button);
        dialog_close_button.setOnClickListener(btn_click_listener);
        add_button.setOnClickListener(btn_click_listener);
    }
}
