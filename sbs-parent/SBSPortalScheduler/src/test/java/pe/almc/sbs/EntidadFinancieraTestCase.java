package pe.almc.sbs;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.service.EntidadFinancieraService;

public class EntidadFinancieraTestCase {

	private EntidadFinancieraService entidadFinancieraService;
	
	public EntidadFinancieraTestCase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		entidadFinancieraService = applicationContext.getBean(EntidadFinancieraService.class);
	}

	@Test
	public void testFindById() {
		String codigo="0832";
		EntidadFinanciera ef = entidadFinancieraService.findById(codigo);
		TestCase.assertEquals("C.R.A.C. SEÑOR DE LUREN", ef.getRazonsocial());		
	}

	@Test
	public void testFindAll() {
		List<EntidadFinanciera> l = entidadFinancieraService.findAll();
		TestCase.assertEquals(118, l.size());
	}

	@Test
	public void testFindNombreSbs() {
		String codigo="CRAC CREDINKA";
		EntidadFinanciera l = entidadFinancieraService.findByNombresbs(codigo);
		TestCase.assertEquals("C.R.A.C CREDINKA", l.getRazonsocial());
	}

}
