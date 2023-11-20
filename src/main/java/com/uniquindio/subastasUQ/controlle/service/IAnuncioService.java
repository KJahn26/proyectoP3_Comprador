package com.uniquindio.subastasUQ.controlle.service;

import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.model.Anuncio;

public interface IAnuncioService {
    public boolean guardarProducto(ProductoDto productoDto);
    public boolean guardarAnuncio(AnuncioDto anunciodto);

}
