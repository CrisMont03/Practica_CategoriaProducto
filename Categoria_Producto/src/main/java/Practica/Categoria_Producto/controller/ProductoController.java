package Practica.Categoria_Producto.controller;

import Practica.Categoria_Producto.entities.Categoria;
import Practica.Categoria_Producto.entities.Producto;
import Practica.Categoria_Producto.excepciones.ResourceNotFoundException;
import Practica.Categoria_Producto.service.CategoriaService;
import Practica.Categoria_Producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/API/Practica/Producto/")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    //GET: Sirve para listar todos los productos
    @GetMapping("/metodos")
    public List<Producto> listarTodosLosProductos() {
        return productoService.findAll();
    }

    //GET: Sirve para buscar un producto por id
    @GetMapping("/metodos/{id}")
    public ResponseEntity<Producto> encontrarProductoById(@PathVariable Long id) {
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID" + id));
        return ResponseEntity.ok(producto);
    }

    // POST: Sirve para guardar un producto
    @PostMapping("/metodos")
    public Producto crearProducto(@RequestBody Map<String, Object> productoRequest) {
        Long idCategoria = Long.valueOf(productoRequest.get("idCategoria").toString());
        Categoria categoria = categoriaService.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID" + idCategoria));

        Producto producto = new Producto();
        producto.setNombreProducto(productoRequest.get("nombreProducto").toString());
        producto.setDescripcionProducto(productoRequest.get("descripcionProducto").toString());
        producto.setExistencia(productoRequest.get("existencia").toString());
        producto.setPrecio(Double.valueOf(productoRequest.get("precio").toString()));
        producto.setCategoria(categoria);

        return productoService.save(producto);
    }

    //PUT: Para actualizar un producto
    @PutMapping("/metodos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID" + id));

        producto.setNombreProducto(detallesProducto.getNombreProducto());
        producto.setDescripcionProducto(detallesProducto.getDescripcionProducto());
        producto.setExistencia(detallesProducto.getExistencia());
        producto.setPrecio(detallesProducto.getPrecio());

        Producto productoActualizado = productoService.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    //DELETE: Para eliminar un producto
    @DeleteMapping("/metodos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id){
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID" + id));

        productoService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
