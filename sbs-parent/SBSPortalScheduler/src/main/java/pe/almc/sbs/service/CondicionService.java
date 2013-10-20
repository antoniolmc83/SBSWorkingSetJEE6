package pe.almc.sbs.service;

import java.util.List;

import pe.almc.sbs.bean.Condicion;

public interface CondicionService {
	Condicion create(Condicion condicion);
	Condicion findById(String condicion);
	List<Condicion> findAll();
}
