package co.com.miniforo.service;

import co.com.common.ResponseEnum;
import co.com.common.error.CoreException;
import co.com.miniforo.model.Posts;
import co.com.miniforo.repository.IPostsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IPostsRepository repository;
    private String msjErrFnd = "Ocurrio un error al momento de consultar el registro";
    private String msjErrSave = "Ocurrio un error al momento de guardar el registro";
    private String msjErrDel = "Ocurrio un error al momento de eliminar el registro";

    @Autowired
    public PostsService(IPostsRepository repository){
        this.repository = repository;
    }

    public List<Posts> getAll() throws CoreException{
    	try {
    		return repository.findAll();
        } catch (Exception e){
            logger.error(msjErrFnd, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }
        
    public Posts getOne(Long id) throws CoreException{
    	try {
    		return repository.getById(id);
        } catch (Exception e){
            logger.error(msjErrFnd, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }
    
    public void save(Posts data) throws CoreException {
    	try {
            repository.save(data);
        } catch (Exception e) {
            logger.error(msjErrSave, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        } 
    }
    
    public void update(Posts data) throws CoreException{
        try {
            repository.save(data);
        } catch (Exception e) {
            logger.error(msjErrSave, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }
    
    public void delete(Long id) throws CoreException{
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error(msjErrDel, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }
}