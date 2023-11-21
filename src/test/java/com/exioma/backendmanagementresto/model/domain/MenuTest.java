package com.exioma.backendmanagementresto.model.domain;

import com.exioma.backendmanagementresto.dto.MenuRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class MenuTest {

    @Mock
    private MenuRequestDTO menuRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMenuCreationFromDTO() {
        // Configurar el comportamiento simulado del DTO
        when(menuRequestDTO.imagen()).thenReturn("imagen");
        when(menuRequestDTO.title()).thenReturn("title");
        when(menuRequestDTO.detail()).thenReturn("detail");
        when(menuRequestDTO.price()).thenReturn(10.5);

        // Crear un objeto Menu a partir del DTO simulado
        Menu menu = new Menu(menuRequestDTO);

        // Verificar que los campos del menú se hayan establecido correctamente
        assertEquals("imagen", menu.getImagen());
        assertEquals("title", menu.getTitle());
        assertEquals("detail", menu.getDetail());
        assertEquals(10.5, menu.getPrice());
    }

    @Test
    void testMenuSerialization() {
        // Crear un objeto Menu para probar la serialización
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setImagen("imagen");
        menu.setTitle("title");
        menu.setDetail("detail");
        menu.setPrice(10.5);

        // Verificar que la serialización funciona correctamente
        assertEquals(1L, menu.getId());
        assertEquals("imagen", menu.getImagen());
        assertEquals("title", menu.getTitle());
        assertEquals("detail", menu.getDetail());
        assertEquals(10.5, menu.getPrice());
    }

}
