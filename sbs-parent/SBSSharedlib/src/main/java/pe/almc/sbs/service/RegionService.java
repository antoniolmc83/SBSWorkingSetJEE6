package pe.almc.sbs.service;

import java.util.List;

import pe.almc.sbs.bean.Region;

public interface RegionService {
	Region create(Region region);
	Region findById(String region);
	List<Region> findAll();

}
