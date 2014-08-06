package pe.almc.sbs.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.service.InfoTasaDiariaService;


@Path("hello")
public class SBSRestfulTasas {
	@Autowired
	private InfoTasaDiariaService infoTasaDiariaService;
	
	@GET
	@Produces("text/plain")
	public String listarTasasEntidadPorEntidad(){
		List<InfoTasaDiaria> l = infoTasaDiariaService.findByEntidadcodigo("");
		System.out.println(l);
		return "Hola";
	}
	

}
