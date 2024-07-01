package Practica.Categoria_Producto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria", nullable = false)
    private Long idCategoria;

    @Column(name = "nombreCategoria", length = 50, nullable = false)
    private String nombreCategoria;

    @Column(name = "descripcionCategoria", length = 250, nullable = false)
    private String descripcionCategoria;
}
