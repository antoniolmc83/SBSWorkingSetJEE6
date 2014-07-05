package pe.almc.sbs.service;

import java.util.List;
import java.util.Map;

import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.SBSTasaRequest;

public interface SbsWebClientService {

	Map<String, String> recuperarRegion();

	Map<String, String> recuperarTiposOperacion(String codRegion);

	Map<String, String> recuperarProductos(	String codTipoProducto);

	Map<String, String> recuperarCondiciones(String codProducto);

	List<InfoTasaDiaria> consultarTasas(SBSTasaRequest a);

}