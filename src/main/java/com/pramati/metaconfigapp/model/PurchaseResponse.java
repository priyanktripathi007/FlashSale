package com.pramati.metaconfigapp.model;

public class PurchaseResponse extends  ResponseModel{

    PurchaseDetail purchaseDetail;

    public PurchaseResponse(String message, PurchaseDetail purchaseDetail) {
        super(message);
        this.purchaseDetail = purchaseDetail;
    }

    public PurchaseDetail getPurchaseDetail() {
        return purchaseDetail;
    }

    public void setPurchaseDetail(PurchaseDetail purchaseDetail) {
        this.purchaseDetail = purchaseDetail;
    }
}
