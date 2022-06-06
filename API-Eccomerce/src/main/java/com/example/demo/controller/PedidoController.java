package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.example.demo.exception.ClienteInexistenteException;
import com.example.demo.exception.PedidoExistenteException;
import com.example.demo.exception.PedidoInexistenteException;
import com.example.demo.model.Pedido;
import com.example.demo.model.PedidoDTO;
import com.example.demo.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService service;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Lista de Pedidos", "Segue todos os pedidos cadastrados");
		return new ResponseEntity<List<Pedido>>(service.listarTudo(), headers, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{numero}")
	public ResponseEntity<Pedido> getOne(@PathVariable Integer numero) throws PedidoInexistenteException {
		return new ResponseEntity<Pedido>(service.listarPedido(numero), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody PedidoDTO pedidoDTO) throws PedidoExistenteException, ClienteInexistenteException, PedidoInexistenteException {
		service.inserir(pedidoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{numero}")
	public ResponseEntity<Pedido> update(@RequestBody Pedido pedido, @PathVariable Integer numero)
			throws PedidoInexistenteException, PedidoExistenteException {
		return new ResponseEntity<Pedido>(service.atualizar(pedido, numero), HttpStatus.OK);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<?> delete(@PathVariable Integer numero) throws PedidoInexistenteException {
		service.deletar(numero);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
