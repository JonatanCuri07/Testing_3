package com.mirai.importback.services.impl;

import com.mirai.importback.entities.ProductList;
import com.mirai.importback.repositories.IProductListRepository;
import com.mirai.importback.services.IProductListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly =true)
public class ProductListImpl implements IProductListService {

    private final IProductListRepository productListRepository;

    public ProductListImpl(IProductListRepository productListRepository) {
        this.productListRepository = productListRepository;
    }


    @Override
    @Transactional
    public ProductList save(ProductList productList) throws Exception {
        return productListRepository.save(productList);
    }

    @Override
    public void delete(Long id) throws Exception {
        productListRepository.deleteById(id);
    }

    @Override
    public List<ProductList> getAll() throws Exception {
        return productListRepository.findAll();
    }

    @Override
    public Optional<ProductList> getById(Long id) throws Exception {
        return productListRepository.findById(id);
    }
}
