package com.uniquindio.subastasUQ.controlle;

import com.rabbitmq.client.DeliverCallback;
import com.uniquindio.subastasUQ.config.RabbitFactory;
import com.uniquindio.subastasUQ.exceptions.*;
import com.uniquindio.subastasUQ.controlle.service.iModelFactoryController;
import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.mapping.mappings.SubastaMapper;
import com.uniquindio.subastasUQ.model.*;
import com.uniquindio.subastasUQ.utils.ArchivoUtil;
import com.uniquindio.subastasUQ.utils.Persistencia;
import com.uniquindio.subastasUQ.utils.subastaUqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ModelFactoryController implements iModelFactoryController,Runnable {

    SubastaUq subastaUq;

    SubastaMapper mapper = SubastaMapper.INSTANCE;

    public final String QUEUE ="persistenciaUsuario";

    String fecha="";

    String cedulaAnunciante ="";

    String nombreProducto="";

    String cedulaComprador ="";

    RabbitFactory rabbitFactory;

    ConnectionFactory connectionFactory;

    Thread hiloServicioUsuarios;

    Thread hiloServicioProductos;

    Thread hiloprueba;

    public String getCedulaAnunciante() {
        return cedulaAnunciante;
    }

    public void setCedulaAnunciante(String cedulaAnunciante) {
        this.cedulaAnunciante = cedulaAnunciante;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCedulaComprador() {
        return cedulaComprador;
    }

    public void setCedulaComprador(String cedulaComprador) {
        this.cedulaComprador = cedulaComprador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {

        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");
        cargarDatosBase();
        //agregarDatos();
        cargarResourceXML();
        //cargarDatosArchivos();
        //guardarResourceXML();
        //salvaGuardarDatosPrueba();
        //initRabbitConnection();




        if (subastaUq == null) {
            cargarDatosBase();
            guardarResourceXML();
            salvaGuardarDatosPrueba();
        }
        registrarAccionesSistema("Sin identificar Tipo de Usuario ", 1, "inicio el programa","InicioSesion");
    }

    @Override
    public void run(){
        Thread currentThread= Thread.currentThread();
        if(hiloServicioUsuarios==currentThread){
            consumirUsuarios();
        }
        if(hiloServicioProductos==currentThread){
            consumirProductos();
        }

    }

    public void consumirUsuarios() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido: " + message);
                Usuario us= new Usuario();
                us.setNombre(message.split("@")[0]);
                us.setCedula(message.split("@")[1]);
                us.setTelefono(message.split("@")[2]);
                us.setDireccion(message.split("@")[3]);
                us.setEmail(message.split("@")[4]);
                us.setContrasena(message.split("@")[5]);
                try {
                    if(us.verificarUsuarioExistente(message.split("@")[1])){
                        getSubasta().agregarUsuario(us);
                        guardarResourceXML();
                        cargarResourceXML();
                    }
                } catch (UsuarioException e) {
                    throw new RuntimeException(e);
                }
            };
            while (true) {
                channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumirProductos(){
        try {
            System.out.println("llegue primero");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);
            System.out.println("llegue");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido: " + message);
                Producto pr= new Producto();
                pr.setNombreProducto(message.split("@")[0]);
                pr.setTipoProducto(message.split("@")[1]);
                pr.setDescProducto(message.split("@")[2]);
                pr.setAnunciante(message.split("@")[3]);
                pr.setValorInicial(message.split("@")[4]);
                pr.setFechaPublicacion(message.split("@")[5]);
                pr.setFechaTerminarPublicacion(message.split("@")[6]);
                pr.setFechaAdquirido(message.split("@")[7]);
                try {

                        getSubasta().agregarProducto(pr);
                        guardarResourceXML();
                        cargarResourceXML();


                } catch (ProductoException e) {
                    throw new RuntimeException(e);
                }
            };
            while (true) {
                channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> { });
            }

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public String getNombreAnunciante(){
        return subastaUq.getNombre(cedulaAnunciante);
    }

    public String getNombreComprador(){
        return subastaUq.getNombre(cedulaComprador);
    }




    private void initRabbitConnection() {
        rabbitFactory = new RabbitFactory();
        connectionFactory = rabbitFactory.getConnectionFactory();
        System.out.println("conexion establecida con rabbitMQ");
    }

    public void consumirServicioUsuarios(){
        hiloServicioUsuarios = new Thread(this);
        hiloServicioUsuarios.start();
    }

    public void consumirServicioProductos(){
        this.hiloServicioProductos= new Thread(this);
        this.hiloServicioProductos.start();
    }

    public void producirUsuarios(String message){
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);
            channel.basicPublish("", QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void producirProductos(String message){
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);
            channel.basicPublish("", QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvaGuardarDatosPrueba() {

        Persistencia.guardarUsuarios(getSubasta().getListaUsuarios());
    }

    private void cargarDatosBase() {

        subastaUq = subastaUqUtils.inicializarDatos();
    }
    @Override
    public void cargarDatosArchivos() {
        subastaUq = new SubastaUq();
        try {
            Persistencia.cargarDatosArchisvos(subastaUq);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public SubastaUq getSubasta() {

        return subastaUq;
    }
    public void setSubastaUq(SubastaUq SubastaUq) {

        this.subastaUq = SubastaUq;
    }
    @Override
    public List<UsuarioDto> obtenerUsuarios() {

        return mapper.getUsuariosDto(subastaUq.getListaUsuarios());
    }
    public List<AnuncioDto> obtenerAnuncios ( )
    {
        return mapper.getANuncioDTo(subastaUq.getListaAnuncios());
    }
    public ArrayList<Usuario> coger() {
        return subastaUq.getListaUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try {
            if (!subastaUq.verificarUsuarioExistente(usuarioDto.cedula())) {
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSubasta().agregarUsuario(usuario);
                String msj=usuario.getNombre() + "@" + usuario.getCedula() + "@" + usuario.getTelefono() +
                        "@" + usuario.getDireccion() + "@" + usuario.getEmail() + "@" + usuario.getContrasena() + "\n";
                //producirUsuarios(msj);
                guardarResourceXML();
                cargarResourceXML();
                registrarAccionesSistema("Sin identificar", 1, "agrego a un usuario","RegistroUsuario");
            }
            return true;
        } catch (UsuarioException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean eliminarUusario(String cedula) {
        boolean flagExiste = false;
        try {
            flagExiste = subastaUq.eliminarUsuario(cedula);
            registrarAccionesSistema("Sin identificar", 2, "elimino a un usuario","AdministracionUsuarios");
            //guardarResourceXML();
            System.out.println("El usuario se ah eliminado correctamente");

        } catch (UsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarUsuario(String cedulaActual, UsuarioDto usuriosDto) {
        try {
            Usuario usuario = mapper.usuarioDtoToUsuario(usuriosDto);
            getSubasta().actualizarUsuario(cedulaActual, usuario);
            registrarAccionesSistema("Sin identificar", 2, "Actualizo a un usuario","AdministracionUsuarios");
            return true;
        } catch (UsuarioException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean buscarEmpleado() {
        return false;
    }

    private void cargarResourceXML() {
        subastaUq = Persistencia.cargarRecursoBancoXML();
    }

    private void guardarResourceXML() {

        Persistencia.guardarRecursoBancoXML(subastaUq);
    }

    private void cargarResourceBinario() {
        subastaUq = Persistencia.cargarRecursoBancoBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoBancoBinario(subastaUq);
    }

    public void registrarAccionesSistema(String tipoUsuario, int nivel, String accion,String interfaz) {
        Persistencia.guardaRegistroLog(tipoUsuario, nivel, accion,interfaz);
    }

    public List<ProductoDto> obtenerProductos(boolean f) {

        if(f){
            return mapper.getProductosDto(subastaUq.getListaproductos(cedulaAnunciante));
        }else{
            return mapper.getProductosDto(subastaUq.getListaproductos());
        }
    }
    public List<ProductoDto> obtenerProductosAdquiridos() {
        return mapper.getProductosDto(subastaUq.getListaProductosAdquiridos(cedulaComprador));
    }

    public List<Anuncio> cogerAnuncios ()
    {
        return subastaUq.getListaAnuncios();
    }

    public void setvalorpuja(String nombreProducto,String cedulaAnunciante,String valorpuja){
         subastaUq.setValorinicial(nombreProducto,cedulaAnunciante,valorpuja);
    }


    public boolean eliminarProducto(String nombre) {
        boolean flag = false;
        try {
            flag = subastaUq.eliminarProducto(nombre);
            guardarResourceXML();
            cargarResourceXML();
            registrarAccionesSistema("Anunciante", 2, "elimino a un producto debido a su compra o retiro por parte del propietario","publicaciones");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String cogerFecha() {

        return ArchivoUtil.cargarFechaSistema();
    }
    public boolean agregarProducto (ProductoDto productoDto) {
        boolean centinela=false;
        Producto producto = mapper.productoDtoToProducto(productoDto);
        if (!subastaUq.verificarProductoExiste(producto))
        {
            try {
                subastaUq.agregarProducto(producto);
                String msg=producto.getNombreProducto()+"@"+producto.getTipoProducto()+"@"+producto.getDescProducto()+"@"+producto.getAnunciante()+"@"+producto.getValorInicial()
                        +"@"+producto.getFechaPublicacion()+"@"+producto.getFechaTerminarPublicacion()+"@"+producto.getFechaAdquirido();
                producirProductos(msg);
                centinela=true;
                guardarResourceXML();
                cargarResourceXML();
                registrarAccionesSistema("Anunciante",1,"agrego un Producto","CrearAnuncio");
            } catch (ProductoException e) {
                throw new RuntimeException(e);
            }


        }
      return centinela;
    }

   public List<PujaDto> obtenerProductosPuja(boolean flag){
        if(flag){
            return mapper.getPujasDto(subastaUq.getListaProductosPuja(nombreProducto,cedulaAnunciante));
        }else{
            return mapper.getPujasDto(subastaUq.getListaProductosPuja(cedulaComprador));
        }
   }

   public boolean eliminarPuja(String nombre,String cedulaC,String cedulaA){
        boolean flag=false;
        try{
            flag= subastaUq.eliminarPuja(nombre,cedulaC,cedulaA);
            //guardarResourceXML();
            registrarAccionesSistema("Comprador",2,"Elimino una puja","pujas");
        }catch(Exception e){
            e.printStackTrace();
        }
   return flag;}

    public boolean agregarPuja(PujaDto pujaDto){
        try{
            if(!subastaUq.verificarCantidadPujas(pujaDto.cedulaAnunciante())) {
                Puja puja = mapper.PujaDtoToPuja(pujaDto);
                subastaUq.agregarPuja(puja);
                //guardarResourceXML();
                registrarAccionesSistema("Comprador", 1, "realizo una puja","pujas");
            }
                return true;
            }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

public boolean agregarAnuncio (AnuncioDto anuncioDto)
{
    boolean centinela=false;
    Anuncio anuncio = mapper.anuncioDtoToAnuncio(anuncioDto);
    if (anuncio!=null)
    {
         try {
                subastaUq.agregarAnuncio(anuncio);
                centinela=true;
                guardarResourceXML();
                cargarResourceXML();
                registrarAccionesSistema("Anunciante",1,"agrego un Producto","CrearAnuncio");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
    return centinela;

}

    public SubastaUq getSubastaUq() {
        return subastaUq;
    }

    public SubastaMapper getMapper() {
        return mapper;
    }

    public void setMapper(SubastaMapper mapper) {
        this.mapper = mapper;
    }

    public void compraProducto(String nombreP, String cedulaC, String cedulaA) throws pujaException {
            subastaUq.agregarProductoAdquirido(nombreP,cedulaA,cedulaC);
            boolean f=subastaUq.eliminarPuja(nombreP,cedulaC,cedulaA);
            //guardarResourceXML();
    }

}


