package co.com.miniforo.service;

import co.com.miniforo.model.Users;
import co.com.miniforo.repository.IUsersRepository;
import co.com.common.ResponseEnum;
import co.com.common.error.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IUsersRepository repository;
    private String msjErrFnd = "Ocurrio un error al momento de consultar el registro";
    private String msjErrSave = "Ocurrio un error al momento de guardar el registro";
    private String msjErrDel = "Ocurrio un error al momento de eliminar el registro";

    @Autowired
    public UsersService(IUsersRepository repository){
        this.repository = repository;
    }

    public List<Users> getAll() throws CoreException{
    	try {
    		return repository.findAll();
        } catch (Exception e){
            logger.error(msjErrFnd, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }

    public Users getOne(Long id) throws CoreException{
    	try {
    		return repository.getById(id);
        } catch (Exception e){
            logger.error(msjErrFnd, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }

    public void save(Users data) throws CoreException {
    	try {
            repository.save(data);
        } catch (Exception e) {
            logger.error(msjErrSave, e);
            throw new CoreException(ResponseEnum.REQUEST_EXIST);
        }
    }

    public void update(Users data) throws CoreException{
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