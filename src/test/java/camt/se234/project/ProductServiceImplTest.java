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
        mockProducts.add(new Product("greentea"));
        mockProducts.add(new Product("tea"));
        mockProducts.add(new Product("smoothie"));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAllProducts(), hasItem(new Product("tea")));
        assertThat(productService.getAllProducts(), hasItems(new Product("greentea"),
                new Product("tea")));
    }

    @Test
    public void getAvailableProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("tea", 20));
        mockProducts.add(new Product("coffee", 15));
        mockProducts.add(new Product("juice", 25));
        mockProducts.add(new Product("ice", 8));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAvailableProducts(), hasItem(new Product("tea", 20)));
        assertThat(productService.getAvailableProducts(), is(not(new Product("ice", -8))));
    }

    @Test
    public void getUnavailableProductSizeTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("tea", 20));
        mockProducts.add(new Product("coffee", 15));
        mockProducts.add(new Product("juice", 25));
        mockProducts.add(new Product("fanta", 18));
        mockProducts.add(new Product("ice", 8));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getUnavailableProductSize(), is(0));
    }
}