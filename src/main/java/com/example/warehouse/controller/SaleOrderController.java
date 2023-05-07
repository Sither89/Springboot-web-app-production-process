package com.example.warehouse.controller;

import com.example.warehouse.broker.TopicProducer;
import com.example.warehouse.domain.SaleItem;
import com.example.warehouse.domain.SaleOrder;
import com.example.warehouse.repository.SaleItemRepository;
import com.example.warehouse.repository.SaleOrderRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SaleOrder")
@RequiredArgsConstructor
public class SaleOrderController {

    @Autowired
    private SaleOrderRepository saleOrderRepository;
    @Autowired
    private SaleItemRepository saleItemRepository;

    public static class SaleOrderItem {
        private int salesnumber;
        private String bill_to;
        private String ship_to;
        private int customer_po;
        private String order_date;
        private String req_deli_date;
        private String shipped_via;
        private String FOB_point;
        private String terms;
        private double sub_total;
        private String sales_tax;
        private String shipping_handling;
        private String other;
        private double order_total;
        private String status;
        private List<SaleItem> saleItems;

        public SaleOrderItem(){
            super();
        };

        public SaleOrderItem(int salesnumber, String bill_to, String ship_to, int customer_po, String order_date, String req_deli_date, String shipped_via, String FOB_point, String terms, double sub_total, String sales_tax, String shipping_handling, String other, double order_total, String status, List<SaleItem> saleItems) {
            this.salesnumber = salesnumber;
            this.bill_to = bill_to;
            this.ship_to = ship_to;
            this.customer_po = customer_po;
            this.order_date = order_date;
            this.req_deli_date = req_deli_date;
            this.shipped_via = shipped_via;
            this.FOB_point = FOB_point;
            this.terms = terms;
            this.sub_total = sub_total;
            this.sales_tax = sales_tax;
            this.shipping_handling = shipping_handling;
            this.other = other;
            this.order_total = order_total;
            this.status = status;
            this.saleItems = saleItems;
        }

        public int getSalesnumber() {
            return salesnumber;
        }

        public void setSalesnumber(int salesnumber) {
            this.salesnumber = salesnumber;
        }

        public String getBill_to() {
            return bill_to;
        }

        public void setBill_to(String bill_to) {
            this.bill_to = bill_to;
        }

        public String getShip_to() {
            return ship_to;
        }

        public void setShip_to(String ship_to) {
            this.ship_to = ship_to;
        }

        public int getCustomer_po() {
            return customer_po;
        }

        public void setCustomer_po(int customer_po) {
            this.customer_po = customer_po;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getReq_deli_date() {
            return req_deli_date;
        }

        public void setReq_deli_date(String req_deli_date) {
            this.req_deli_date = req_deli_date;
        }

        public String getShipped_via() {
            return shipped_via;
        }

        public void setShipped_via(String shipped_via) {
            this.shipped_via = shipped_via;
        }

        public String getFOB_point() {
            return FOB_point;
        }

        public void setFOB_point(String FOB_point) {
            this.FOB_point = FOB_point;
        }

        public String getTerms() {
            return terms;
        }

        public void setTerms(String terms) {
            this.terms = terms;
        }

        public double getSub_total() {
            return sub_total;
        }

        public void setSub_total(double sub_total) {
            this.sub_total = sub_total;
        }

        public String getSales_tax() {
            return sales_tax;
        }

        public void setSales_tax(String sales_tax) {
            this.sales_tax = sales_tax;
        }

        public String getShipping_handling() {
            return shipping_handling;
        }

        public void setShipping_handling(String shipping_handling) {
            this.shipping_handling = shipping_handling;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public double getOrder_total() {
            return order_total;
        }

        public void setOrder_total(double order_total) {
            this.order_total = order_total;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<SaleItem> getSaleItemsArr() {
            return saleItems;
        }

        public void setSaleItems(List<SaleItem> saleItems) {
            this.saleItems = saleItems;
        }

        public SaleItem getSaleItems(int i) {
            return saleItems.get(i);
        }

        public int getSaleItemsLength() {
            return saleItems.size();
        }
    }

    //kafka
    private final TopicProducer topicProducer;

    @PostMapping(path = "/addSaleOrder")
    public @ResponseBody String addSaleOrder(@RequestBody String content){
//        System.out.println(content);
        Gson gson = new Gson();
        SaleOrderItem saleOrderItem = gson.fromJson(content, SaleOrderItem.class);
//        System.out.println(saleOrderItem.getSalesnumber());
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setSalesnumber(saleOrderItem.getSalesnumber());
        saleOrder.setBillto(saleOrderItem.getBill_to());
        saleOrder.setShipto(saleOrderItem.getShip_to());
        saleOrder.setCustomerpo(saleOrderItem.getCustomer_po());
        saleOrder.setOrderdate(saleOrderItem.getOrder_date());
        saleOrder.setReqdelidate(saleOrderItem.getReq_deli_date());
        saleOrder.setShippedvia(saleOrderItem.getShipped_via());
        saleOrder.setFOBpoint(saleOrderItem.getFOB_point());
        saleOrder.setTerms(saleOrderItem.getTerms());
        saleOrder.setSubtotal(saleOrderItem.getSub_total());
        saleOrder.setSalestax(saleOrderItem.getSales_tax());
        saleOrder.setShippinghandling(saleOrderItem.getShipping_handling());
        saleOrder.setOther(saleOrderItem.getOther());
        saleOrder.setOrdertotal(saleOrderItem.getOrder_total());
        saleOrder.setStatus(saleOrderItem.getStatus());
        saleOrderRepository.save(saleOrder);
//        System.out.println(saleOrderItem.getSaleItemsLength());
        for (int i = 0; i < saleOrderItem.getSaleItemsLength(); i++) {
            SaleItem saleItem = saleOrderItem.getSaleItems(i);
            saleItemRepository.save(saleItem);
        }
        topicProducer.send("Added SaleOrder");
        return "Added SaleOrder";
    }
    @GetMapping(path = "/getAllSaleOrder")
    public @ResponseBody List<SaleOrderItem> getSaleOrder(){
        List<SaleOrder> saleOrderList = saleOrderRepository.findAll();
        List<SaleOrderItem> saleOrderItemsList = new ArrayList<>();
        for (int i = 0; i < saleOrderList.size(); i++) {
            SaleOrderItem saleOrderItem =  new SaleOrderItem();
            saleOrderItem.setSalesnumber(saleOrderList.get(i).getSalesnumber());
            saleOrderItem.setBill_to(saleOrderList.get(i).getBillto());
            saleOrderItem.setShip_to(saleOrderList.get(i).getShipto());
            saleOrderItem.setCustomer_po(saleOrderList.get(i).getCustomerpo());
            saleOrderItem.setOrder_date(saleOrderList.get(i).getOrderdate());
            saleOrderItem.setReq_deli_date(saleOrderList.get(i).getReqdelidate());
            saleOrderItem.setShipped_via(saleOrderList.get(i).getShippedvia());
            saleOrderItem.setFOB_point(saleOrderList.get(i).getFOBpoint());
            saleOrderItem.setTerms(saleOrderList.get(i).getTerms());
            saleOrderItem.setSub_total(saleOrderList.get(i).getSubtotal());
            saleOrderItem.setSales_tax(saleOrderList.get(i).getSalestax());
            saleOrderItem.setShipping_handling(saleOrderList.get(i).getShippinghandling());
            saleOrderItem.setOther(saleOrderList.get(i).getOther());
            saleOrderItem.setOrder_total(saleOrderList.get(i).getOrdertotal());
            saleOrderItem.setStatus(saleOrderList.get(i).getStatus());
            List<SaleItem> saleItemList = saleItemRepository.findBysalenumber(saleOrderItem.getSalesnumber());
            saleOrderItem.setSaleItems(saleItemList);
            saleOrderItemsList.add(saleOrderItem);
        }
        String plannedOrderItemListStr = new Gson().toJson(saleOrderItemsList);
        topicProducer.send(plannedOrderItemListStr);
        return saleOrderItemsList;
    }
    @GetMapping(path = "/searchBySalesnumber")
    public @ResponseBody List<SaleOrderItem> searchBySalesnumber(@RequestParam int salesnumber){
        List<SaleOrder> saleOrderList = saleOrderRepository.findBysalesnumber(salesnumber);
        List<SaleOrderItem> saleOrderItemsList = new ArrayList<>();
        for (int i = 0; i < saleOrderList.size(); i++) {
            SaleOrderItem saleOrderItem =  new SaleOrderItem();
            saleOrderItem.setSalesnumber(saleOrderList.get(i).getSalesnumber());
            saleOrderItem.setBill_to(saleOrderList.get(i).getBillto());
            saleOrderItem.setShip_to(saleOrderList.get(i).getShipto());
            saleOrderItem.setCustomer_po(saleOrderList.get(i).getCustomerpo());
            saleOrderItem.setOrder_date(saleOrderList.get(i).getOrderdate());
            saleOrderItem.setReq_deli_date(saleOrderList.get(i).getReqdelidate());
            saleOrderItem.setShipped_via(saleOrderList.get(i).getShippedvia());
            saleOrderItem.setFOB_point(saleOrderList.get(i).getFOBpoint());
            saleOrderItem.setTerms(saleOrderList.get(i).getTerms());
            saleOrderItem.setSub_total(saleOrderList.get(i).getSubtotal());
            saleOrderItem.setSales_tax(saleOrderList.get(i).getSalestax());
            saleOrderItem.setShipping_handling(saleOrderList.get(i).getShippinghandling());
            saleOrderItem.setOther(saleOrderList.get(i).getOther());
            saleOrderItem.setOrder_total(saleOrderList.get(i).getOrdertotal());
            saleOrderItem.setStatus(saleOrderList.get(i).getStatus());
            List<SaleItem> saleItemList = saleItemRepository.findBysalenumber(saleOrderItem.getSalesnumber());
            saleOrderItem.setSaleItems(saleItemList);
            saleOrderItemsList.add(saleOrderItem);
        }
        String plannedOrderItemListStr = new Gson().toJson(saleOrderItemsList);
        topicProducer.send(plannedOrderItemListStr);
        return saleOrderItemsList;
    }
    @GetMapping(path = "/getAllSaleItem")
    public @ResponseBody List<SaleItem> getAllSaleItem(){
        List<SaleItem> saleItemList = saleItemRepository.findAll();
        String saleItemListStr = new Gson().toJson(saleItemList);
        topicProducer.send(saleItemListStr);
        return saleItemList;
    }
    @GetMapping(path = "/searchItemBySalesnumber")
    public  @ResponseBody List<SaleItem> searchSaleItemBySalesnumber(@RequestParam int salesnumber){
        List<SaleItem> saleItemList = saleItemRepository.findBysalenumber(salesnumber);
        String saleItemListStr = new Gson().toJson(saleItemList);
        topicProducer.send(saleItemListStr);
        return saleItemList;
    }
}
