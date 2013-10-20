package pe.almc.sbs;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.almc.sbs.bean.Condicion;
import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.Region;
import pe.almc.sbs.service.InfoTasaDiariaService;


public class InfoTasaDiariaTestCase {

	private InfoTasaDiariaService infoTasaDiariaService;
	
	public InfoTasaDiariaTestCase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		infoTasaDiariaService = applicationContext.getBean(InfoTasaDiariaService.class);
	}
	
	@Test
	public void testGrabarInfoTasaDiaria() {
		EntidadFinanciera ef = new EntidadFinanciera("2018");
		Condicion c = new Condicion("00083|2");
		Region r = new Region("01");
		GregorianCalendar gc1 = new GregorianCalendar(2013, 9, 14);
		GregorianCalendar gc2 = new GregorianCalendar(2013, 9, 15);
		gc1.setTime(new Date());
		InfoTasaDiaria inf = new InfoTasaDiaria(ef, c, r, gc1.getTime());			
		InfoTasaDiaria inf2 = new InfoTasaDiaria(ef, c, r, gc2.getTime());
		
		infoTasaDiariaService.create(inf);
		infoTasaDiariaService.create(inf2);
	}
	
	@Test
	public void testFindById() {
		EntidadFinanciera ef = new EntidadFinanciera("2018");
		Condicion c = new Condicion("01");
		Region r = new Region("01");
		GregorianCalendar gc2 = new GregorianCalendar(2013, 9, 15);
		InfoTasaDiaria i = infoTasaDiariaService.findById(ef, c, r, gc2.getTime());
		TestCase.assertEquals(gc2.getTime() , i.getInfotasadiariaPK().getFecha());
	}

	
	@Test
	public void testFindByAll() {
		List<InfoTasaDiaria> l = infoTasaDiariaService.findAll();
		TestCase.assertEquals(2, l.size());
	}

}
