package co.com.miniforo.repository;

import co.com.miniforo.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentsRepository extends JpaRepository<Comments, Long> {

}