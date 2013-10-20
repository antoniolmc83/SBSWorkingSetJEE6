package pe.almc.sbs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.almc.sbs.bean.Condicion;
import pe.almc.sbs.repository.CondicionRepository;
import pe.almc.sbs.service.CondicionService;

@Service
public class CondicionServiceImpl implements CondicionService{

	@Resource
	private CondicionRepository condicionRepository;
	
	@Transactional
	@Override
	public Condicion create(Condicion condicion) {
		if(condicionRepository.exists(condicion.getCodigo())){
			throw new EntityExistsException("Condicion con codigo " + condicion.getCodigo() + " ya existe: " + condicion.getDescripcion());
		}
		return condicionRepository.save(condicion);
	}

	@Override
	public Condicion findById(String condicion) {
		return condicionRepository.findOne(condicion);
	}

	@Override
	public List<Condicion> findAll() {
		return condicionRepository.findAll();
	}

}
