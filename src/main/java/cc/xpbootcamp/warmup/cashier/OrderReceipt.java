package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

import static cc.xpbootcamp.warmup.Util.DateUtil.parseDateToString;
import static cc.xpbootcamp.warmup.Util.PriceUtil.formatPrice;

public class OrderReceipt {
    public static final String RECEIPT_TITLE = "===== 老王超市,值得信赖 ======\n";
    public static final String DIVIDER = "-----------------------------------\n";
    public static final String TOTAL_TAX_TITLE = "税额: ";
    public static final String DISCOUNT_TITLE = "折扣: ";
    public static final String TOTAL_PRICE_TITLE = "总价: ";
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

        receiptHeaderBuilder.append(RECEIPT_TITLE);
        receiptHeaderBuilder.append(parseDateToString(orderDate));

        return receiptHeaderBuilder.toString();
    }

    private String buildReceiptBody(List<LineItem> lineItemList) {
        StringBuilder receiptBodyBuilder = new StringBuilder();
        for (LineItem lineItem : lineItemList) {
            receiptBodyBuilder.append(buildLineItem(lineItem));
        }
        return receiptBodyBuilder.toString();
    }

    private String buildReceiptFooter(List<LineItem> lineItemList) {
        StringBuilder receiptFooterBuilder = new StringBuilder();
        receiptFooterBuilder.append(DIVIDER);
        receiptFooterBuilder.append(TOTAL_TAX_TITLE)
                .append(formatPrice(order.calculateTotalTax()))
                .append("\n");
        if (order.isAbleToDiscount()) {
            receiptFooterBuilder.append(DISCOUNT_TITLE)
                    .append(formatPrice(order.calculateDiscount()))
                    .append("\n");
        }
        receiptFooterBuilder.append(TOTAL_PRICE_TITLE)
                .append(formatPrice(order.calculateTotalPrice()))
                .append("\n");
        return receiptFooterBuilder.toString();
    }

    private String buildLineItem(LineItem lineItem) {
        StringBuilder lineItemsBuilder = new StringBuilder();
        lineItemsBuilder.append(lineItem.getDescription())
                .append(", ")
                .append(formatPrice(lineItem.getPrice()))
                .append(" x ")
                .append(lineItem.getQuantity())
                .append(", ")
                .append(formatPrice(lineItem.calculateAmount()))
                .append('\n');
        return lineItemsBuilder.toString();
    }
}
