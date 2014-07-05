package pe.almc.sbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pe.almc.sbs.bean.EntidadFinanciera;

public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, String>{
	
	EntidadFinanciera findByNombresbs(String nombresbs);
}
