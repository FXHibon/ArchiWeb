package fr.epsi.tp.ws.controllers.soap;

import fr.epsi.tp.ws.beans.Product;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "ProductService", serviceName = "ProductService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ProductService {

    public Product getProduct(@WebParam(name = "id") String id);
}
