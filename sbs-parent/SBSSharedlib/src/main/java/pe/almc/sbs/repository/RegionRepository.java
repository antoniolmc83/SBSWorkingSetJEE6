package pe.almc.sbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pe.almc.sbs.bean.Region;

public interface RegionRepository extends JpaRepository<Region, String>{
	
}
