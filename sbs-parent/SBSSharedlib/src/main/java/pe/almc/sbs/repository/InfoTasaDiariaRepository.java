package pe.almc.sbs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.almc.sbs.bean.InfoTasaDiariaPK;
import pe.almc.sbs.bean.InfoTasaDiaria;

public interface InfoTasaDiariaRepository extends JpaRepository<InfoTasaDiaria, InfoTasaDiariaPK>{
	List<InfoTasaDiaria> findByEntidadcodigo(String entidadCodigo); 
	List<InfoTasaDiaria> findByFecha(Date fecha);
	@Query("SELECT i FROM InfoTasaDiaria i WHERE i.infotasadiariaPK.entidadcodigo = :entidadCodigo AND i.infotasadiariaPK.fecha = :fecha")
	List<InfoTasaDiaria> listarTasasEntidadPorEntidad(@Param("entidadCodigo")String entidadCodigo, @Param("fecha")Date fecha);
}
