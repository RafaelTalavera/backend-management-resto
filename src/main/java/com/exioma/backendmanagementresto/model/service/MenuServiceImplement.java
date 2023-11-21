package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IMenuDao;
import com.exioma.backendmanagementresto.model.domain.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImplement implements IMenuService{

    @Autowired
    private IMenuDao menuDao;

    @Transactional(readOnly = true)
    @Override
    public List<Menu> findAll() {
        return (List<Menu>) menuDao.findAll();
    }

    @Transactional
    @Override
    public void save(Menu menu) {
         menuDao.save(menu);
    }

    @Override
    @Transactional(readOnly = true)
    public Menu findById(Long id) {
        return menuDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {menuDao.deleteById(id);}
}
