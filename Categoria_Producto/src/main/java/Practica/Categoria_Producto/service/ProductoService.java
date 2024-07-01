package Practica.Categoria_Producto.service;

import Practica.Categoria_Producto.entities.Categoria;
import Practica.Categoria_Producto.entities.Producto;
import Practica.Categoria_Producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //Mostrar productos
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    //Encontrar productos
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    //Crear o editar productos
    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    //Eliminar producto
    public void deleteById(Long id){
        productoRepository.deleteById(id);
    }
}
