package pe.almc.sbs;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.almc.sbs.bean.Producto;
import pe.almc.sbs.bean.TipoProducto;
import pe.almc.sbs.service.ProductoService;

public class ProductoTestCase {

	private ProductoService productoService;
	
	public ProductoTestCase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		productoService = applicationContext.getBean(ProductoService.class);
	}

	
	@Test
	public void testGrabarProducto() {
		Producto p1 = new Producto("01", "01");
		Producto p2 = new Producto("01", "02");
		Producto p3 = new Producto("02", "01");
		p1.setDescripcion("D1");
		p2.setDescripcion("D2");
		p3.setDescripcion("D3");
		productoService.create(p1);
		productoService.create(p2);
		productoService.create(p3);
		TestCase.assertTrue(true);
	}
	
	@Test
	public void testFindById() {
		Producto p = productoService.findById("01", "02");
		TestCase.assertEquals("D2", p.getDescripcion());
	}

	@Test
	public void testFindAll() {
		List<Producto> l = productoService.findAll();
		TestCase.assertEquals(14, l.size());
	}

	@Test
	public void testFindByTipoprod() {
		List<Producto> l = productoService.findByTipoprod(TipoProducto.CREDITOS.toString());
		TestCase.assertEquals(9, l.size());
	}
}
