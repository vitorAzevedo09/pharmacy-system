package com.pharmacy.system.store.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pharmacy.system.store.api.dto.OrderDTO;
import com.pharmacy.system.store.api.dto.OrderWithIdDTO;
import com.pharmacy.system.store.api.assembler.OrderAssembler;
import com.pharmacy.system.store.domain.model.Order;
import com.pharmacy.system.store.domain.service.OrderService;

/**
 * OrderController
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderAssembler orderAssembler;

  @GetMapping
  public ResponseEntity<Page<OrderWithIdDTO>> getAll(Pageable pageable) {
    Page<OrderWithIdDTO> ordersOutput = orderService.findAll(pageable)
        .map(o -> orderAssembler.toOutput(o));
    return new ResponseEntity<Page<OrderWithIdDTO>>(ordersOutput, HttpStatus.OK);
  }

  @GetMapping("/{orderID}")
  public ResponseEntity<OrderWithIdDTO> getOne(
      @PathVariable final Long orderID) {
    OrderWithIdDTO orderOutput = orderAssembler.toOutput(orderService.findOrFail(orderID));
    return new ResponseEntity<OrderWithIdDTO>(orderOutput, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderWithIdDTO> create(
      @RequestBody OrderDTO orderInput) {
    Order order = orderAssembler.toEntity(orderInput);
    try {
      Order orderOut = orderService.create(order);
      OrderWithIdDTO orderOutDTO = orderAssembler.toOutput(orderOut);
      return new ResponseEntity<OrderWithIdDTO>(orderOutDTO, HttpStatus.OK);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }

}
