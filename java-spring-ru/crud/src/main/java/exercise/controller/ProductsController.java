package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping(path = "")
    public List<ProductDTO> index() {
        return productRepository.findAll().stream().map(productMapper::map).toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found"));
        return productMapper.map(product);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO data) {
        Product candidate = productMapper.map(data);
        productRepository.save(candidate);
        return productMapper.map(candidate);
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO data) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found"));
        productMapper.update(data, product);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    // END
}
