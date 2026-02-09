package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditProductTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findByIdReturnsProductWhenPresent() {
        Product product = new Product();
        product.setProductId("prod-1");
        product.setProductName("Shampoo");
        product.setProductQuantity(10);
        when(productRepository.findById("prod-1")).thenReturn(Optional.of(product));

        Product result = productService.findById("prod-1");

        assertEquals(product, result);
        verify(productRepository).findById("prod-1");
    }

    @Test
    void findByIdReturnsNullWhenMissing() {
        when(productRepository.findById("missing")).thenReturn(Optional.empty());

        Product result = productService.findById("missing");

        assertNull(result);
        verify(productRepository).findById("missing");
    }

    @Test
    void updateDelegatesToRepository() {
        Product product = new Product();
        product.setProductId("prod-2");
        product.setProductName("Soap");
        product.setProductQuantity(5);
        when(productRepository.update(product)).thenReturn(product);

        Product updated = productService.update(product);

        assertEquals(product, updated);
        verify(productRepository).update(product);
    }
}
