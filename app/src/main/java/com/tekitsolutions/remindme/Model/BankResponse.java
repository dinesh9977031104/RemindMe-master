package com.tekitsolutions.remindme.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankResponse {


    @SerializedName("bankId")
    @Expose
    private long id;
    @SerializedName("bankName")
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
