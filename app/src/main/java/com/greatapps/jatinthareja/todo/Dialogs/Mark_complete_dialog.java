package com.greatapps.jatinthareja.todo.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.greatapps.jatinthareja.todo.Interfaces.MarkCompleteListener;
import com.greatapps.jatinthareja.todo.R;

/**
 * Created by JatinThareja on 16-Feb-17.
 */

public class Mark_complete_dialog extends DialogFragment  {

    MarkCompleteListener markCompleteListener;
    ImageButton imageButton;
    Button button;
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.markdialog_checkbutton:
                    Bundle bundle=getArguments();
                    int position=bundle.getInt("Position");
                    markCompleteListener.onMarkedComplete(position);
                    break;
            }
            dismiss();
        }
    };

    public void setMarkCompletedListener(MarkCompleteListener listener){
        markCompleteListener=listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_markcomplete,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButton=(ImageButton)view.findViewById(R.id.markdialog_closebutton);
        button= (Button) view.findViewById(R.id.markdialog_checkbutton);
        imageButton.setOnClickListener(listener);
        button.setOnClickListener(listener);

    }


}
