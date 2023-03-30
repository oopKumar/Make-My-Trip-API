package com.oop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oop.request.Passenger;
import com.oop.response.Ticket;
import com.oop.service.BookTicketConsumerService;

import reactor.core.publisher.Mono;

@RestController
public class BookTicketConsumerController {
	
	BookTicketConsumerService bookTicketConsumerService;
	
	public BookTicketConsumerController(BookTicketConsumerService bookTicketConsumerService) {
		super();
		this.bookTicketConsumerService = bookTicketConsumerService;
	}
	
	//Book Ticket API
	
		@PostMapping(
			value = "/bookTicket",
			consumes = { "application/xml", "application/json" }, 
			produces = { "application/xml", "application/json" }
			)
	public ResponseEntity<?> bookTicket(@RequestBody Passenger passenger) {
		Ticket bookTicket = bookTicketConsumerService.bookTicket(passenger);
		if(bookTicket!=null)
		{
		
		return new ResponseEntity<>(bookTicket,HttpStatus.CREATED);
		}else{
		return new ResponseEntity<>("Try After Some Time Server Under Maintainance Now",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
		
		//Check Ticket Status API
		
		@GetMapping(
				value="/checkStatus/{ticketId}",
				produces = { "application/xml", "application/json" }
				)
		public ResponseEntity<?> checkTicketStatus(@PathVariable Integer ticketId){
			
			Object checkTicketStatusById = bookTicketConsumerService.checkTicketStatusById(ticketId);
			if(checkTicketStatusById!=null) {
				return new ResponseEntity<>(checkTicketStatusById,HttpStatus.OK);
				
			}else
			{
			return new ResponseEntity<>("Ticket Not Found By Particular Id",HttpStatus.PARTIAL_CONTENT);
			}
				
		}
		
		//Cancel Ticket API
	@DeleteMapping("/cancelTicket/{ticketId}")
	public ResponseEntity<String> cancelTicket(@PathVariable Integer ticketId) {
		String cancelTicket = bookTicketConsumerService.cancelTicket(ticketId);
			
		return new ResponseEntity<>(cancelTicket,HttpStatus.OK);
		
		}	
	
}
