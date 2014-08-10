package pe.almc.sbs.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.service.EntidadFinancieraService;
import pe.almc.sbs.service.InfoTasaDiariaService;


@Path("SBSRsWs")
public class SBSRestfulTasas {
	
	private static Logger logger = LoggerFactory.getLogger(SBSRestfulTasas.class);
	
	@Autowired
	private InfoTasaDiariaService infoTasaDiariaService;
	@Autowired
	private EntidadFinancieraService entidadFinancieraService; 
	
	@GET
	@Produces("text/plain")
	public String listarTasasEntidadPorEntidad(){
		List<InfoTasaDiaria> l = infoTasaDiariaService.findByEntidadcodigo("");
		System.out.println(l);
		return "Hola";
	}
	
	@GET
	@Path("/entidad/{codigo}")
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public EntidadFinanciera getEntidadByCode(@PathParam("codigo") String codigo){
		EntidadFinanciera ef = null;
		ef = entidadFinancieraService.findById(codigo);
		logger.info("getEntidadByCode:" + ef);
		return ef;
	}
	
	@GET
	@Path("/entidadSearchName/{namepart}")
	@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
	public List<EntidadFinanciera> getEntidadByPartName(@PathParam("namepart") String namepart){
		List<EntidadFinanciera> lef = null;
		logger.info("getEntidadByCode:Parameter" + namepart);
		lef = entidadFinancieraService.findBySBSNamePart(namepart);
		logger.info("getEntidadByCode:" + lef);
		return lef;
	}

}
