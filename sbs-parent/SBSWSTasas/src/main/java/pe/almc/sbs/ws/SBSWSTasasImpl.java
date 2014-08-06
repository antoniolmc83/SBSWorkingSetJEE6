package pe.almc.sbs.ws;


import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.beans.factory.annotation.Autowired;

import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.service.InfoTasaDiariaService;

@WebService(endpointInterface = "pe.almc.sbs.ws.SBSWSTasas", portName="TestSBSWSTasasPort", serviceName = "SBSWSTasasService",
	targetNamespace="http://www.almc.com")

public class SBSWSTasasImpl implements SBSWSTasas {

	@Autowired
	private InfoTasaDiariaService infoTasaDiariaService;	

	public InfoTasaDiaria[] listarTasasEntidadPorFecha(String nombreEntidad, String fecha) {		
		List<InfoTasaDiaria> lst = infoTasaDiariaService.listarTasasEntidadPorEntidad(nombreEntidad, fecha); 		
		return lst.toArray(new InfoTasaDiaria[0]);
	}


	@Override
	public String listarTasasEntidadPorEntidad(String codigoEntidad) {
		List<InfoTasaDiaria> l = infoTasaDiariaService.findByEntidadcodigo(codigoEntidad); 
		return String.valueOf(l.size());
	}

}
