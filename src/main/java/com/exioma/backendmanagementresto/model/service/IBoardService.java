package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.domain.Board;

import java.util.List;

public interface IBoardService {

    public List<Board> findAll();

    public void save(Board board);

    public Board findById(Long id);

    public void deleteById(Long id);
}
