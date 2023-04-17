package com.mirai.importback.services.impl;

import com.mirai.importback.entities.StoreProducts;
import com.mirai.importback.repositories.IStoreProductsRepository;
import com.mirai.importback.services.IStoreProductsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StoreProductsImpl implements IStoreProductsService {
    private final IStoreProductsRepository storeProductsRepository;

    public StoreProductsImpl(IStoreProductsRepository storeProductsRepository) {
        this.storeProductsRepository = storeProductsRepository;
    }

    @Override
    @Transactional
    public StoreProducts save(StoreProducts storeProducts) throws Exception {
        return storeProductsRepository.save(storeProducts);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        storeProductsRepository.deleteById(id);
    }

    @Override
    public List<StoreProducts> getAll() throws Exception {
        return storeProductsRepository.findAll();
    }

    @Override
    public Optional<StoreProducts> getById(Long id) throws Exception {
        return storeProductsRepository.findById(id);
    }
}
