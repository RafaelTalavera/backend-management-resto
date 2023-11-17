package com.exioma.backendmanagementresto.model.service;


import com.exioma.backendmanagementresto.model.domain.Menu;

import java.util.List;

public interface IMenuService {

    public List<Menu> findAll();

    public void save(Menu menu);

    public Menu findById(Long id);

    public void deleteById(Long id);
}
