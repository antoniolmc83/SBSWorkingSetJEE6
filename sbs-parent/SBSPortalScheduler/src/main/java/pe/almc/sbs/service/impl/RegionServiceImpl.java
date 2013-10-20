package pe.almc.sbs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import pe.almc.sbs.bean.Region;
import pe.almc.sbs.repository.RegionRepository;
import pe.almc.sbs.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

	@Resource
	private RegionRepository regionRepository;
	
	public RegionServiceImpl() {

	}

	@Override
	public Region create(Region region) {
		if(regionRepository.exists(region.getCodigo())){
			throw new EntityExistsException("Region con codigo "+ region.getCodigo() +  " ya existe");
		}		
		return regionRepository.save(region);
	}

	@Override
	public Region findById(String region) {
		return regionRepository.findOne(region);
	}

	@Override
	public List<Region> findAll() {
		return regionRepository.findAll();
	}

}
