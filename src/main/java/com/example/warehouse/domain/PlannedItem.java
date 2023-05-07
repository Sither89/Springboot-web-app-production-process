package com.example.warehouse.domain;

import jakarta.persistence.*;

@Entity
@Table (name = "planneditems")
public class PlannedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String materialnumber;
    private int plannednumber;
    private String materialdes;
    private int quantity;

    public PlannedItem(){
        super();
    }
    public PlannedItem(String materialnumber, int plannedsnumber, String materialdes, int quantity) {
        this.materialnumber = materialnumber;
        this.plannednumber = plannedsnumber;
        this.materialdes = materialdes;
        this.quantity = quantity;
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

    public int getPlannednumber() {
        return plannednumber;
    }

    public void setPlannednumber(int plannednumber) {
        this.plannednumber = plannednumber;
    }

    public String getMaterialdes() {
        return materialdes;
    }

    public void setMaterialdes(String materialdes) {
        this.materialdes = materialdes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
