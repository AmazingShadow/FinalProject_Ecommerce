package service;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
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
public class UserTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    void whenGetAll_shouldReturnList() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            users.add(new User());
        }
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userServiceImpl.getAll();
        assertThat(result.size()).isEqualTo(users.size());
        verify(userRepository).findAll();
    }
}
