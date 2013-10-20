package pe.almc.sbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pe.almc.sbs.bean.Region;

public interface RegionRepository extends JpaRepository<Region, String>{

	/*
	Region grabar(Region region);
	Region eliminar(String codigo);
	Region actualizar(Region region);
	Region buscarPorId(String codigo);
	*/
	
}
