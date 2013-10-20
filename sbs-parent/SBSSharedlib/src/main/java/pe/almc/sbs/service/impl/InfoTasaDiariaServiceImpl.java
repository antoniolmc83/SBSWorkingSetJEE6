package pe.almc.sbs.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.almc.sbs.bean.Condicion;
import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.bean.InfoTasaDiariaPK;
import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.Region;
import pe.almc.sbs.repository.InfoTasaDiariaRepository;
import pe.almc.sbs.service.InfoTasaDiariaService;

@Service
public class InfoTasaDiariaServiceImpl implements InfoTasaDiariaService{

	@Resource
	private InfoTasaDiariaRepository informacionTasaDiariaRepository;
	
	@Transactional
	@Override
	public InfoTasaDiaria create(InfoTasaDiaria informacionTasaDiaria) {
		if( informacionTasaDiariaRepository.exists(informacionTasaDiaria.getInfotasadiariaPK()) ){
			throw new EntityExistsException("InformacionTasaDiaria con codigo " + informacionTasaDiaria.getInfotasadiariaPK() + " ya existe");
		}
		return informacionTasaDiariaRepository.save(informacionTasaDiaria);
	}

	@Override
	public InfoTasaDiaria findById(String entidadcodigo,	String condicioncodigo, String regioncodigo, Date fecha) {
		InfoTasaDiariaPK infoTasaDiariaPK = new InfoTasaDiariaPK(entidadcodigo, condicioncodigo, regioncodigo, fecha);
		return informacionTasaDiariaRepository.findOne(infoTasaDiariaPK);
	}

	@Override
	public InfoTasaDiaria findById(EntidadFinanciera entidadFinanciera,
			Condicion condicion, Region region, Date fecha) {
		return findById(entidadFinanciera.getCodigo(), condicion.getCodigo(), region.getCodigo(), fecha);
	}

	@Override
	public List<InfoTasaDiaria> findAll() {
		return informacionTasaDiariaRepository.findAll();
	}

}
