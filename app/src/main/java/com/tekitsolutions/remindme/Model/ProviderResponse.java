package com.tekitsolutions.remindme.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderResponse {

    @SerializedName("providerId")
    @Expose
    private long providerId;
    @SerializedName("categoryId")
    @Expose
    private long categoryId;
    @SerializedName("providerName")
    @Expose
    private String providerName;

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}