package com.example.warehouse.domain;

import jakarta.persistence.*;

@Entity
@Table (name = "salesitems")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String materialnumber;
    private int salenumber;
    private int quantity;
    private String materialdes;
    private String unittype;
    private double unitprice;
    private double total;

    public SaleItem(){
        super();
    }
    public SaleItem(String materialnumber, int salenumber, int quantity, String materialdes, String unittype, double unitprice, double total) {
        this.materialnumber = materialnumber;
        this.salenumber = salenumber;
        this.quantity = quantity;
        this.materialdes = materialdes;
        this.unittype = unittype;
        this.unitprice = unitprice;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterialnumber() {
        return materialnumber;
    }

    public void setMaterialnumber(String materialnumber) {
        this.materialnumber = materialnumber;
    }

    public int getSalenumber() {
        return salenumber;
    }

    public void setSalenumber(int salenumber) {
        this.salenumber = salenumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMaterialdes() {
        return materialdes;
    }

    public void setMaterialdes(String materialdes) {
        this.materialdes = materialdes;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
