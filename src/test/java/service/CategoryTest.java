package service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceImpl;
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
public class CategoryTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @Test
    void whenGetAll_shouldReturnList() {
        List<Category> categories = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            categories.add(new Category());
        }
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> result = categoryServiceImpl.getAll();
        assertThat(result.size()).isEqualTo(categories.size());
        verify(categoryRepository).findAll();
    }
}
