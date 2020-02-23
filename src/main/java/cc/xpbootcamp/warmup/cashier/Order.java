package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItemList;
    private Date orderDate;

    public Order(String customerName, String customerAddress, List<LineItem> lineItemList, Date orderDate) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItemList = lineItemList;
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
