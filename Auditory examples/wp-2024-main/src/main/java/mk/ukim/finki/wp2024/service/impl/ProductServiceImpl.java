package mk.ukim.finki.wp2024.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp2024.model.Category;
import mk.ukim.finki.wp2024.model.Manufacturer;
import mk.ukim.finki.wp2024.model.Product;
import mk.ukim.finki.wp2024.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp2024.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp2024.repository.impl.jpa.CategoryRepository;
import mk.ukim.finki.wp2024.repository.impl.jpa.ManufacturerRepository;
import mk.ukim.finki.wp2024.repository.impl.jpa.ProductRepository;
import mk.ukim.finki.wp2024.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

//    @Override
//    public Page<Product> findPage(String name, Long categoryId, Long manufacturerId, Integer pageNum, Integer pageSize) {
//        Specification<Product> specification = Specification
//                .where(filterContainsText(Product.class, "name", name))
//                .and(filterEquals(Product.class, "category.id", categoryId))
//                .and(filterEquals(Product.class, "manufacturer.id", manufacturerId));
//
//        return this.productRepository.findAll(
//                specification,
//                PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "name"))
//        );
//    }


    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Product product = new Product(name, price, quantity, category, manufacturer);
        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(product));

    }

//    @Override
//    public Optional<Product> update(
//            Long id,
//            String name,
//            Double price,
//            Integer quantity,
//            Long categoryId,
//            Long manufacturerId
//    ) {
//        Product product = this.productRepository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException(id));
//        Category category = this.categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
//        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
//                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
//
//        product.setName(name);
//        product.setPrice(price);
//        product.setQuantity(quantity);
//        product.setCategory(category);
//        product.setManufacturer(manufacturer);
//
//        return Optional.of(this.productRepository.save(product));
//    }


    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

}

