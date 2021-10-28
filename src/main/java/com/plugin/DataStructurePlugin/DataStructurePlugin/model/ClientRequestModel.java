package com.plugin.DataStructurePlugin.DataStructurePlugin.model;

public class ClientRequestModel {
    String clientName;
    String requestItem;

    public ClientRequestModel(String clientName, String requestItem) {
        this.clientName = clientName;
        this.requestItem = requestItem;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(String requestItem) {
        this.requestItem = requestItem;
    }
}
