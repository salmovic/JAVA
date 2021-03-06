package Acciones;


/**
* Acciones/OperacionesOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Acciones.idl
* jueves 26 de mayo de 2016 12:02:55 AM CDT
*/


//interface de operaciones
public interface OperacionesOperations 
{

  //insertar
  boolean setCliente (Acciones.Cliente dts);

  //actualizar
  boolean updateCliente (Acciones.Cliente dts);

  //eliminar
  boolean deleteCliente (String id);

  //login cliente
  boolean getLoginCliente (String noc, String nip);

  //login admin
  boolean getLoginAdmin (String usr, String pass);

  //buscar cliente
  Acciones.Cliente[] getClienteBy (String txt);

  //obtener todos los clientes
  Acciones.Cliente[] getCliente ();
  void mensaje (String nom, String op, String tipUs, String desk, String ip);

  //OPERACIONES
  double getSaldo (String noCuenta);
  boolean retiro (String no, double cant);
  boolean depositar (String no, double cant);
  boolean tiempoAire (String no, double cant);
  boolean pagoServicio (String no, double cant);
} // interface OperacionesOperations
