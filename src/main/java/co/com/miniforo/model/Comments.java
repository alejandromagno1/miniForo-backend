package co.com.miniforo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comments implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComm")
    private Long id;

    @Column(name = "postId")
    private Long idPost;

    @Column(name = "userId")
    private Long idUser;

    @Column(name = "body")
    private String body;

    @Column(name = "state")
    private String state;
}