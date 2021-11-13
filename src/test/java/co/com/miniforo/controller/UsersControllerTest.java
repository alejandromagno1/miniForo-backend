package co.com.miniforo.controller;

import co.com.miniforo.model.Users;
import co.com.miniforo.repository.IUsersRepository;
import co.com.miniforo.service.UsersService;
import co.com.common.GenericResponse;
import co.com.common.error.CoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;

class UsersControllerTest {
    IUsersRepository repositoryMock = Mockito.mock(IUsersRepository.class);

    @Autowired
    UsersService service = new UsersService(repositoryMock);

    @Autowired
    UsersController controller = new UsersController(service);

    @BeforeEach
    void setUp() throws CoreException {
        Users data = new Users();
        data.setId(1L);
        data.setName("Alejandro Magno");
        data.setMail("alejandro@magno.com");
        data.setGender("M");
        data.setState("A");

        List<Users> lists = new ArrayList<>();
        lists.add(data);

        Mockito.when(repositoryMock.findAll()).thenReturn(lists);
    }

    @Test
    void get() {
        GenericResponse response = controller.get();
        Assertions.assertNotNull(response);
    }

    @Test
    void getFailed() {
        Mockito.when(repositoryMock.findAll()).thenThrow(NullPointerException.class);

        GenericResponse response = controller.get();
        Assertions.assertEquals(500, response.getErrorCode());
    }

    @Test
    void getOne() {
        GenericResponse response = controller.get(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    void getOneFailed() {
        Mockito.when(repositoryMock.getById(anyLong())).thenThrow(NullPointerException.class);

        GenericResponse response = controller.get(1L);
        Assertions.assertEquals(500, response.getErrorCode());
    }

    @Test
    void save() {
        Users data = new Users();
        data.setId(1L);
        data.setName("Alejandro Magno");
        data.setMail("alejandro@magno.com");
        data.setGender("M");
        data.setState("A");

        GenericResponse response = controller.save(data);
        Assertions.assertNotNull(response);
    }

    @Test
    void saveFailed() {
        Users data = new Users();
        data.setId(1L);
        data.setName("Alejandro Magno");
        data.setMail("alejandro@magno.com");
        data.setGender("M");
        data.setState("A");

        Mockito.when(repositoryMock.save(any(Users.class))).thenThrow(NullPointerException.class);

        GenericResponse response = controller.save(data);
        Assertions.assertEquals(500, response.getErrorCode());
    }

    @Test
    void edit() {
        Users data = new Users();
        data.setId(1L);
        data.setName("Alejandro Magno");
        data.setMail("alejandro@magno.com");
        data.setGender("M");
        data.setState("A");

        GenericResponse response = controller.edit(data);
        Assertions.assertNotNull(response);
    }

    @Test
    void editFailed() {
        Users data = new Users();
        data.setId(1L);
        data.setName("Alejandro Magno");
        data.setMail("alejandro@magno.com");
        data.setGender("M");
        data.setState("A");

        Mockito.when(repositoryMock.save(any(Users.class))).thenThrow(NullPointerException.class);

        GenericResponse response = controller.edit(data);
        Assertions.assertEquals(500, response.getErrorCode());
    }

    @Test
    void delete() {
        GenericResponse response = controller.delete(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    void deleteFailed() {
        doThrow(NullPointerException.class).when(repositoryMock).deleteById(anyLong());

        GenericResponse response = controller.delete(1L);
        Assertions.assertEquals(500, response.getErrorCode());
    }
}