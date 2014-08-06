package pe.almc.sbs.ws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import pe.almc.sbs.bean.InfoTasaDiaria;


/**
 * Web Service de informacion de Tasas de SBS
 * @author ALMC
 *
 */
@WebService(name = "TestSBSWSTasas", targetNamespace="http://www.almc.com")
@SOAPBinding(style = Style.RPC)
public interface SBSWSTasas {
	@WebMethod(action="actionListarTasasEntidadPorFecha", operationName="methodListarTasasEntidadPorFecha")
	InfoTasaDiaria[] listarTasasEntidadPorFecha(@WebParam(partName="nombreEntidad") String nombreEntidad, @WebParam(partName="fecha") String fecha);

	@WebMethod
	@WebResult(partName="number_response")
	String listarTasasEntidadPorEntidad(String codigoEntidad);

}
