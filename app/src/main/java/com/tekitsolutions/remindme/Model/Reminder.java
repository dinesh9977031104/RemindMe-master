package com.tekitsolutions.remindme.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reminder {

    @SerializedName("reminderId")
    @Expose
    private long reminderId;
    @SerializedName("particularPaymentId")
    @Expose
    private long particularPaymentId;
    @SerializedName("paymentId")
    @Expose
    private long paymentId;
    @SerializedName("favorite")
    @Expose
    private Integer favoriteId;
    @SerializedName("categoryId")
    @Expose
    private long categoryId;
    @SerializedName("reminderTypeId")
    @Expose
    private long reminderTypeId;
    @SerializedName("notifyDay")
    @Expose
    private Integer notifyDay;
    @SerializedName("repeatInterval")
    @Expose
    private Integer repeatInterval;
    @SerializedName("repeatCount")
    @Expose
    private Integer repeatCount;
    @SerializedName("providerId")
    @Expose
    private long providerId;
    @SerializedName("subProviderId")
    @Expose
    private long subProviderId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("providerName")
    @Expose
    private String providerName;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("alarmTime")
    @Expose
    private String alarmTime;
    @SerializedName("repeat")
    @Expose
    private Integer repeat;
    @SerializedName("repeatIntervalType")
    @Expose
    private String repeatIntervalType;
    @SerializedName("repeatType")
    @Expose
    private String repeatType;
    @SerializedName("repeatEndDate")
    @Expose
    private String repeatEndDate;
    @SerializedName("repeatDays")
    @Expose
    private String repeatDays;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("providerNo")
    @Expose
    private String providerNo;
    @SerializedName("aliasName")
    @Expose
    private String aliasName;
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("userId")
    @Expose
    private String userId;

    private General general;

    public Reminder() {
    }

    public Reminder(long reminderId, long particularPaymentId, long paymentId, Integer favoriteId, long categoryId, long reminderTypeId, Integer notifyDay, Integer repeatInterval, Integer repeatCount, long providerId, long subProviderId, String title, String amount, String accountNo, String providerName, String dueDate, String alarmTime, Integer repeat, String repeatIntervalType, String repeatType, String repeatEndDate, String repeatDays, String notes, String createdAt, String providerNo, String aliasName, String ownerName, String userId, General general) {
        this.reminderId = reminderId;
        this.particularPaymentId = particularPaymentId;
        this.paymentId = paymentId;
        this.favoriteId = favoriteId;
        this.categoryId = categoryId;
        this.reminderTypeId = reminderTypeId;
        this.notifyDay = notifyDay;
        this.repeatInterval = repeatInterval;
        this.repeatCount = repeatCount;
        this.providerId = providerId;
        this.subProviderId = subProviderId;
        this.title = title;
        this.amount = amount;
        this.accountNo = accountNo;
        this.providerName = providerName;
        this.dueDate = dueDate;
        this.alarmTime = alarmTime;
        this.repeat = repeat;
        this.repeatIntervalType = repeatIntervalType;
        this.repeatType = repeatType;
        this.repeatEndDate = repeatEndDate;
        this.repeatDays = repeatDays;
        this.notes = notes;
        this.createdAt = createdAt;
        this.providerNo = providerNo;
        this.aliasName = aliasName;
        this.ownerName = ownerName;
        this.userId = userId;
        this.general = general;
    }

    public long getReminderId() {
        return reminderId;
    }

    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
    }

    public long getParticularPaymentId() {
        return particularPaymentId;
    }

    public void setParticularPaymentId(long particularPaymentId) {
        this.particularPaymentId = particularPaymentId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getReminderTypeId() {
        return reminderTypeId;
    }

    public void setReminderTypeId(long reminderTypeId) {
        this.reminderTypeId = reminderTypeId;
    }

    public Integer getNotifyDay() {
        return notifyDay;
    }

    public void setNotifyDay(Integer notifyDay) {
        this.notifyDay = notifyDay;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public long getSubProviderId() {
        return subProviderId;
    }

    public void setSubProviderId(long subProviderId) {
        this.subProviderId = subProviderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public String getRepeatIntervalType() {
        return repeatIntervalType;
    }

    public void setRepeatIntervalType(String repeatIntervalType) {
        this.repeatIntervalType = repeatIntervalType;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public String getRepeatEndDate() {
        return repeatEndDate;
    }

    public void setRepeatEndDate(String repeatEndDate) {
        this.repeatEndDate = repeatEndDate;
    }

    public String getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(String repeatDays) {
        this.repeatDays = repeatDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getProviderNo() {
        return providerNo;
    }

    public void setProviderNo(String providerNo) {
        this.providerNo = providerNo;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }
}