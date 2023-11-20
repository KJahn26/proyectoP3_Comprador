package com.uniquindio.subastasUQ.mapping.dto;

public record PujaDto(
        String nombreComprador,

        String cedulaComprador,
        String nombreProducto,

        String nombreAnunciante,
        String cedulaAnunciante,

        String valorPuja,

        String fechaFinal) {
}
