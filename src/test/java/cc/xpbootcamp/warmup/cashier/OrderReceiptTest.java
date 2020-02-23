package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class OrderReceiptTest {
    @Test
    void shouldPrintCustomerInformationOnOrder() throws ParseException {
        Order order = spy(new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>()));
        OrderReceipt receipt = new OrderReceipt(order);

        Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-19");
        doReturn(orderDate).when(order).getOrderDate();

        String output = receipt.printReceipt();

        assertThat(output, containsString("===== 老王超市,值得信赖 ======"));
        assertThat(output, containsString("2020年2月19日,星期三"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        Order order = spy(new Order(null, null, lineItems));
        OrderReceipt receipt = new OrderReceipt(order);

        Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-17");
        doReturn(orderDate).when(order).getOrderDate();

        String output = receipt.printReceipt();

        assertThat(output, containsString("巧克力, 21.50 x 2, 43.00\n"));
        assertThat(output, containsString("小白菜, 10.00 x 1, 10.00\n"));
        assertThat(output, containsString("-----------------------------------"));
        assertThat(output, containsString("税额: 5.30"));
        assertThat(output, containsString("总价: 58.30"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformationWithDiscount() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        Order order = spy(new Order(null, null, lineItems));
        OrderReceipt receipt = new OrderReceipt(order);

        Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-19");
        doReturn(orderDate).when(order).getOrderDate();

        String output = receipt.printReceipt();

        assertThat(output, containsString("巧克力, 21.50 x 2, 43.00\n"));
        assertThat(output, containsString("小白菜, 10.00 x 1, 10.00\n"));
        assertThat(output, containsString("-----------------------------------"));
        assertThat(output, containsString("税额: 5.30"));
        assertThat(output, containsString("折扣: 1.17"));
        assertThat(output, containsString("总价: 57.13"));
    }
}
