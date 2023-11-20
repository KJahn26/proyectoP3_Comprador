package com.uniquindio.subastasUQ.mapping.mappings;

import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.model.Anuncio;
import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.Puja;
import com.uniquindio.subastasUQ.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SubastaMapper {
    SubastaMapper INSTANCE = Mappers.getMapper(SubastaMapper.class);

    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

    //@Named("ProductoToProductoDto")
    ProductoDto ProductoToproductoDto(Producto producto);

    Producto productoDtoToProducto(ProductoDto productoDto);

    //@IterableMapping(qualifiedByName = "productoToProductoDto")
    List<ProductoDto> getProductosDto(ArrayList<Producto> listaproductos);

    PujaDto PujaToPujaDto(Puja puja);

    Puja PujaDtoToPuja (PujaDto pujaDto);
    List<PujaDto> getPujasDto(ArrayList<Puja> listaProductosPuja);

    List<AnuncioDto> getANuncioDTo (ArrayList<Anuncio> anuncios);
    AnuncioDto anuciotoanuncioDto (Anuncio anuncio);
    Anuncio anuncioDtoToAnuncio (AnuncioDto anunciodto);

}
