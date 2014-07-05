package pe.almc.sbs;



import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import pe.almc.sbs.service.SBSTasasFacade;

public class SBSTasasFacadeTestCase {
	private final static Logger logger = LoggerFactory.getLogger(SBSTasasFacadeTestCase.class);
	private SBSTasasFacade sbsTasasFacade;
	
	public SBSTasasFacadeTestCase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		sbsTasasFacade = applicationContext.getBean(SBSTasasFacade.class);
	}
	
	@Test
	public void testObtenerRegiones() {
		String resp = sbsTasasFacade.obtenerRegiones();
		TestCase.assertEquals("0", resp);
	}

	@Test
	public void testObtenerProductos() {
		String resp = sbsTasasFacade.obtenerProductos();
		TestCase.assertEquals("0", resp);
	}
	
	@Test
	public void testObtenerCondiciones() {
		String resp = sbsTasasFacade.obtenerCondiciones();
		TestCase.assertEquals("0", resp);
	}

	@Test
	public void testObtenerTasas() {
		String resp = sbsTasasFacade.obtenerTasas();
		TestCase.assertEquals("0", resp);
	}
	
	@Test
	public void testMetodoJob(){
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
			logger.info("No hay productos... inicia proceso de registro de regiones");
			String rsp = sbsTasasFacade.obtenerProductos();
			if("0".equals(rsp)){
				logger.info("Se completo registro de productos");
			}else{
				logger.info("Error en registro de productos");
			}
			
		}		
		
		//Condiciones
		if( sbsTasasFacade.condicionFindAll().isEmpty() ){
			logger.info("No hay condiciones... inicia proceso de registro de regiones");
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
