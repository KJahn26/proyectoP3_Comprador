package com.uniquindio.subastasUQ.mapping.dto;

public record ProductoDto(
        String nombreProducto,

        String tipoProducto,

        String descProducto,

        String anunciante,
        String cedulaAnunciante,
        String valorInicial,
        String fechaPublicacion,
        String fechaTerminarPublicacion,
        String fechaAdquirido,
        String cedulaAdquisicion


) {
}
