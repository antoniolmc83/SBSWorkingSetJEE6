package pe.almc.sbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.almc.sbs.bean.Producto;
import pe.almc.sbs.bean.ProductoPK;

public interface ProductoRepository extends JpaRepository<Producto, ProductoPK>{

	List<Producto> findByTipoprod(String tipoprod);

}
