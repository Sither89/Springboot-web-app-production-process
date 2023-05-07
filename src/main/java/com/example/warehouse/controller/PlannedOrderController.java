package com.example.warehouse.controller;

import com.example.warehouse.broker.TopicProducer;
import com.example.warehouse.domain.PlannedItem;
import com.example.warehouse.domain.PlannedOrder;
import com.example.warehouse.repository.PlannedItemRopository;
import com.example.warehouse.repository.PlannedOrderRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/PlannedOrder")
@RequiredArgsConstructor
public class PlannedOrderController {
    @Autowired
    private PlannedOrderRepository plannedOrderRepository;
    @Autowired
    private PlannedItemRopository plannedItemRopository;

    public static class PlannedOrderItem{
        private int plannedsnumber;
        private int productionnumber;
        private String reqdate;
        private String reqdeliverydate;
        private String reqname;
        private String reqphone;
        private String deliverylocation;
        private String approvedby;
        private String approveddate;
        private List<PlannedItem> plannedItems;

        public PlannedOrderItem(){
            super();
        }

        public PlannedOrderItem(int plannedsnumber ,int productionnumber, String reqdate, String reqdeliveryDate, String reqname, String reqphone, String deliverylocation, String approvedby, String approveddate, List<PlannedItem> plannedItems) {
            this.plannedsnumber = plannedsnumber;
            this.productionnumber = productionnumber;
            this.reqdate = reqdate;
            this.reqdeliverydate = reqdeliveryDate;
            this.reqname = reqname;
            this.reqphone = reqphone;
            this.deliverylocation = deliverylocation;
            this.approvedby = approvedby;
            this.approveddate = approveddate;
            this.plannedItems = plannedItems;
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

        public List<PlannedItem> getPlannedItemsArr() {
            return plannedItems;
        }
        public PlannedItem getPlannedItems(int i) {
            return plannedItems.get(i);
        }

        public void setPlannedItems(List<PlannedItem> plannedItems) {
            this.plannedItems = plannedItems;
        }

        public int getPlannedItemsLength(){
            return plannedItems.size();
        }
    }
    //kafka
    private final TopicProducer topicProducer;
    @PostMapping(path = "/createPlannedOrder")
    public @ResponseBody String createPlannedOrder(@RequestBody String content) {
        Gson gson = new Gson();
        PlannedOrderItem plannedOrderItem = gson.fromJson(content , PlannedOrderItem.class);
        PlannedOrder plannedOrder = new PlannedOrder();
        plannedOrder.setPlannedsnumber(plannedOrderItem.getPlannedsnumber());
        plannedOrder.setProductionnumber(plannedOrderItem.getProductionnumber());
        plannedOrder.setReqdate(plannedOrderItem.getReqdate());
        plannedOrder.setReqdeliverydate(plannedOrderItem.getReqdeliverydate());
        plannedOrder.setReqname(plannedOrderItem.getReqname());
        plannedOrder.setReqphone(plannedOrderItem.getReqphone());
        plannedOrder.setDeliverylocation(plannedOrderItem.getDeliverylocation());
        plannedOrder.setApprovedby(plannedOrderItem.getApprovedby());
        plannedOrder.setApproveddate(plannedOrderItem.getApproveddate());
        plannedOrderRepository.save(plannedOrder);
        for (int i = 0; i < plannedOrderItem.getPlannedItemsLength(); i++) {
//            System.out.println(plannedOrderItem.getPlannedItems(i));
            PlannedItem plannedItem = plannedOrderItem.getPlannedItems(i);
            plannedItemRopository.save(plannedItem);
        }
        topicProducer.send("PlannedOrder Created");
        return "PlannedOrder Created";
    }

    @GetMapping(path = "/getAllPlannedOrder")
    public @ResponseBody List<PlannedOrderItem> getAllPlannedOrder(){
        List<PlannedOrder> plannedOrderList = plannedOrderRepository.findAll();
        List<PlannedOrderItem> plannedOrderItemList = new ArrayList<>();
        for (int i = 0; i < plannedOrderList.size(); i++) {
            PlannedOrderItem plannedOrderItem =  new PlannedOrderItem();
            plannedOrderItem.setPlannedsnumber(plannedOrderList.get(i).getPlannedsnumber());
            plannedOrderItem.setProductionnumber(plannedOrderList.get(i).getProductionnumber());
            plannedOrderItem.setReqdate(plannedOrderList.get(i).getReqdate());
            plannedOrderItem.setReqdeliverydate(plannedOrderList.get(i).getReqdeliverydate());
            plannedOrderItem.setReqname(plannedOrderList.get(i).getReqname());
            plannedOrderItem.setReqphone(plannedOrderList.get(i).getReqphone());
            plannedOrderItem.setDeliverylocation(plannedOrderList.get(i).getDeliverylocation());
            plannedOrderItem.setApprovedby(plannedOrderList.get(i).getApprovedby());
            plannedOrderItem.setApproveddate(plannedOrderList.get(i).getApproveddate());
            List<PlannedItem> plannedItemList = plannedItemRopository.findByplannednumber(plannedOrderList.get(i).getPlannedsnumber());
            plannedOrderItem.setPlannedItems(plannedItemList);
            plannedOrderItemList.add(plannedOrderItem);
        }
        String plannedOrderItemListStr = new Gson().toJson(plannedOrderItemList);
        topicProducer.send(plannedOrderItemListStr);
        return plannedOrderItemList;
    }

    @GetMapping(path = "/searchByPlannedsnumber")
    public @ResponseBody List<PlannedOrderItem> searchByPlannednumber(@RequestParam int plannednumber){
        List<PlannedOrder> plannedOrderList = plannedOrderRepository.findByplannedsnumber(plannednumber);
        List<PlannedOrderItem> plannedOrderItemList = new ArrayList<>();
        for (int i = 0; i < plannedOrderList.size(); i++) {
            PlannedOrderItem plannedOrderItem =  new PlannedOrderItem();
            plannedOrderItem.setPlannedsnumber(plannedOrderList.get(i).getPlannedsnumber());
            plannedOrderItem.setProductionnumber(plannedOrderList.get(i).getProductionnumber());
            plannedOrderItem.setReqdate(plannedOrderList.get(i).getReqdate());
            plannedOrderItem.setReqdeliverydate(plannedOrderList.get(i).getReqdeliverydate());
            plannedOrderItem.setReqname(plannedOrderList.get(i).getReqname());
            plannedOrderItem.setReqphone(plannedOrderList.get(i).getReqphone());
            plannedOrderItem.setDeliverylocation(plannedOrderList.get(i).getDeliverylocation());
            plannedOrderItem.setApprovedby(plannedOrderList.get(i).getApprovedby());
            plannedOrderItem.setApproveddate(plannedOrderList.get(i).getApproveddate());
            List<PlannedItem> plannedItemList = plannedItemRopository.findByplannednumber(plannedOrderList.get(i).getPlannedsnumber());
            plannedOrderItem.setPlannedItems(plannedItemList);
            plannedOrderItemList.add(plannedOrderItem);
        }
        String plannedOrderItemListStr = new Gson().toJson(plannedOrderItemList);
        topicProducer.send(plannedOrderItemListStr);
        return plannedOrderItemList;
    }

    @GetMapping(path = "/getAllItem")
    public  @ResponseBody List<PlannedItem> getAllPlannedOrderItem(){
        List<PlannedItem> plannedItemList =  plannedItemRopository.findAll();
        String plannedItemStr = new Gson().toJson(plannedItemList);
        topicProducer.send(plannedItemStr);
        return plannedItemList;
    }

    @GetMapping(path = "/searchItemByPlannedsnumber")
    public @ResponseBody List<PlannedItem> searchItemByPlannedsnumber(@RequestParam int plannednumber){
        List<PlannedItem> plannedItemList =  plannedItemRopository.findByplannednumber(plannednumber);
        String plannedItemStr = new Gson().toJson(plannedItemList);
        topicProducer.send(plannedItemStr);
        return plannedItemList;
    }

}
