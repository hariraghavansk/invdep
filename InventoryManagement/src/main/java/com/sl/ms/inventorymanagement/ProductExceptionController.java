package com.sl.ms.inventorymanagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ProductExceptionController {
	
   private static final Logger log=LoggerFactory.getLogger(InvController.class);
	
   @ExceptionHandler(ProductNotfoundException.class)
   public ResponseEntity<Object> exception(ProductNotfoundException ex, WebRequest request) {
	   
	   List<String> details = new ArrayList<>();
       details.add(ex.getLocalizedMessage());
       ErrorResponse error = new ErrorResponse("Product Not Found", details);
       log.error("Product Not Found");
       return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
   }
   
}
