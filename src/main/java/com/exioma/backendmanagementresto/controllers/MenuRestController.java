package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.MenuRequestDTO;
import com.exioma.backendmanagementresto.dto.MenuResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.domain.Menu;
import com.exioma.backendmanagementresto.model.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired
    private IMenuService menuService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<MenuResponseDTO> getAll(){
        List<MenuResponseDTO> menuList = menuService.findAll().stream().map(MenuResponseDTO::new).toList();

        if (!menuList.isEmpty()) {
            return menuList;
        } else {
            // Lanza una excepción personalizada
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<MenuResponseDTO> getMenuById(@PathVariable Long id) {
        Optional<Menu> menuOptional = Optional.ofNullable(menuService.findById(id));

        if (menuOptional.isPresent()) {
            MenuResponseDTO menuResponseDTO = new MenuResponseDTO(menuOptional.get());
            return ResponseEntity.ok(menuResponseDTO);
        } else {
            throw new RegistroNoEncontradoException("No se encontró el registro con ID: " + id);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<MenuResponseDTO> saveMenu(@RequestBody MenuRequestDTO data) {
        Menu foodData = new Menu(data);
        menuService.save(foodData);

        MenuResponseDTO responseDTO = new MenuResponseDTO(foodData);
        responseDTO.setMessage("Menu saved successfully");

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) {
        menuService.deleteById(id);
        return ResponseEntity.ok("Éxito: El alimento con ID " + id + " ha sido eliminado.");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public String editMenu(@PathVariable Long id, @RequestBody MenuRequestDTO data) {
        // Verificar si el alimento con el ID proporcionado existe en la base de datos
        Optional<Menu> existingMenu = Optional.ofNullable(menuService.findById(id));

        if (existingMenu.isPresent()) {
            // Si existe, actualizar sus datos
            Menu manuToUpdate = existingMenu.get();
            manuToUpdate.setDetail(data.detail());
            manuToUpdate.setPrice(data.price());
            manuToUpdate.setTitle(data.title());
            manuToUpdate.setImagen(data.imagen());

            menuService.save(manuToUpdate);

            return "Éxito: El alimento con ID " + id + " ha sido actualizado.";

        } else {
            // Manejar el caso en el que no se encuentra el alimento con el ID proporcionado
            // Devolver un mensaje de error
            return "Error: No se encontró el alimento con ID " + id + ".";
        }
    }

}
