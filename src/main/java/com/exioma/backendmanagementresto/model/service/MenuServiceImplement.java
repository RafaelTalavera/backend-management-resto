package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IMenuDao;
import com.exioma.backendmanagementresto.model.domain.Menu;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuServiceImplement implements IMenuService{

    @Autowired
    private IMenuDao menuDao;

    @Transactional
    @Override
    public List<Menu> findAll() {
        return (List<Menu>) menuDao.findAll();
    }

    @Override
    @Transactional
    public void save(Menu menu) {
         menuDao.save(menu);
    }

    @Override
    @Transactional
    public Menu findById(Long id) {
        return menuDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       menuDao.deleteById(id);
    }
}
