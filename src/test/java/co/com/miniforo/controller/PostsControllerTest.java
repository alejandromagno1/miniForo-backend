package co.com.miniforo.controller;

import co.com.common.GenericResponse;
import co.com.common.error.CoreException;
import co.com.miniforo.model.Posts;
import co.com.miniforo.repository.IPostsRepository;
import co.com.miniforo.service.PostsService;
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

class PostsControllerTest {
    IPostsRepository repositoryMock = Mockito.mock(IPostsRepository.class);

    @Autowired
    PostsService service = new PostsService(repositoryMock);

    @Autowired
    PostsController controller = new PostsController(service);

    @BeforeEach
    void setUp() throws CoreException {
        Posts data = new Posts();
        data.setId(1L);
        data.setIdUser(1L);
        data.setTitle("Primer post");
        data.setBody("¿Cómo se crea un post?");
        data.setState("A");

        List<Posts> lists = new ArrayList<>();
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
        Posts data = new Posts();
        data.setId(1L);
        data.setIdUser(1L);
        data.setTitle("Primer post");
        data.setBody("¿Cómo se crea un post?");
        data.setState("A");

        GenericResponse response = controller.save(data);
        Assertions.assertNotNull(response);
    }

    @Test
    void saveFailed() {
        Posts data = new Posts();
        data.setId(1L);
        data.setIdUser(1L);
        data.setTitle("Primer post");
        data.setBody("¿Cómo se crea un post?");
        data.setState("A");

        Mockito.when(repositoryMock.save(any(Posts.class))).thenThrow(NullPointerException.class);

        GenericResponse response = controller.save(data);
        Assertions.assertEquals(500, response.getErrorCode());
    }

    @Test
    void edit() {
        Posts data = new Posts();
        data.setId(1L);
        data.setIdUser(1L);
        data.setTitle("Primer post");
        data.setBody("¿Cómo se crea un post?");
        data.setState("A");

        GenericResponse response = controller.edit(data);
        Assertions.assertNotNull(response);
    }

    @Test
    void editFailed() {
        Posts data = new Posts();
        data.setId(1L);
        data.setIdUser(1L);
        data.setTitle("Primer post");
        data.setBody("¿Cómo se crea un post?");
        data.setState("A");

        Mockito.when(repositoryMock.save(any(Posts.class))).thenThrow(NullPointerException.class);

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