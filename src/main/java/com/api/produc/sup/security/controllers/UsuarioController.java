package com.api.produc.sup.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.produc.sup.security.entities.Usuario;
import com.api.produc.sup.security.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/admin/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	// ==============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ==============================================

	// ================
	// ===== POST =====
	// ================
	@Operation(summary = "Inserción de un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Insertado el Usuario Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Insertar el Usuario. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Inserción del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {

		try {
			usuarioService.addUsuario(usuario);

			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// ================
	// ===== PUT =====
	// ================
	@Operation(summary = "Actualización de un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Actualizado el Usuario Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Actualizar el Usuario. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Actualización del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PutMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {

		try {
			usuarioService.updateUsuario(usuario);

			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}
	
	
	// ================
	// ===== DELETE =====
	// ================
	@Operation(summary = "Eliminación de un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Eliminado el Usuario Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Eliminar el Usuario. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Eliminación del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUsuario(@RequestBody long id) {

		try {
			usuarioService.deleteUsuario(id);

			return new ResponseEntity<Usuario>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}
	
	
	// =================
	// ===== GET ALL ====
	// =================
	@Operation(summary = "Listado Paginado de Usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Listado de Usuarios. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Usuarios no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/listado")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Usuario> getAllUsuario(Pageable pageable) {

		return usuarioService.getAllUsuario(pageable);

	}

}



