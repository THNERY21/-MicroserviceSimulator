package br.com.loja.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.loja.controller.form.TypeProductForm;
import br.com.loja.controller.form.UpdateTypeProductForm;
import br.com.loja.dto.TypeProductConsultDto;
import br.com.loja.model.TypeProduct;
import br.com.loja.model.pk.TypeId;
import br.com.loja.repository.TypeProductRepository;

@RestController
@RequestMapping("/typeProdcuts")
public class TypeProductController {


	@Autowired
	private TypeProductRepository typeProductRepository;

	@GetMapping
	@Cacheable(value = "typeList")
	public Page<TypeProductConsultDto> searchType(@RequestParam(required = false) @Valid String name,
			@PageableDefault(sort = "id", direction = Direction.ASC, page =0, size=10) Pageable pagination) {

		if (name != null) {
			Page<TypeProduct> typeProduct = typeProductRepository.findByid_NameType(name, pagination);
			return TypeProductConsultDto.convertTypeList(typeProduct);
		} else {
			Page<TypeProduct> typeProduct =typeProductRepository.findAll(pagination);
			return TypeProductConsultDto.convertTypeList(typeProduct);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "typeList", allEntries = true)
	public ResponseEntity<TypeProductConsultDto> registerTypeProduct(@RequestBody @Valid TypeProductForm typeProductForm,
			UriComponentsBuilder uriBuilder) {
		TypeProduct typeProduct = typeProductForm.convert(typeProductRepository);
		if(typeProduct!=null) {
			typeProductRepository.save(typeProduct);
			URI uri = uriBuilder.path("/prodcuts/{id}").buildAndExpand(typeProduct.getId().getName()).toUri();
			return ResponseEntity.created(uri).body(new TypeProductConsultDto(typeProduct));
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{name}")
	public ResponseEntity<TypeProductConsultDto> typeDetail(@PathVariable String name) {
		TypeId id = new TypeId();
		id.setName(name);
		Optional<TypeProduct> typeOpt = typeProductRepository.findById(id);
		if (typeOpt.isPresent()) {
			return ResponseEntity.ok(new TypeProductConsultDto(typeOpt.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{name}")
	@Transactional
	@CacheEvict(value = "typeList", allEntries = true)
	public ResponseEntity<TypeProductConsultDto> updateTypeProduct(@PathVariable String name, @RequestBody @Valid UpdateTypeProductForm form) {
		TypeId id = new TypeId();
		id.setName(name);
		Optional<TypeProduct> typeOpt = typeProductRepository.findById(id);
		if (typeOpt.isPresent()) {
			TypeProduct type = form.update(typeOpt.get(), typeProductRepository);
			return ResponseEntity.ok(new TypeProductConsultDto(type));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{name}")
	@Transactional
	@CacheEvict(value = "typeList", allEntries = true)
	public ResponseEntity<?> deleteTypeProduct(@PathVariable String name) {
		TypeId id = new TypeId();
		id.setName(name);
		Optional<TypeProduct> typeOpt = typeProductRepository.findById(id);
		if (typeOpt.isPresent()) {
			typeProductRepository.deleteById(typeOpt.get().getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
