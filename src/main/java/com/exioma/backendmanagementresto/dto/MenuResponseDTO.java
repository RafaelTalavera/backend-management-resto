package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Menu;

public record MenuResponseDTO(
       Long id,
       String imagen,
       String title,
       String detail,
       Double price
) {
    public MenuResponseDTO(Menu menu){
        this(menu.getId(), menu.getImagen(), menu.getTitle(), menu.getDetail(), menu.getPrice());
    }

    public void setMessage(String menuSavedSuccessfully) {

    }
}
