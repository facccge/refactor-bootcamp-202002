package cc.xpbootcamp.warmup.cashier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {
    private String cName;
    private String addr;
    private List<LineItem> lineItemList;
    private Date orderDate;

    public Order(String cName, String addr, List<LineItem> lineItemList, Date orderDate){
        this.cName = cName;
        this.addr = addr;
        this.lineItemList = lineItemList;
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return cName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
