package com.example.warehouse.domain;

import jakarta.persistence.*;

@Entity
@Table (name = "salesorders")
public class SaleOrder {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesnumber;
    private String billto;
    private String shipto;
    private int customerpo;
    private String orderdate;
    private String reqdelidate;
    private String shippedvia;
    private String FOBpoint;
    private String terms;
    private double subtotal;
    private String salestax;
    private String shippinghandling;
    private String other;
    private double ordertotal;
    private String status;

    public SaleOrder(){
        super();
    }
    public SaleOrder(int salesnumber ,String bill_to, String ship_to, int customer_po, String order_date, String req_deli_date, String shipped_via, String FOB_point, String terms, double sub_total, String sales_tax, String shipping_handling, String other, double order_total, String status) {
        this.salesnumber = salesnumber;
        this.billto = bill_to;
        this.shipto = ship_to;
        this.customerpo = customer_po;
        this.orderdate = order_date;
        this.reqdelidate = req_deli_date;
        this.shippedvia = shipped_via;
        this.FOBpoint = FOB_point;
        this.terms = terms;
        this.subtotal = sub_total;
        this.salestax = sales_tax;
        this.shippinghandling = shipping_handling;
        this.other = other;
        this.ordertotal = order_total;
        this.status = status;
    }

    public int getSalesnumber() {
        return salesnumber;
    }

    public void setSalesnumber(int salesnumber) {
        this.salesnumber = salesnumber;
    }

    public String getBillto() {
        return billto;
    }

    public void setBillto(String billto) {
        this.billto = billto;
    }

    public String getShipto() {
        return shipto;
    }

    public void setShipto(String shipto) {
        this.shipto = shipto;
    }

    public int getCustomerpo() {
        return customerpo;
    }

    public void setCustomerpo(int customerpo) {
        this.customerpo = customerpo;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getReqdelidate() {
        return reqdelidate;
    }

    public void setReqdelidate(String reqdelidate) {
        this.reqdelidate = reqdelidate;
    }

    public String getShippedvia() {
        return shippedvia;
    }

    public void setShippedvia(String shippedvia) {
        this.shippedvia = shippedvia;
    }

    public String getFOBpoint() {
        return FOBpoint;
    }

    public void setFOBpoint(String FOBpoint) {
        this.FOBpoint = FOBpoint;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getSalestax() {
        return salestax;
    }

    public void setSalestax(String salestax) {
        this.salestax = salestax;
    }

    public String getShippinghandling() {
        return shippinghandling;
    }

    public void setShippinghandling(String shippinghandling) {
        this.shippinghandling = shippinghandling;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public double getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(double ordertotal) {
        this.ordertotal = ordertotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
