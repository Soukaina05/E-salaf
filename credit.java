package com.exemple.model;
import java.util.Date;


public class credit {
        private long credit_id;
        private long produit_id;
        private long client_id;
        private float credit_amount;
        private Date credit_date;


    public credit(long credit_id, long produit_id, long client_id) {
        this.credit_id = credit_id;
        this.produit_id = produit_id;
        this.client_id = client_id;

    }

    public long getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(long credit_id) {
        this.credit_id = credit_id;
    }

    public long getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(long produit_id) {
        this.produit_id = produit_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public float getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(float credit_amount) {
        this.credit_amount = credit_amount;
    }

    public Date getCredit_date() {
        return credit_date;
    }

    public void setCredit_date(Date credit_date) {
        this.credit_date = credit_date;
    }
}
