package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

import static cc.xpbootcamp.warmup.Util.DateUtil.isWednesday;

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

    public double calculateTotalAmount() {
        double totalAmount = 0d;
        for (LineItem lineItem : lineItemList) {
            totalAmount += lineItem.calculateAmount();
        }
        return totalAmount;
    }

    public double calculateTotalTax() {
        return 0.1d * calculateTotalAmount();
    }

    public double calculateDiscount() {
        return 0.02d * (calculateTotalAmount() + calculateTotalTax());
    }

    public double calculateTotalPrice() {
        return isWednesday(orderDate)
                ? calculateTotalAmount() - calculateDiscount() + calculateTotalTax()
                : calculateTotalAmount() + calculateTotalTax();
    }
}
