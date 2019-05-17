package com.tekitsolutions.remindme.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParticularPayment {

    @SerializedName("paymentModeId")
    @Expose
    private long particularPaymentId;
    @SerializedName("paymentNumber")
    @Expose
    private String paymentNumber;
    @SerializedName("cardValidity")
    @Expose
    private String cardValidity;
    @SerializedName("cvv")
    @Expose
    private String cvv;
    @SerializedName("aliasName")
    @Expose
    private String aliasName;
    @SerializedName("bankId")
    @Expose
    private long bankId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("agentName")
    @Expose
    private String agentName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("paymentOptionId")
    @Expose
    private long paymentId;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("paymentIcon")
    @Expose
    private Integer paymentIcon;
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("userId")
    @Expose
    private String userId;

    private General bank;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getParticularPaymentId() {
        return particularPaymentId;
    }

    public void setParticularPaymentId(long particularPaymentId) {
        this.particularPaymentId = particularPaymentId;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getCardValidity() {
        return cardValidity;
    }

    public void setCardValidity(String cardValidity) {
        this.cardValidity = cardValidity;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentIcon() {
        return paymentIcon;
    }

    public void setPaymentIcon(Integer paymentIcon) {
        this.paymentIcon = paymentIcon;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public General getBank() {
        return bank;
    }

    public void setBank(General bank) {
        this.bank = bank;
    }
}