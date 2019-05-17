package com.tekitsolutions.remindme.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReminderSpinnerListResponse {


    @SerializedName("reminderId")
    @Expose
    private long id;
    @SerializedName("reminderTypeName")
    @Expose
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
