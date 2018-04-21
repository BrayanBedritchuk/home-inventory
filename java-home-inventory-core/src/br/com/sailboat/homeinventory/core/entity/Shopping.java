package br.com.sailboat.homeinventory.core.entity;

import java.util.Calendar;

public class Shopping {

    private Long id;
    private Establishment establishment;
    private Calendar purchaseDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Calendar getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Calendar purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }
}
