package com.sl.ms.inventorymanagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api")
public class InvController {

	@Autowired
	private ProductRepo productrepo;

	@Autowired
	private InvRepo invrepo;

	private static final Logger log=LoggerFactory.getLogger(InvController.class);
	
	@GetMapping("/products")
	public List<Product> findAllProduct() {
		log.info("List products");		
		return productrepo.findAll();
	}

	@GetMapping("/products/{product_id}")
	public ResponseEntity<Product> findOne(@PathVariable Integer product_id) throws ProductNotfoundException {
		log.info("Display products");
		Product product = productrepo.findById(product_id).orElseThrow(() -> new ProductNotfoundException("Invalid product id : " + product_id));
		return ResponseEntity.ok().body(product);
	}

	@PostMapping("/products/{product_id}")
	public Inv createInv(@RequestBody Inv inv) {
		log.info("Create products");
		return invrepo.save(inv);
	}

	@PostMapping("/products")
	public List<Product> saveAllPrd(@RequestBody List<Product> product) {
		log.info("Save all products");
		return productrepo.saveAll(product);
	}
	
	@PostMapping(value = "/products/file", consumes = "text/csv")
	public void uploadSimple(@RequestBody InputStream body) {
		try {
			log.info("Save from file");
			productrepo.saveAll(CsvUtils.read(Product.class, body));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/products/file", consumes = "multipart/form-data")
	public void uploadMultipart(@RequestParam("file") MultipartFile file) {
		try {
			log.info("Save from file");
			productrepo.saveAll(CsvUtils.read(Product.class, file.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PutMapping("/products/{product_id}")
	public ResponseEntity<Object> UpdateOne(@RequestBody Product newproduct, @PathVariable Integer product_id)
			throws ProductNotfoundException {
		log.info("Get a product");
		Product product = productrepo.findById(product_id).orElseThrow(() -> new ProductNotfoundException("Invalid product id : " + product_id));
		product.setProductid(newproduct.getProductid());
		product.setName(newproduct.getName());
		product.setPrice(newproduct.getPrice());
		product.setQuantity(newproduct.getQuantity());
		productrepo.save(product);
		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/products/{product_id}")
	public ResponseEntity<Object> deleteOne(@PathVariable Integer product_id) throws ProductNotfoundException {
		log.info("Deleting product");
		@SuppressWarnings("unused")
		Product product = productrepo.findById(product_id).orElseThrow(() -> new ProductNotfoundException("Invalid product id : " + product_id));
		productrepo.deletePrd(product_id);
		return new ResponseEntity<>("Product quantity is updated to 0", HttpStatus.OK);
	}

	@GetMapping("/supportedprodcuts")
	@Cacheable("UniquePrd")
	public List<UniquePrd> findDistinctproduct() {
		log.info("Cache is enabled");
		return productrepo.findDistinctproduct();
	}

	
}
