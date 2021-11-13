package co.com.miniforo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="posts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Posts implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;
	
	@Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "state")
    private String state;
}