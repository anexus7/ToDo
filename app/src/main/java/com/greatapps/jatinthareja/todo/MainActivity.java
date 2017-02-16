package com.greatapps.jatinthareja.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greatapps.jatinthareja.todo.Dialogs.Add_Todo_Dialog;
import com.greatapps.jatinthareja.todo.Dialogs.Mark_complete_dialog;
import com.greatapps.jatinthareja.todo.Interfaces.ButtonListener;
import com.greatapps.jatinthareja.todo.Interfaces.ClickListerner;
import com.greatapps.jatinthareja.todo.Interfaces.MarkCompleteListener;
import com.greatapps.jatinthareja.todo.recyler_setup.CustomizedCallback;
import com.greatapps.jatinthareja.todo.recyler_setup.TodoAdapter;
import com.greatapps.jatinthareja.todo.recyler_setup.TodoRecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity  {
    String TAG="JATIN";
    Toolbar toolbar;
    ImageView background;
    Button add_todo_button;
    TodoRecyclerView recyclerView;
    RealmResults<ToDo> results;
    TodoAdapter adapter;
    Realm mRealm;
    View empty_activity_view;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addtodo_dialogsetup();
        }
    };
    private RealmChangeListener realmChangeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            if (!results.isEmpty())
                adapter.update(results);
        }
    };
    ClickListerner clickListerner = new ClickListerner() {
        @Override
        public void onClick(int position) {
            markcomplete_dialogsetup(position);
        }
    };

    private ButtonListener buttonListener = new ButtonListener() {
        @Override
        public void add() {
            addtodo_dialogsetup();
        }
    };

    private MarkCompleteListener markCompleteListener =new MarkCompleteListener() {
        @Override
        public void onMarkedComplete(int position) {
            adapter.onMarked(position);
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        results = mRealm.where(ToDo.class).findAllAsync();
        results.addChangeListener(realmChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        results.removeChangeListener(realmChangeListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Stetho.initialize(
        //         Stetho.newInitializerBuilder(this)
        //                 .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        //                 .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
        //                 .build());
        add_todo_button = (Button) findViewById(R.id.add_todo_btn);
        recyclerView = (TodoRecyclerView) findViewById(R.id.recyler_list);
        background = (ImageView) findViewById(R.id.bg_imageview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        empty_activity_view = findViewById(R.id.empty_activity);
        add_todo_button.setOnClickListener(listener);
        setSupportActionBar(toolbar);
        Glide.with(this).load(R.drawable.background).centerCrop().into(background);
        mRealm = Realm.getDefaultInstance();
        adapter = new TodoAdapter(this, results,mRealm, clickListerner);
        adapter.setAddlistener(buttonListener);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.viewstohide(toolbar);
        recyclerView.viewstoshow(empty_activity_view);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    Log.d(TAG, "onTouch: ");

                return false;
            }
        });
        CustomizedCallback callback=new CustomizedCallback(adapter);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu_add:
                Toast.makeText(this, "Add clicked", Toast.LENGTH_SHORT).show();
            break;
        }
        return true;
    }

    public void addtodo_dialogsetup() {
        Add_Todo_Dialog dialog = new Add_Todo_Dialog();
        dialog.show(getSupportFragmentManager(), "ADD");
    }
    public void markcomplete_dialogsetup(int position){
        Bundle bundle=new Bundle();
        bundle.putInt("Position",position);
        Mark_complete_dialog dialog=new Mark_complete_dialog();
        dialog.setArguments(bundle);
        dialog.setMarkCompletedListener(markCompleteListener);
        dialog.show(getSupportFragmentManager(),"MARK");
    }

}
