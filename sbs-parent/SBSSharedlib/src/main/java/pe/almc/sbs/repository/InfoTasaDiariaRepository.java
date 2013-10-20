package pe.almc.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.almc.sbs.bean.InfoTasaDiariaPK;
import pe.almc.sbs.bean.InfoTasaDiaria;

public interface InfoTasaDiariaRepository extends JpaRepository<InfoTasaDiaria, InfoTasaDiariaPK>{

}
