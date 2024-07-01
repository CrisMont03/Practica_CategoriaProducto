package Practica.Categoria_Producto.service;

import Practica.Categoria_Producto.entities.Categoria;
import Practica.Categoria_Producto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Mostrar categorias
    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    //Encontrar categoria
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    //Crear o editar categoria
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    //Eliminar categoria
    public void deleteById(Long id){
        categoriaRepository.deleteById(id);
    }

}
