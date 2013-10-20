package pe.almc.sbs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import pe.almc.sbs.bean.Producto;
import pe.almc.sbs.bean.ProductoPK;
import pe.almc.sbs.repository.ProductoRepository;
import pe.almc.sbs.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Resource
	private ProductoRepository productoRepository;
	
	@Override	
	public Producto create(Producto producto) {
		if( productoRepository.exists(producto.getProductoPK()) ){
			throw new EntityExistsException("Producto con codigo " + producto.getProductoPK() + " ya existe");
		}
		
		return productoRepository.save(producto);
	}

	@Override
	public Producto findById(String codigo, String tipoProd) {
		ProductoPK productoPk = new ProductoPK(codigo, tipoProd);
		return productoRepository.findOne(productoPk);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> findByTipoprod(String tipoprod) {
		return productoRepository.findByTipoprod(tipoprod);
	}

}
