package by.fastrentcar.service.impl;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import by.fastrentcar.springdata.AuthUserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})

public class DefaultUserServiceTest {
    @Mock
    AuthUserDAO dao;
    @InjectMocks
    DefaultUserService service;


    @Test
    void getListAuthUserUserDTO() {
        when(dao.getListAuthUserUserDTO()).thenReturn(new ArrayList<AuthUserUserDTO>());
        List<AuthUserUserDTO> users = service.getListAuthUserUserDTO();
        assertNotNull(users);
    }

    @Test
    void getAuthUserUserDTO() {
        when(dao.getAuthUserUserDTO("11")).thenReturn(new AuthUserUserDTO(1l, "11", "11", Role.USER, 12l, "11", "11", "11", "11", "11", "11", "11"));
        AuthUserUserDTO au = service.getAuthUserUserDTO("11");
        assertNotNull(au);
        assertEquals(Role.USER, au.getRole());
    }

    @Test
    void addCustomerTest() {
        User user = new User(123l, "11", "12", "11", "23", "23", "34", "4");
        AuthUser authUser = new AuthUser(1l, "123", "321", Role.USER, 123l);
        when(dao.addAuthUserUserT(authUser, user)).thenReturn(1l);
        Long id = service.addCustomer(authUser, user);
        assertNotNull(id);
        assertEquals(id, 1l);
    }

    @Test
    void updateCustomerTest() {
        User user = new User(123l, "11", "12", "11", "23", "23", "34", "4");
        AuthUser authUser = new AuthUser(1l, "123", "321", Role.USER, 123l);
        when(dao.updateAuthUserUserT(authUser, user)).thenReturn(true);
        assertEquals(true, service.updateCustomer(authUser, user));
        verify(dao).updateAuthUserUserT(authUser, user);
    }

    @Test
    void deleteCustomerTest() {
        when(dao.deleteAuthUserT(1l)).thenReturn(true);
        assertEquals(true, service.deleteCutomer(1l));
        verify(dao).deleteAuthUserT(1l);
    }


}
