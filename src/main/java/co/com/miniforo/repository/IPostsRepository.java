package co.com.miniforo.repository;

import co.com.miniforo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostsRepository extends JpaRepository<Posts, Long> {

}