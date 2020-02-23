package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

import static cc.xpbootcamp.warmup.Util.DateUtil.isWednesday;
import static cc.xpbootcamp.warmup.Util.DateUtil.parseDateToString;
import static cc.xpbootcamp.warmup.Util.PriceUtil.formatPrice;

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
        receiptHeaderBuilder.append(parseDateToString(orderDate));

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
                .append(formatPrice(calculateTotalSalesTax(lineItemList)))
                .append("\n");
        if(isWednesday(order.getOrderDate())){
            receiptFooterBuilder.append("折扣: ")
                    .append(formatPrice(calculateDiscount(lineItemList)))
                    .append("\n");
            receiptFooterBuilder.append("总价: ")
                    .append(formatPrice(calculateTotalAmountWithDiscount(lineItemList)))
                    .append("\n");
        } else {
            receiptFooterBuilder.append("总价: ")
                    .append(formatPrice(calculateTotalAmount(lineItemList)))
                    .append("\n");
        }
        return receiptFooterBuilder.toString();
    }

    private double calculateDiscount(List<LineItem> lineItemList) {
        return 0.02 * (calculateTotalAmount(lineItemList));
    }

    private double calculateTotalAmountWithDiscount(List<LineItem> lineItemList) {
        return calculateTotalAmount(lineItemList) - calculateDiscount(lineItemList);
    }

    private double calculateTotalAmount(List<LineItem> lineItemList) {
        double totalAmount = 0d;
        for (LineItem lineItem : lineItemList) {
            totalAmount += lineItem.calculateTotalAmount();
        }
        totalAmount += calculateTotalSalesTax(lineItemList);
        return totalAmount;
    }

    private double calculateTotalSalesTax(List<LineItem> lineItemList) {
        double totalSalesTax = 0d;
        for (LineItem lineItem : lineItemList) {
            double salesTax = lineItem.calculateTotalAmount() * .10;
            totalSalesTax += salesTax;
        }
        return totalSalesTax;
    }

    private String buildLineItems(List<LineItem> lineItemList) {
        StringBuilder lineItemsBuilder = new StringBuilder();
        for (LineItem lineItem : lineItemList) {
            lineItemsBuilder.append(lineItem.getDescription())
                    .append(", ")
                    .append(formatPrice(lineItem.getPrice()))
                    .append(" x ")
                    .append(lineItem.getQuantity())
                    .append(", ")
                    .append(formatPrice(lineItem.calculateTotalAmount()))
                    .append('\n');
        }
        return lineItemsBuilder.toString();
    }
}
