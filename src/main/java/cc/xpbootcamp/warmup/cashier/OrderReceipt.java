package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(buildReceiptHeader(order.getOrderDate()));
        output.append(buildReceiptBody(order.getLineItems()));
        output.append(buildReceiptFooter(order.getLineItems()));

        return output.toString();
    }

    private String buildReceiptHeader(Date orderDate) {
        StringBuilder receiptHeaderBuilder = new StringBuilder();

        receiptHeaderBuilder.append("===== 老王超市,值得信赖 ======\n");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月dd日,EEEE\n", Locale.CHINA);
        receiptHeaderBuilder.append(dateFormat.format(orderDate));

        return receiptHeaderBuilder.toString();
    }

    private String buildReceiptBody(List<LineItem> lineItemList) {
        StringBuilder receiptBodyBuilder = new StringBuilder();
        receiptBodyBuilder.append(buildLineItems(lineItemList));
        return receiptBodyBuilder.toString();
    }

    private String buildReceiptFooter(List<LineItem> lineItemList) {
        StringBuilder receiptFooterBuilder = new StringBuilder();
        receiptFooterBuilder.append("-----------------------------------\n");
        receiptFooterBuilder.append("税额: ")
                .append(formatePrice(calculateTotalSalesTax(lineItemList)))
                .append("\n");
        receiptFooterBuilder.append("总价: ")
                .append(formatePrice(calculateTotalAmount(lineItemList)))
                .append("\n");
        return receiptFooterBuilder.toString();
    }

    private double calculateTotalAmount(List<LineItem> lineItemList) {
        double totalAmount = 0d;
        for (LineItem lineItem : lineItemList) {
            totalAmount += lineItem.totalAmount();
        }
        totalAmount += calculateTotalSalesTax(lineItemList);
        return totalAmount;
    }

    private double calculateTotalSalesTax(List<LineItem> lineItemList) {
        double totalSalesTax = 0d;
        for (LineItem lineItem : lineItemList) {
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;
        }
        return totalSalesTax;
    }

    private String buildLineItems(List<LineItem> lineItemList) {
        StringBuilder lineItemsBuilder = new StringBuilder();
        for (LineItem lineItem : lineItemList) {
            lineItemsBuilder.append(lineItem.getDescription())
                    .append(", ")
                    .append(formatePrice(lineItem.getPrice()))
                    .append(" x ")
                    .append(lineItem.getQuantity())
                    .append(", ")
                    .append(formatePrice(lineItem.totalAmount()))
                    .append('\n');
        }
        return lineItemsBuilder.toString();
    }

    private String formatePrice(double price){
        return String.format("%.2f", price);
    }
}
