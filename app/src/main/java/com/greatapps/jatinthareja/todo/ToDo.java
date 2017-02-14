package com.greatapps.jatinthareja.todo;

import io.realm.RealmObject;

/**
 * Created by JatinThareja on 12-Feb-17.
 */

public class ToDo extends RealmObject {
    public ToDo() {
    }

    public ToDo(String what, long added_time, boolean completed) {
        this.what = what;
        this.added_time = added_time;
        this.completed = completed;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public long getAdded_time() {
        return added_time;
    }

    public void setAdded_time(long added_time) {
        this.added_time = added_time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private String what;
    private long added_time;
    private boolean completed;

}
