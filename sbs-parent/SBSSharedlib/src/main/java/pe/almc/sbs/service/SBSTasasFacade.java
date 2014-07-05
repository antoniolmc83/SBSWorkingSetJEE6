package pe.almc.sbs.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.almc.sbs.bean.Condicion;
import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.Producto;
import pe.almc.sbs.bean.Region;
import pe.almc.sbs.bean.SBSTasaRequest;
import pe.almc.sbs.bean.TipoProducto;
import pe.almc.util.http.SBSParametros;

@Service(value="sbsTasasFacade")
public class SBSTasasFacade {

	private static Logger logger = LoggerFactory.getLogger(SBSTasasFacade.class); 
	@Resource
	private SbsWebClientService sbsWebClient;
	
	@Resource
	private RegionService regionService;
	@Resource
	private ProductoService productoService;
	@Resource
	private CondicionService condicionService;
	@Resource
	private EntidadFinancieraService entidadFinancieraService;
	@Resource
	private InfoTasaDiariaService infoTasaDiariaService; 
	
	public String metodoPrueba(){
		String resp = null;
		logger.info("INFO METODO PRUEBA");
		return resp;
	}
	
	/**
	 * Recupera todos las regiones
	 * @return
	 */
	public List<Condicion> condicionFindAll() {
		return condicionService.findAll();
	}
	
	
	/**
	 * Recupera todos los productoss
	 * @return
	 */
	public List<Producto> productoFindAll() {
		return productoService.findAll();
	}	
	
	/**
	 * Recupera todos las regiones
	 * @return
	 */
	public List<Region> regionFindAll() {
		return regionService.findAll();
	}
	
	

	@Transactional
	public String obtenerRegiones(){
		String error = "1";
		Map<String, String> lst = sbsWebClient.recuperarRegion();
		Region r = null;
		if( lst != null){
			Iterator<String> it = lst.keySet().iterator();
			String key = null;
			while(it.hasNext()){
				key = it.next();
				r = new Region(key);
				r.setNombre(lst.get(key));
				logger.info("Grabando..." + r);
				regionService.create(r);
			}			
			error = "0";	
		}
		return error;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String obtenerProductos(){
		String error = "1";
		Producto p = null;
		for(TipoProducto tipoProd:TipoProducto.values()){
			Map<String, String> lst = sbsWebClient.recuperarProductos(tipoProd.toString());			
			if( lst != null){
				Iterator<String> it = lst.keySet().iterator();
				String key = null;
				while(it.hasNext()){
					key = it.next();
					p = new Producto(key, tipoProd.toString());
					p.setDescripcion(lst.get(key));
					productoService.create(p);
				}
				error = "0";
			}			
		}		
		return error;		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String obtenerCondiciones(){
		String resp = null;
		String keyProd = null;
		//Para inicializar la invocacion a la pagina SBS
		sbsWebClient.recuperarTiposOperacion( SBSParametros.COD_REGION_LIMA );
		for( TipoProducto tipoProd:TipoProducto.values() ){
			Map<String, String> lst = sbsWebClient.recuperarProductos(tipoProd.toString());
			if( lst != null){
				Iterator<String> it = lst.keySet().iterator();				
				while(it.hasNext()){
					keyProd = it.next();
					guardarCondicionProducto(tipoProd.toString(), keyProd, sbsWebClient.recuperarCondiciones(keyProd));												
				}				
			}			
		}		
		resp = "0";
		return resp;		
	}	
	
	private void guardarCondicionProducto(String tipoProducto, String producto, Map<String, String> condiciones){
		String keyCondicion = null;
		Condicion condicion = null;
		if( condiciones != null){
			Iterator<String> it = condiciones.keySet().iterator();
			while(it.hasNext()){
				keyCondicion = it.next();
				condicion = new Condicion(keyCondicion, producto, tipoProducto);
				condicion.setDescripcion(condiciones.get(keyCondicion));
				logger.info(condicion.getDescripcion());
				condicionService.create(condicion);
			}			
		}			
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String obtenerTasas(){
		String resp = "";
		List<Region> lstRegion = null;
		List<Producto> lstProducto = null;
		Collection<Condicion> lstCondicion = null;
		SBSTasaRequest sbsTasaRequest = null;
		
		List<InfoTasaDiaria> lstInfoTasaDiariaTotal = new ArrayList<InfoTasaDiaria>();

		//Para inicializar la invocacion a la pagina SBS
		//1 Recorrer regiones
		lstRegion = regionService.findAll();
		for( Region r:lstRegion ){
			r = regionService.findById("15");
			logger.info("Region: " + r.getNombre());
			//Para inicializar la invocacion a la pagina SBS
			sbsWebClient.recuperarTiposOperacion(r.getCodigo());			
			//1.1 Por cada region recuperar los tipos de producto
			for( TipoProducto tp : TipoProducto.values() ){
				logger.info("	>Tipo Producto: " + tp.name());
				sbsWebClient.recuperarProductos(tp.toString());
				//1.2 Recuperar los productos
				lstProducto = productoService.findByTipoprod( tp.toString() );
				//1.2.1 Por cada producto recuperar las condiciones
				for(Producto p : lstProducto){
					logger.info("		>>Producto: " + p.getDescripcion());
					//1.2.1 Recuperar las condiciones
					p.getLstCondiciones().isEmpty();
					lstCondicion = p.getLstCondiciones();					
					//1.2.1.1 Por cada condicion recuperar las infotasas
					for(Condicion c:lstCondicion){						
						sbsTasaRequest = new SBSTasaRequest();						
						try {
							sbsTasaRequest.setAsCondicion( URLEncoder.encode(c.getCodigo(), SBSParametros.ENCODE_UTF_8) );
							sbsTasaRequest.setAsDpto("");
							sbsTasaRequest.setTxtCondicion( URLEncoder.encode(c.getDescripcion(), SBSParametros.ENCODE_UTF_8) );
							sbsTasaRequest.setTxtDepartamento( URLEncoder.encode(r.getNombre(), SBSParametros.ENCODE_UTF_8) );
							sbsTasaRequest.setTxtProducto( URLEncoder.encode(p.getDescripcion(), SBSParametros.ENCODE_UTF_8) );
							sbsTasaRequest.setTxtTipoProducto( URLEncoder.encode(tp.name(), SBSParametros.ENCODE_UTF_8) );
							logger.info("			>>Condicion: " + sbsTasaRequest.toString());
						} catch (UnsupportedEncodingException e) {
							logger.error("Encoding Error", e);
						}
						//Setetando los datos de condicion y region a lo recuperado
						List<InfoTasaDiaria> lstTemp = sbsWebClient.consultarTasas(sbsTasaRequest);
						logger.info("				>>Resultado: " + (lstTemp!=null?lstTemp.size():0) );
						if( lstTemp != null ){
							for(InfoTasaDiaria itd:lstTemp ){
								itd.setCondicion(c);
								itd.setRegion(r);
							}
							lstInfoTasaDiariaTotal.addAll(lstTemp);
						}
						
					}
					
				}
				
			}
			break;
		}
		
		logger.info("Guardar datos recuperados");
		
		//Guardar la lista total recuperada
		for( InfoTasaDiaria inf: lstInfoTasaDiariaTotal ){
			guardarInfoTasaDiaria(inf);
		}
		
		resp = "0";
		return resp;
	}
	
	/**
	 * 
	 * @param infoTasaDiaria
	 */
	private void guardarInfoTasaDiaria( InfoTasaDiaria infoTasaDiaria ){
		Date today = new Date();				
		//Setear la fecha de la operacion
		infoTasaDiaria.getInfotasadiariaPK().setFecha(today);		
		//La entidad financiera
		String nombreSBS = infoTasaDiaria.getEntidad();
		EntidadFinanciera entidadFinanciera = entidadFinancieraService.findByNombresbs(nombreSBS);	
		if( entidadFinanciera!=null ){
			infoTasaDiaria.setEntidadfinanciera(entidadFinanciera);
			try{
				
				if( infoTasaDiariaService.findById(entidadFinanciera, infoTasaDiaria.getCondicion(), infoTasaDiaria.getRegion(), 
						infoTasaDiaria.getInfotasadiariaPK().getFecha()) == null){
					infoTasaDiariaService.create(infoTasaDiaria);	
				}else{
					logger.info("Ya existe info tasa diaria: " + infoTasaDiaria);
				}
				
				
				
			}catch (EntityExistsException e) {
				logger.error("Tasa Duplicada: " + e);
			}
			
			logger.info("Guardando:" + infoTasaDiaria.toString());
		}else{
			logger.warn("No existe: " + nombreSBS);
		}
				
	}
	
}
