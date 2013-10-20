package pe.almc.herramientas.aritmeticas;



import java.util.Map;

import org.junit.Before;
import org.junit.Test;


import pe.almc.sbs.bean.SBSTasaRequest;
import pe.almc.sbs.service.SbsWebClientService;
import pe.almc.sbs.service.impl.SBSWebClientServiceImpl;
import junit.framework.TestCase;


public class SBSWebClientTest {
	
	SbsWebClientService sbs = null;

	@Before
	public void inicarSBSWebClient(){
		sbs = new SBSWebClientServiceImpl();

	}
	
	
	@Test
	public void testRecuperarRegion(){
		Map<String,String> l = sbs.recuperarRegion();
		System.out.println(l);
		TestCase.assertNotNull(l);
	}

	@Test
	public void testRecuperarTiposOperacion(){
		System.out.println( sbs.recuperarTiposOperacion("01") );
		TestCase.assertNotNull( sbs.recuperarTiposOperacion("01") );
	}

	@Test
	public void testRecuperarProductos(){
		System.out.println( sbs.recuperarProductos("01") );
		TestCase.assertNotNull(sbs.recuperarProductos("01"));
	}

	@Test
	public void testRecuperarCondiciones(){
		System.out.println( sbs.recuperarCondiciones("03") );
		TestCase.assertNotNull(sbs.recuperarCondiciones("03"));
	}
	
	@Test
	public void testRecuperarTasa(){
		
		SBSTasaRequest rqTasa = new SBSTasaRequest();
		rqTasa.setAsCondicion("00086%7C2");
		rqTasa.setAsDpto("");
		rqTasa.setTxtCondicion("PRESTAMO+ACTIVO+FIJO+POR+US%24+6+000+A+24+MESES");
		rqTasa.setTxtDepartamento("LIMA");
		rqTasa.setTxtProducto("ACTIVO+FIJO");
		rqTasa.setTxtTipoProducto("CREDITOS");
		
		TestCase.assertNotNull(sbs.consultarTasas(rqTasa));
				
	}
	
	

}
