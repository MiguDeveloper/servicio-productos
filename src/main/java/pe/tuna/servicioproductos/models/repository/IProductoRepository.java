package pe.tuna.servicioproductos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.tuna.servicioproductos.models.entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
