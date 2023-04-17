package com.mirai.importback.services.impl;


import com.mirai.importback.entities.ProductWholesale;
import com.mirai.importback.repositories.IProductWholesaleRepository;
import com.mirai.importback.services.IProductWholesaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductWholesaleServiceImpl implements IProductWholesaleService {

    private final IProductWholesaleRepository productWholesaleRepository;

    public ProductWholesaleServiceImpl(IProductWholesaleRepository productWholesaleRepository) {
        this.productWholesaleRepository = productWholesaleRepository;
    }

    @Override
    @Transactional
    public ProductWholesale save(ProductWholesale productWholesale) throws Exception {
        return  productWholesaleRepository.save(productWholesale);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        productWholesaleRepository.deleteById(id);
    }

    @Override
    public List<ProductWholesale> getAll() throws Exception {
        return productWholesaleRepository.findAll();
    }

    @Override
    public Optional<ProductWholesale> getById(Long id) throws Exception {
        return productWholesaleRepository.findById(id);
    }
}