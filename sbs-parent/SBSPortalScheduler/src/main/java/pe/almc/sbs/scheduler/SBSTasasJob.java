package pe.almc.sbs.scheduler;


import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import pe.almc.sbs.service.SBSTasasFacade;


public class SBSTasasJob extends QuartzJobBean{

	private static Logger logger = LoggerFactory.getLogger(SBSTasasJob.class);
	private SBSTasasFacade sbsTasasFacade;
	

	public void setSbsTasasFacade(SBSTasasFacade sbsTasasFacade) {
		this.sbsTasasFacade = sbsTasasFacade;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		//Regiones
		if( sbsTasasFacade.regionFindAll().isEmpty() ){
			logger.info("No hay regiones... inicia proceso de registro de regiones");
			String rsp = sbsTasasFacade.obtenerRegiones();
			if("0".equals(rsp)){
				logger.info("Se completo registro de regiones");
			}else{
				logger.info("Error en registro de regiones");
			}
		}
		
		//Productos
		if( sbsTasasFacade.productoFindAll().isEmpty() ){
			logger.info("No hay productos... inicia proceso de registro de productos");
			String rsp = sbsTasasFacade.obtenerProductos();
			if("0".equals(rsp)){
				logger.info("Se completo registro de productos");
			}else{
				logger.info("Error en registro de productos");
			}
			
		}		
		
		//Condiciones
		if( sbsTasasFacade.condicionFindAll().isEmpty() ){
			logger.info("No hay condiciones... inicia proceso de registro de condiciones");
			String rsp = sbsTasasFacade.obtenerCondiciones();
			if("0".equals(rsp)){
				logger.info("Se completo registro de condiciones");
			}else{
				logger.info("Error en registro de condiciones");
			}			
		}
		
		
		logger.info("Iniciando registro de tasas diarias " +  new Date());
		sbsTasasFacade.obtenerTasas();
		logger.info("Finalizando " +  new Date());
	}
	
}
