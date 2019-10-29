package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.AuthUserUserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DefaultUserServiceTest {
    @Mock
    AuthUserDAO dao;

    @InjectMocks
    DefaultUserService service;



    @Test
    void getUsers() {
        when(dao.getListAuthUserUserDTO()).thenReturn(new ArrayList<AuthUserUserDTO>());
        List<AuthUserUserDTO> users = service.getListAuthUserUserDTO();
        assertNotNull(users);
    }

}
