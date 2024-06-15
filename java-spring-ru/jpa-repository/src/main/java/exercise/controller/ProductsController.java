package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> index(@RequestParam(required = false, defaultValue = "0") Integer min,
                               @RequestParam(required = false, defaultValue = "10000000") Integer max) {
        if (min != null && max != null) {
            return productRepository.findByPriceBetweenOrderByPriceAsc(min, max);
        }
        if (min != null) return productRepository.findByPriceGreaterThanEqualOrderByPriceAsc(min);
        if (max != null) return productRepository.findByPriceLessThanEqualOrderByPriceAsc(max);
        return productRepository.findAll(Sort.by(Sort.Order.asc("price")));
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
