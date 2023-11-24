package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.BoardRequestDTO;
import com.exioma.backendmanagementresto.dto.BoardResponseDTO;
import com.exioma.backendmanagementresto.dto.MenuResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.domain.Board;
import com.exioma.backendmanagementresto.model.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @CrossOrigin (origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<BoardResponseDTO> saveBoard(@RequestBody BoardRequestDTO data){
        Board boardData = new Board(data);
        boardService.save(boardData);
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(boardData);
        return new ResponseEntity<>(boardResponseDTO, HttpStatus.CREATED);
    }

    @CrossOrigin (origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public String editBoard (@PathVariable Long id,@RequestBody BoardRequestDTO data){
        Optional<Board> existingBoard = Optional.ofNullable(boardService.findById(id));
        if (existingBoard.isPresent()){
            Board boardUpdate = existingBoard.get();
            boardUpdate.setChair(data.chair());
            boardUpdate.setCondition(data.condition());
            boardService.save(boardUpdate);
            return "Éxito: El alimento con ID " + id + " ha sido actualizado.";
        } else {
           return "Error: No se encontró el alimento con ID " + id + ".";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
        return ResponseEntity.ok("Éxito: La mesa con ID " + id + " ha sido eliminado.");
    }
}
