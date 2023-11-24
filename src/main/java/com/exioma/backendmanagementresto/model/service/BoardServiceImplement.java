package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IBoardDao;
import com.exioma.backendmanagementresto.model.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BoardServiceImplement implements IBoardService{

    @Autowired
    private IBoardDao boardDao;

    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll() {return (List<Board>) boardDao.findAll();}

    @Override
    @Transactional
    public void save(Board board) {boardDao.save(board);}

    @Override
    @Transactional(readOnly = true)
    public Board findById(Long id) {
        return boardDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {boardDao.deleteById(id);}
}
