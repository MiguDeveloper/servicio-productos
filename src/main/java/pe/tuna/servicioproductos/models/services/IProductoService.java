package pe.tuna.servicioproductos.models.services;

import pe.tuna.servicioproductos.models.entity.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto findById(Long id);

}
