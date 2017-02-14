package com.greatapps.jatinthareja.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.greatapps.jatinthareja.todo.recyler_setup.TodoAdapter;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView background;
    Button add_todo_button;
    RecyclerView recyclerView;
    RealmResults<ToDo> results;
    TodoAdapter adapter;
    Realm mRealm;
    private RealmChangeListener realmChangeListener=new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            adapter.update(results);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        results=mRealm.where(ToDo.class).findAllAsync();
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
        add_todo_button= (Button) findViewById(R.id.add_todo_btn);
        recyclerView= (RecyclerView) findViewById(R.id.recyler_list);
        background= (ImageView) findViewById(R.id.bg_imageview);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Realm.init(this);
        mRealm=Realm.getDefaultInstance();
        adapter=new TodoAdapter(this,results);
        recyclerView.setAdapter(adapter);


        Glide.with(this).load(R.drawable.background).centerCrop().into(background);
        add_todo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Todo_Dialog dialog=new Add_Todo_Dialog();
                dialog.show(getSupportFragmentManager(),"D1");
            }
        });
        
    }
}
