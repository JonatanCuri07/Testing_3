package com.mirai.importback.services.impl;

import com.mirai.importback.entities.Orders;
import com.mirai.importback.repositories.IOrdersRepository;
import com.mirai.importback.services.IOrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersServiceImpl implements IOrdersService {

    private final IOrdersRepository ordersRepository;

    public OrdersServiceImpl(IOrdersRepository ordersRepository) {this.ordersRepository = ordersRepository;}

    @Override
    @Transactional
    public Orders save(Orders orders) throws Exception {
        return ordersRepository.save(orders);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        ordersRepository.deleteById(id);
    }

    @Override
    public List<Orders> getAll() throws Exception {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> getById(Long id) throws Exception {
        return ordersRepository.findById(id);
    }
}