package com.example.ResumeGeneratorBackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T,ID> extends CrudRepository<T, ID> {
    // returns all existing instances T associated with a user
    public List<T> findAllByUserUsername(String username);    

    public void deleteById(int id);

    public void deleteByIdAndUserUsername(int id, String username);

}
