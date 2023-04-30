package service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Test
    void whenGetAll_shouldReturnList() {
        List<Product> categories = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            categories.add(new Product());
        }
        when(productRepository.findAll()).thenReturn(categories);
        List<Product> result = productServiceImpl.getAll();
        assertThat(result.size()).isEqualTo(categories.size());
        verify(productRepository).findAll();
    }

}
