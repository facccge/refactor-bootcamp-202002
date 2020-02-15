package cc.xpbootcamp.warmup.cashier;

import java.util.List;

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

        output.append(buildReceiptHeader(order.getCustomerName(), order.getCustomerAddress()));
        output.append(buildReceiptBody(order.getLineItems()));

        return output.toString();
    }

    private String buildReceiptHeader(String customerName, String customerAddress) {
        StringBuilder receiptHeaderBuilder = new StringBuilder();

        receiptHeaderBuilder.append("======Printing Orders======\n");
        receiptHeaderBuilder.append(customerName);
        receiptHeaderBuilder.append(customerAddress);

        return receiptHeaderBuilder.toString();
    }

    private String buildReceiptBody(List<LineItem> lineItemList) {
        StringBuilder receiptBodyBuilder = new StringBuilder();
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : lineItemList) {
            receiptBodyBuilder.append(lineItem.getDescription());
            receiptBodyBuilder.append('\t');
            receiptBodyBuilder.append(lineItem.getPrice());
            receiptBodyBuilder.append('\t');
            receiptBodyBuilder.append(lineItem.getQuantity());
            receiptBodyBuilder.append('\t');
            receiptBodyBuilder.append(lineItem.totalAmount());
            receiptBodyBuilder.append('\n');

            double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;

            tot += lineItem.totalAmount() + salesTax;
        }
        receiptBodyBuilder.append("Sales Tax").append('\t').append(totSalesTx);
        receiptBodyBuilder.append("Total Amount").append('\t').append(tot);
        return receiptBodyBuilder.toString();
    }
}
