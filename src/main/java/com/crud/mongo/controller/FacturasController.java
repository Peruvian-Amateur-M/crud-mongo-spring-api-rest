package com.crud.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.mongo.documents.Facturas;
import com.crud.mongo.repository.FacturasRepository;

@RestController
@RequestMapping("/facturas")
public class FacturasController {

	@Autowired
	private FacturasRepository facturasRepository;
	
	@PostMapping("/new")
	public ResponseEntity<?> saveFactura(@RequestBody Facturas factura){
		try {
			Facturas facturaNueva = facturasRepository.save(factura);
			return new ResponseEntity<Facturas>(facturaNueva, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllFacturas(){
		try {
			List <Facturas> facturas = facturasRepository.findAll();
			return ResponseEntity.ok(facturas);
		} catch (Exception e) {
			String errorMessage = "Error al buscar todas la facturas: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> findByIdFactura(@PathVariable Long id){
		try {
			Optional<Facturas> facturaBuscada = facturasRepository.findById(id);
			if (facturaBuscada.isPresent()) {
	            
	            return ResponseEntity.ok(facturaBuscada.get());
			}else {
	            return ResponseEntity.notFound().build(); // Factura no encontrada
	        }
		} catch (Exception e) {
			String errorMessage = "Error al buscar la factura buscada: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateFactura(@PathVariable Long id, @RequestBody Facturas factura) {
	    try {
	        // Verifica si la factura con el ID especificado existe en la base de datos
	        Optional<Facturas> facturaExistente = facturasRepository.findById(id);
	        
	        if (facturaExistente.isPresent()) {
	            Facturas facturaActual = facturaExistente.get();
	            
	            // Actualiza los campos de la factura con los datos proporcionados en el cuerpo de la solicitud
	            facturaActual.setEmisor(factura.getEmisor()); // Reemplaza "Campo1" con los nombres reales de los campos
	            facturaActual.setReceptor(factura.getReceptor()); // Reemplaza "Campo2" con los nombres reales de los campos
	            facturaActual.setNumFactura(factura.getNumFactura());

	            // Guarda la factura actualizada en la base de datos
	            Facturas facturaActualizada = facturasRepository.save(facturaActual);

	            return ResponseEntity.ok(facturaActualizada);
	        } else {
	            return ResponseEntity.notFound().build(); // Factura no encontrada
	        }
	    } catch (Exception e) {
	        String errorMessage = "Error al actualizar la factura: " + e.getMessage();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}

	@DeleteMapping("/factura/{id}")
	public ResponseEntity<?> deleteFactura(@PathVariable Long id) {
	    try {
	        // Verifica si la factura con el ID especificado existe en la base de datos
	        Optional<Facturas> facturaExistente = facturasRepository.findById(id);
	        
	        if (facturaExistente.isPresent()) {
	            facturasRepository.deleteById(id);
	            return new ResponseEntity<String>("registro eliminado correctamente", HttpStatus.OK);
	        } else {
	        	return new ResponseEntity<String>("registro no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        String errorMessage = "Error al eliminar la factura: " + e.getMessage();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	
}
