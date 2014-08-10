package pe.almc.sbs.service;

import java.util.List;

import pe.almc.sbs.bean.EntidadFinanciera;

public interface EntidadFinancieraService {

	EntidadFinanciera findById(String codigo);
	List<EntidadFinanciera> findAll();
	EntidadFinanciera findByNombresbs(String nombresbs);
	List<EntidadFinanciera> findBySBSNamePart(String namePart);
}
