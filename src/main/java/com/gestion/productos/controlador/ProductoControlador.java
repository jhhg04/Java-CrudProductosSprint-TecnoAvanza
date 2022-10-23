package com.gestion.productos.controlador;

import com.gestion.productos.modelo.Producto;
import com.gestion.productos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    @GetMapping("/productos")
    public List<Producto> listarProductos(){
        return servicio.listarProductos();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id){
        try{
            Producto producto = servicio.obtenerProductoPorId(id);
            return new ResponseEntity<Producto>(producto, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/productos")
    public void guardarProducto(@RequestBody Producto producto){
        servicio.guardarProducto(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Integer id){
        try{
            Producto productoActual = servicio.obtenerProductoPorId(id);

            productoActual.setNombre(producto.getNombre());
            productoActual.setPrecio(producto.getPrecio());

            servicio.guardarProducto(productoActual);

            return new ResponseEntity<Producto>(HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable Integer id){
        servicio.eliminarProducto(id);
    }
}
