package com.exioma.backendmanagementresto.model.dao;

import com.exioma.backendmanagementresto.model.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface IBoardDao extends CrudRepository<Board, Long> {
}
