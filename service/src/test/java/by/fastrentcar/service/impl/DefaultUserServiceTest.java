package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.impl.DefaultAuthUserDAO;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.service.UserService;
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
    AuthUserDAO dao= DefaultAuthUserDAO.getInstance();

    @InjectMocks
    UserService service=DefaultUserService.getInstance();



    @Test
    void getUsers() {
        when(dao.getListAuthUserUserDTO()).thenReturn(new ArrayList<AuthUserUserDTO>());
        List<AuthUserUserDTO> users = service.getListAuthUserUserDTO();
        assertNotNull(users);
    }

}
