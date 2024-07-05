package br.com.univille.germoneys.product;

import br.com.univille.germoneys.controller.ProductController;
import br.com.univille.germoneys.entity.Product;
import br.com.univille.germoneys.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100.00));

        when(productService.getById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Product", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(100, response.getBody().getPrice());
    }
}