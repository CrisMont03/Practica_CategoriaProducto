package Practica.Categoria_Producto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", nullable = false)
    private Long idProducto;

    @Column(name = "nombreProducto", length = 50, nullable = false)
    private String nombreProducto;

    @Column(name = "descripcionProducto", length = 250, nullable = false)
    private String descripcionProducto;

    @Column(name = "existencia", nullable = false)
    private String existencia;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "idCategoria", foreignKey = @ForeignKey(name = "FK_PRODUCTO_IDCATEGORIA"), nullable = false)
    private Categoria categoria;
}
