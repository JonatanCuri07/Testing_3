package com.mirai.importback.services.impl;

import com.mirai.importback.entities.TravelerOrders;
import com.mirai.importback.repositories.ITravelerOrdersRepository;
import com.mirai.importback.services.ITravelerOrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly =true)
public class TravelerOrdersServiceImpl implements ITravelerOrdersService {

    private final ITravelerOrdersRepository travelerOrdersRepository ;

    public TravelerOrdersServiceImpl(ITravelerOrdersRepository travelerOrdersRepository) {
        this.travelerOrdersRepository = travelerOrdersRepository;
    }


    @Override
    @Transactional
    public TravelerOrders save(TravelerOrders travelerOrders) throws Exception {
        return travelerOrdersRepository.save(travelerOrders);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        travelerOrdersRepository.deleteById(id);
    }

    @Override
    public List<TravelerOrders> getAll() throws Exception {
        return travelerOrdersRepository.findAll();
    }

    @Override
    public Optional<TravelerOrders> getById(Long id) throws Exception {
        return travelerOrdersRepository.findById(id);
    }
}
