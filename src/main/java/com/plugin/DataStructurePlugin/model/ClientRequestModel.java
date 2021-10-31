package com.plugin.DataStructurePlugin.model;

public class ClientRequestModel {
    String clientName;
    String merchantName;
    OrderModel requestItem;

    public ClientRequestModel(String clientName, String merchantName, OrderModel requestItem) {
        this.clientName = clientName;
        this.merchantName = merchantName;
        this.requestItem = requestItem;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public OrderModel getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(OrderModel requestItem) {
        this.requestItem = requestItem;
    }
}
