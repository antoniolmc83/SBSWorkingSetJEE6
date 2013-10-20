package pe.almc.sbs;



import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pe.almc.sbs.service.SBSTasasFacade;

public class SBSTasasFacadeTestCase {

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

}
