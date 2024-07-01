package Practica.Categoria_Producto.controller;

import Practica.Categoria_Producto.entities.Categoria;
import Practica.Categoria_Producto.excepciones.ResourceNotFoundException;
import Practica.Categoria_Producto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/API/Practica/Categoria/")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //GET: Sirve para listar todas las categorias
    @GetMapping("/metodos")
    public List<Categoria> listarTodasLasCategorias() {
        return categoriaService.findAll();
    }

    //GET: Sirve para buscar una categoria por Id
    @GetMapping("/metodos/{id}")
    public ResponseEntity<Categoria> encontrarCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID" + id));
        return ResponseEntity.ok(categoria);
    }

    //POST: Sirve para guardar una categoria
    @PostMapping("/metodos")
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }

    //PUT: Para actualizar una categoria
    @PutMapping("/metodos/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria detallesCategoria) {
        Categoria categoria = categoriaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID" + id));

        categoria.setNombreCategoria(detallesCategoria.getNombreCategoria());
        categoria.setDescripcionCategoria(detallesCategoria.getDescripcionCategoria());

        Categoria categoriaActualizada = categoriaService.save(categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    //DELETE
    @DeleteMapping("/metodos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCategoria(@PathVariable Long id){
        Categoria categoria = categoriaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID" + id));

        categoriaService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
