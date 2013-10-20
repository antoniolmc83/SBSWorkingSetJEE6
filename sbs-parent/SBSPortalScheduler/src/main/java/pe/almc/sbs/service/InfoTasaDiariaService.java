package pe.almc.sbs.service;

import java.util.Date;
import java.util.List;

import pe.almc.sbs.bean.Condicion;
import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.Region;

public interface InfoTasaDiariaService {

	InfoTasaDiaria create(InfoTasaDiaria informacionTasaDiaria);
	InfoTasaDiaria findById(String entidadcodigo, String condicioncodigo, String regioncodigo, Date fecha);
	InfoTasaDiaria findById(EntidadFinanciera entidadFinanciera, Condicion condicion, Region region, Date fecha);
	List<InfoTasaDiaria> findAll();
}
