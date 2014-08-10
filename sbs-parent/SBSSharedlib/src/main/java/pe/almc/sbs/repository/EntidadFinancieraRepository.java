package pe.almc.sbs.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.almc.sbs.bean.EntidadFinanciera;

public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, String>{
	
	EntidadFinanciera findByNombresbs(String nombresbs);
	
	@Query("SELECT e FROM EntidadFinanciera e WHERE UPPER(e.razonsocial) LIKE CONCAT('%',:namepart,'%')")
	List<EntidadFinanciera> findBySBSNamePart(@Param("namepart")String namePart);
}
