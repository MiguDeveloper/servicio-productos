package pe.tuna.servicioproductos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.tuna.serviciocommons.models.entity.Producto;
import pe.tuna.servicioproductos.models.services.IProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    // A traves del enviroment podemos obtener el puerto donde se esta ejecutando
    @Autowired
    private Environment env;

    // Otra opcion es usar el value y leer la propiedad del puerto
    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.findAll().stream().map(producto -> {
            //producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            producto.setPort(port);
            return producto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/listar/{id}")
    public Producto detalle(@PathVariable long id) throws Exception {
        Producto producto = productoService.findById(id);
        //producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        producto.setPort(port);

        /*
        Simulamos un error para que se vea el efecto de hystrix en nuestra configuracion
        boolean ok = false;
        if (ok == false) {
            throw new Exception("No se puede cargar el producto");
        }
        */

        // Ahora simulamos un tiempo de espera mayor para que tambien sea detectado por hystrix
        //Thread.sleep(2000L);

        return producto;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@PathVariable(name = "id") Long id, @RequestBody Producto producto) {
        Producto productoCurrent = productoService.findById(id);

        productoCurrent.setNombre(producto.getNombre());
        productoCurrent.setPrecio(producto.getPrecio());

        return productoService.save(productoCurrent);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        productoService.deleteById(id);
    }
}
