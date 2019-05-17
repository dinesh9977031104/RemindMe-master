package com.tekitsolutions.remindme.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubProviderResponse {
    @SerializedName("subProviderId")
    @Expose
    private long subProviderId;
    @SerializedName("subProviderName")
    @Expose
    private String subProviderName;
    @SerializedName("providerid")
    @Expose
    private long providerid;

    public long getSubProviderId() {
        return subProviderId;
    }

    public void setSubProviderId(long subProviderId) {
        this.subProviderId = subProviderId;
    }

    public String getSubProviderName() {
        return subProviderName;
    }

    public void setSubProviderName(String subProviderName) {
        this.subProviderName = subProviderName;
    }

    public long getProviderid() {
        return providerid;
    }

    public void setProviderid(long providerid) {
        this.providerid = providerid;
    }
}