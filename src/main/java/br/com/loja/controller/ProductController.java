package br.com.loja.controller;

import java.net.URI;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.loja.controller.form.ProductForm;
import br.com.loja.controller.form.UpdateProductForm;
import br.com.loja.dto.ProductDto;
import br.com.loja.model.Products;
import br.com.loja.repository.ProductsRepository;
import br.com.loja.repository.TypeProductRepository;

@RestController
@RequestMapping("/prodcuts")
public class ProductController {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private TypeProductRepository typeProductRepository;

	@GetMapping
	public Page<ProductDto> searchproducts(@RequestParam(required = false) @Valid String name,
			@PageableDefault(sort = "id", direction = Direction.ASC, page =0, size=10) Pageable pagination) {

		if (name != null) {
			Page<Products> product = productsRepository.findByName(name, pagination);
			return ProductDto.convertProductList(product);
		} else {
			Page<Products> product = productsRepository.findAll(pagination);
			return ProductDto.convertProductList(product);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> registerProduct(@RequestBody @Valid ProductForm productForm,
			UriComponentsBuilder uriBuilder) {
		Products products = productForm.convert(typeProductRepository);
		productsRepository.save(products);
		URI uri = uriBuilder.path("/prodcuts/{id}").buildAndExpand(products.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(products));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> productdetail(@PathVariable Long id) {

		Optional<Products> product = productsRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new ProductDto(product.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductForm form) {
		Optional<Products> optional = productsRepository.findById(id);
		if (optional.isPresent()) {
			Products product = form.update(id, productsRepository, typeProductRepository);
			return ResponseEntity.ok(new ProductDto(product));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Optional<Products> optional = productsRepository.findById(id);
		if (optional.isPresent()) {
			productsRepository.delete(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
