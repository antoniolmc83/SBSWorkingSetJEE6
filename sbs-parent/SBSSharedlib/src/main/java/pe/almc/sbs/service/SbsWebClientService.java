package pe.almc.sbs.service;

import java.util.List;
import java.util.Map;

import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.SBSTasaRequest;

public interface SbsWebClientService {

	public abstract Map<String, String> recuperarRegion();

	public abstract Map<String, String> recuperarTiposOperacion(String codRegion);

	public abstract Map<String, String> recuperarProductos(
			String codTipoProducto);

	public abstract Map<String, String> recuperarCondiciones(String codProducto);

	public abstract List<InfoTasaDiaria> consultarTasas(SBSTasaRequest a);

}