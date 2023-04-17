package com.mirai.importback.services.impl;

import com.mirai.importback.entities.Users;
import com.mirai.importback.repositories.IUsersRepository;
import com.mirai.importback.services.IUsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly =true)
public class UsersServiceImpl implements IUsersService {

    //@Autowired
    //private IUsersRepository usersRepository;
    private final IUsersRepository usersRepository;

    public UsersServiceImpl(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public Users save(Users users) throws Exception {
        return usersRepository.save(users);
    }
    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        usersRepository.deleteById(id);
    }
    @Override
    public List<Users> getAll() throws Exception {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getById(Long id) throws Exception {
        return usersRepository.findById(id);
    }

 /*   @Override
    public Users findByDni(String dni) throws Exception {
        return usersRepository.findByDni(dni);
    }

@Override
    public List<Users> findByLastName(String lastname) throws Exception {
        return usersRepository.findByLastName(lastname);
    }

    @Override
    public List<Users> findByFirstAndLastName(String firstname, String lastname) throws Exception {
        return usersRepository.findByFirstAndLastName(firstname,lastname);
    }

    @Override
    public List<Users> findByFirstName(String firstname) throws Exception {
        return usersRepository.findByFirstName(firstname);
    }*/
}
