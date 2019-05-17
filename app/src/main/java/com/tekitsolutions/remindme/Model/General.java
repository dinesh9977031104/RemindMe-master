package com.tekitsolutions.remindme.Model;

public class General {

    private int icon, subProviderIcon;
    private long id, subProviderId;
    private String name, bankLink, subProviderName;

    public General() {
    }

    public General(long id, int icon, long subProviderId, int subProviderIcon, String name, String subProviderName) {
        this.id = id;
        this.icon = icon;
        this.subProviderId = subProviderId;
        this.subProviderIcon = subProviderIcon;
        this.name = name;
        this.subProviderName = subProviderName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubProviderId() {
        return subProviderId;
    }

    public void setSubProviderId(long subProviderId) {
        this.subProviderId = subProviderId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public int getSubProviderIcon() {
        return subProviderIcon;
    }

    public void setSubProviderIcon(int subProviderIcon) {
        this.subProviderIcon = subProviderIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankLink() {
        return bankLink;
    }

    public void setBankLink(String bankLink) {
        this.bankLink = bankLink;
    }

    public String getSubProviderName() {
        return subProviderName;
    }

    public void setSubProviderName(String subProviderName) {
        this.subProviderName = subProviderName;
    }
}
