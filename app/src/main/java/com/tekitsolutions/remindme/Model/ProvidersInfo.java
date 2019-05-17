package com.tekitsolutions.remindme.Model;

public class ProvidersInfo {
    public String mProviderEditTextPlaceHolder;
    public String mProviderHint;
    public int mInputType;
    public int mProviderIDMaxLength;
    public int mProviderIDMinLength;
    public int mProviderIDStartingDigit;

    public ProvidersInfo(String mProviderEditTextPlaceHolder, String mProviderHint, int mInputType, int mProviderIDMaxLength, int mProviderIDMinLength, int mProviderIDStatringDegit) {
        this.mProviderEditTextPlaceHolder = mProviderEditTextPlaceHolder;
        this.mProviderHint = mProviderHint;
        this.mInputType = mInputType;
        this.mProviderIDMaxLength = mProviderIDMaxLength;
        this.mProviderIDMinLength = mProviderIDMinLength;
        this.mProviderIDStartingDigit = mProviderIDStatringDegit;
    }

    public String getProviderEditTextPlaceHolder() {
        return mProviderEditTextPlaceHolder;
    }

    public void setProviderEditTextPlaceHolder(String mProviderEditTextPlaceHolder) {
        this.mProviderEditTextPlaceHolder = mProviderEditTextPlaceHolder;
    }

    public String getProviderHint() {
        return mProviderHint;
    }

    public void setProviderHint(String mProviderHint) {
        this.mProviderHint = mProviderHint;
    }

    public int getInputType() {
        return mInputType;
    }

    public void setInputType(int mInputType) {
        this.mInputType = mInputType;
    }

    public int getProviderIDMaxLength() {
        return mProviderIDMaxLength;
    }

    public void setProviderIDMaxLength(int mProviderIDMaxLength) {
        this.mProviderIDMaxLength = mProviderIDMaxLength;
    }

    public int getProviderIDMinLength() {
        return mProviderIDMinLength;
    }

    public void setProviderIDMinLength(int mProviderIDMinLength) {
        this.mProviderIDMinLength = mProviderIDMinLength;
    }

    public int getProviderIDStatringDegit() {
        return mProviderIDStartingDigit;
    }

    public void setProviderIDStatringDegit(int mProviderIDStatringDegit) {
        this.mProviderIDStartingDigit = mProviderIDStatringDegit;
    }
}
