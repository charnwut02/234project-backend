// JavaScript source code
package camt.se234.project;

import camt.se234.project.dao.OrderDao;
import camt.se234.project.entity.Product;
import camt.se234.project.entity.SaleOrder;
import camt.se234.project.entity.SaleTransaction;
import camt.se234.project.service.SaleOrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaleOrderServiceImplTest {
    OrderDao orderDao;
    SaleOrderServiceImpl saleOrderService;

    @Before
    public void setup(){
        orderDao = mock(OrderDao.class);
        saleOrderService = new SaleOrderServiceImpl();
        saleOrderService.setOrderDao(orderDao);

    }

    @Test
    public void getSaleOrdersTest(){
        List<SaleOrder> orders = new ArrayList<>();
        when(orderDao.getOrders()).thenReturn(orders);
        orders.add(new SaleOrder("100"));
        orders.add(new SaleOrder("200"));
        orders.add(new SaleOrder("300"));
        assertThat(saleOrderService.getSaleOrders() , hasItem(new SaleOrder("100")));
        assertThat(saleOrderService.getSaleOrders() , hasItems(new SaleOrder("100"),
                new SaleOrder("200")));
    }

    @Test
    public void getAverageSaleOrderPriceTest(){
        List<SaleTransaction> transactions = new ArrayList<>();
        List<SaleOrder> orders = new ArrayList<>();
        transactions.add(new SaleTransaction(new Product("tea" , 20) , 5));
        transactions.add(new SaleTransaction(new Product("coffee" , 15) , 3));
        when(orderDao.getOrders()).thenReturn(orders);
        orders.add(new SaleOrder(transactions));
        assertThat(saleOrderService.getAverageSaleOrderPrice(),is(145.0));
    }
}