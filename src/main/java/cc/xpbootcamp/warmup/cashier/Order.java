package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

import static cc.xpbootcamp.warmup.Util.DateUtil.isWednesday;

public class Order {
    public static final double TAX_RATE = 0.1d;
    public static final double DISCOUNT_RATE = 0.02d;
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItemList;
    private Date orderDate;

    public Order(String customerName, String customerAddress, List<LineItem> lineItemList) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItemList = lineItemList;
        this.orderDate = new Date();
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
        for (LineItem lineItem : getLineItems()) {
            totalAmount += lineItem.calculateAmount();
        }
        return totalAmount;
    }

    double calculateTotalTax() {
        return TAX_RATE * calculateTotalAmount();
    }

    double calculateDiscount() {
        return DISCOUNT_RATE * (calculateTotalAmount() + calculateTotalTax());
    }

    double calculateTotalPrice() {
        return isAbleToDiscount()
                ? calculateTotalAmount() - calculateDiscount() + calculateTotalTax()
                : calculateTotalAmount() + calculateTotalTax();
    }

    boolean isAbleToDiscount(){
        return isWednesday(getOrderDate());
    }
}
