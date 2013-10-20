package pe.almc.sbs;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.almc.sbs.bean.Condicion;
import pe.almc.sbs.bean.Producto;
import pe.almc.sbs.service.CondicionService;

public class CondicionTestCase {

	private CondicionService condicionService;
	
	
	public CondicionTestCase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		condicionService = applicationContext.getBean(CondicionService.class);
	}
	
	@Test
	public void testGrabarCondicion(){
		Producto p = new Producto("01", "02");
		Condicion c = new Condicion();
		Condicion c2 = new Condicion();
		c.setCodigo("01");
		c2.setCodigo("02");
		c.setDescripcion("Descripcion");
		c2.setDescripcion("Descripcion2");
		c.setProducto(p);
		c2.setProducto(p);
		condicionService.create(c);
		condicionService.create(c2);
	}
	
	@Test
	public void testFindById(){
		Condicion c = condicionService.findById("01");
		TestCase.assertEquals("Descripcion", c.getDescripcion());
	}
	
	@Test
	public void testFindAll(){
		List<Condicion> l = condicionService.findAll();
		TestCase.assertEquals(2, l.size());
	}
	
	
}
