package pe.almc.sbs;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.almc.sbs.bean.Region;
import pe.almc.sbs.service.RegionService;

public class RegionTestCase {

	private RegionService regionService;
		
	public RegionTestCase(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		regionService = applicationContext.getBean(RegionService.class);
	}
	
	@Test
	public void testGrabarRegion() {
		Region region = new Region();
		region.setCodigo("01");
		region.setNombre("Prueba");		
		regionService.create(region);
		region = new Region();
		region.setCodigo("02");
		region.setNombre("Prueba2");
		regionService.create(region);
		TestCase.assertTrue(true);
	}
	
	@Test
	public void testFindById(){
		Region region = null;
		String codigo = "01";
		region = regionService.findById( codigo );
		System.out.println(region.getNombre());
		TestCase.assertEquals("Prueba", region.getNombre());		
	}
	
	@Test
	public void testFindAll(){		
		List<Region> lista = regionService.findAll();
		TestCase.assertTrue(lista.size() == 2);
	}
	
	
	

}
