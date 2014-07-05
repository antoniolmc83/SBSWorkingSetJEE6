package pe.almc.sbs.ws;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import pe.almc.sbs.bean.InfoTasaDiaria;


/**
 * Web Service de informacion de Tasas de SBS
 * @author ALMC
 *
 */
@WebService(name = "SBSWSTasas", targetNamespace = "http://sbs.almc.pe/", serviceName = "SBSWSTasasService", portName = "SBSWSTasasPort")
@SOAPBinding(style = Style.RPC)
public interface SBSWSTasas {
	@WebMethod
	InfoTasaDiaria[] listarTasasEntidadPorFecha(String nombreEntidad, String fecha);

	@WebMethod
	String listarTasasEntidadPorEntidad(String codigoEntidad);

}
