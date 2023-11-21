package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.BoardResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    @Autowired
    private IBoardService boardService;

    @GetMapping
    public List<BoardResponseDTO> getAll(){
        List<BoardResponseDTO> boardList = boardService.findAll().stream().map(BoardResponseDTO::new).toList();

        if (!boardList.isEmpty()){
            return boardList;
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }
}
