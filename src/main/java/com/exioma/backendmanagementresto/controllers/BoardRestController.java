package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.BoardResponseDTO;
import com.exioma.backendmanagementresto.dto.MenuResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.domain.Board;
import com.exioma.backendmanagementresto.model.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDTO> getBoardById(@PathVariable long id){
        Optional<Board> boardOptional = Optional.ofNullable(boardService.findById(id));

        if (boardOptional.isPresent()){
            BoardResponseDTO boardResponseDTO = new BoardResponseDTO(boardOptional.get());
            return ResponseEntity.ok(boardResponseDTO);
        } else {
            throw new RegistroNoEncontradoException("No se encontró el registros con ID: " +id);
        }
    }
}
