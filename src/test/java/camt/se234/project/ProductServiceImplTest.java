package camt.se234.project;

import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    ProductDao productDao;
    ProductServiceImpl productService;

    @Before
    public void setup() {
        productDao = mock(ProductDao.class);
        productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
    }

    @Test
    public void getAllProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("chicken"));
        mockProducts.add(new Product("pork"));
        mockProducts.add(new Product("beef"));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAllProducts(), hasItem(new Product("pork")));
        assertThat(productService.getAllProducts(), hasItems(new Product("chicken"),
                new Product("pork")));
    }

    @Test
    public void getAvailableProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("pork", 80));
        mockProducts.add(new Product("beef", 100));
        mockProducts.add(new Product("fish", 30));
        mockProducts.add(new Product("rice", 24));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAvailableProducts(), hasItem(new Product("pork", 80)));
        assertThat(productService.getAvailableProducts(), is(not(new Product("rice", 24))));
    }

    @Test
    public void getUnavailableProductSizeTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("pork", 20));
        mockProducts.add(new Product("beef", 15));
        mockProducts.add(new Product("fish", 30));
        mockProducts.add(new Product("tea", 16));
        mockProducts.add(new Product("rice", 24));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getUnavailableProductSize(), is(1));
    }
}