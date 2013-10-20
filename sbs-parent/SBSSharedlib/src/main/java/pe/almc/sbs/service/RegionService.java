package pe.almc.sbs.service;

import java.util.List;

import pe.almc.sbs.bean.Region;

public interface RegionService {
	public Region create(Region region);
	public Region findById(String region);
	public List<Region> findAll();

}
