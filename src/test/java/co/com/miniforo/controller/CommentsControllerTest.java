package co.com.miniforo.controller;

import co.com.common.GenericResponse;
import co.com.common.error.CoreException;
import co.com.miniforo.model.Comments;
import co.com.miniforo.repository.ICommentsRepository;
import co.com.miniforo.service.CommentsService;
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

class CommentsControllerTest {
    ICommentsRepository repositoryMock = Mockito.mock(ICommentsRepository.class);

    @Autowired
    CommentsService service = new CommentsService(repositoryMock);

    @Autowired
    CommentsController controller = new CommentsController(service);

    @BeforeEach
    void setUp() throws CoreException {
        Comments data = new Comments();
        data.setId(1L);
        data.setIdPost(1L);
        data.setIdUser(1L);
        data.setBody("Descarga el manual que se encuentra en la opción help");
        data.setState("A");

        List<Comments> lists = new ArrayList<>();
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
        Comments data = new Comments();
        data.setId(1L);
        data.setIdPost(1L);
        data.setIdUser(1L);
        data.setBody("Descarga el manual que se encuentra en la opción help");
        data.setState("A");

        GenericResponse response = controller.save(data);
        Assertions.assertNotNull(response);
    }

    @Test
    void saveFailed() {
        Comments data = new Comments();
        data.setId(1L);
        data.setIdPost(1L);
        data.setIdUser(1L);
        data.setBody("Descarga el manual que se encuentra en la opción help");
        data.setState("A");

        Mockito.when(repositoryMock.save(any(Comments.class))).thenThrow(NullPointerException.class);

        GenericResponse response = controller.save(data);
        Assertions.assertEquals(500, response.getErrorCode());
    }

    @Test
    void edit() {
        Comments data = new Comments();
        data.setId(1L);
        data.setIdPost(1L);
        data.setIdUser(1L);
        data.setBody("Descarga el manual que se encuentra en la opción help");
        data.setState("A");

        GenericResponse response = controller.edit(data);
        Assertions.assertNotNull(response);
    }

    @Test
    void editFailed() {
        Comments data = new Comments();
        data.setId(1L);
        data.setIdPost(1L);
        data.setIdUser(1L);
        data.setBody("Descarga el manual que se encuentra en la opción help");
        data.setState("A");

        Mockito.when(repositoryMock.save(any(Comments.class))).thenThrow(NullPointerException.class);

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