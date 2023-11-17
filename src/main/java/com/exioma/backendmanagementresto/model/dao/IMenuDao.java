package com.exioma.backendmanagementresto.model.dao;

import com.exioma.backendmanagementresto.model.domain.Menu;
import org.springframework.data.repository.CrudRepository;

public interface IMenuDao extends CrudRepository<Menu,Long> {
}
