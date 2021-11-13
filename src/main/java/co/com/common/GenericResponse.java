package co.com.common;

import co.com.common.error.CoreException;
import lombok.Data;

import java.io.Serializable;

@Data
public class GenericResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Object object;
    private String errorMessage;
    private String successMessage;
    private co.com.common.ResultStatus resultStatus;
    private Integer errorCode;

    /**
     * Metodo encargado de setear la respuesta del success con la información
     * suficiente para responder al cliente que realiza la solicitus, adicional
     * se retornará información especifica sobre la propiedad Object.
     * '@param object'
     */
    public void success( Object object ) {
        setObject(object);
        setResultStatus(co.com.common.ResultStatus.OK);
        setSuccessMessage( ResponseEnum.CODE_SUCCESS.name() );
    }

    /**
     * Metodo encargado de setear la respuesta del success con la información
     * suficiente para responder al cliente que realiza la solicitdu.
     */
    public void success() {
        setResultStatus(co.com.common.ResultStatus.OK);
        setSuccessMessage( ResponseEnum.CODE_SUCCESS.name() );
    }

    /**
     * Metodo encargado de setear la respuesta del failure con la información
     * suficiente para responder al cliente que realiza la solicitud.
     * '@param coreException' Exception lanzada desde el servicio.
     */
    public void failure( CoreException coreException ) {
        setResultStatus(co.com.common.ResultStatus.FAILURE);
        setErrorMessage( coreException.getError().name() );
        setErrorCode( 500 );
    }
}