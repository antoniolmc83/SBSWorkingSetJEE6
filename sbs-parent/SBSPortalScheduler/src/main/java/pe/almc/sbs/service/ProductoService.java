package pe.almc.sbs.service;

import java.util.List;

import pe.almc.sbs.bean.Producto;

public interface ProductoService {

	Producto create(Producto producto);
	Producto findById(String codigo, String tipoProd);
	List<Producto> findByTipoprod(String tipoProd);
	List<Producto> findAll();
	
}
