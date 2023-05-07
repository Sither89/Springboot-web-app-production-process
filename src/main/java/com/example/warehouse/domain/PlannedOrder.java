package com.example.warehouse.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "plannedorders")
public class PlannedOrder {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plannedsnumber;
    private int productionnumber;
    private String reqdate;
    private String reqdeliverydate;
    private String reqname;
    private String reqphone;
    private String deliverylocation;
    private String approvedby;
    private String approveddate;

    public PlannedOrder() {
        super();
    }

    public PlannedOrder(int plannedsnumber, int productionnumber, String reqdate, String reqdelivery_date, String reqname, String reqphone, String deliverylocation, String approvedby, String approveddate) {
        this.plannedsnumber = plannedsnumber;
        this.productionnumber = productionnumber;
        this.reqdate = reqdate;
        this.reqdeliverydate = reqdelivery_date;
        this.reqname = reqname;
        this.reqphone = reqphone;
        this.deliverylocation = deliverylocation;
        this.approvedby = approvedby;
        this.approveddate = approveddate;
    }

    public int getPlannedsnumber() {
        return plannedsnumber;
    }

    public void setPlannedsnumber(int plannedsnumber) {
        this.plannedsnumber = plannedsnumber;
    }

    public int getProductionnumber() {
        return productionnumber;
    }

    public void setProductionnumber(int productionnumber) {
        this.productionnumber = productionnumber;
    }

    public String getReqdate() {
        return reqdate;
    }

    public void setReqdate(String reqdate) {
        this.reqdate = reqdate;
    }

    public String getReqdeliverydate() {
        return reqdeliverydate;
    }

    public void setReqdeliverydate(String reqdeliverydate) {
        this.reqdeliverydate = reqdeliverydate;
    }

    public String getReqname() {
        return reqname;
    }

    public void setReqname(String reqname) {
        this.reqname = reqname;
    }

    public String getReqphone() {
        return reqphone;
    }

    public void setReqphone(String reqphone) {
        this.reqphone = reqphone;
    }

    public String getDeliverylocation() {
        return deliverylocation;
    }

    public void setDeliverylocation(String deliverylocation) {
        this.deliverylocation = deliverylocation;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getApproveddate() {
        return approveddate;
    }

    public void setApproveddate(String approveddate) {
        this.approveddate = approveddate;
    }
}
