package pe.almc.sbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.almc.sbs.bean.EntidadFinanciera;
import pe.almc.sbs.repository.EntidadFinancieraRepository;
import pe.almc.sbs.service.EntidadFinancieraService;

@Service
public class EntidadFinancieraServiceImpl implements EntidadFinancieraService{

	@Resource
	EntidadFinancieraRepository entidadFinancieraRepository;
	
	@Override
	public EntidadFinanciera findById(String codigo) {
		return entidadFinancieraRepository.findOne(codigo);
	}

	@Override
	public List<EntidadFinanciera> findAll() {
		return entidadFinancieraRepository.findAll();
	}

	@Override
	public EntidadFinanciera findByNombresbs(String nombresbs) {
		return entidadFinancieraRepository.findByNombresbs(nombresbs);
	}

}
