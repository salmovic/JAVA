package Acciones;


/**
* Acciones/Cliente.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Acciones.idl
* jueves 26 de mayo de 2016 12:02:52 AM CDT
*/

public final class Cliente implements org.omg.CORBA.portable.IDLEntity
{
  public String nombre = null;
  public String apellidos = null;
  public String direccion = null;
  public String noCuenta = null;
  public String nip = null;
  public double saldo = (double)0;
  public byte foto[] = null;
  public int lenFoto = (int)0;

  public Cliente ()
  {
  } // ctor

  public Cliente (String _nombre, String _apellidos, String _direccion, String _noCuenta, String _nip, double _saldo, byte[] _foto, int _lenFoto)
  {
    nombre = _nombre;
    apellidos = _apellidos;
    direccion = _direccion;
    noCuenta = _noCuenta;
    nip = _nip;
    saldo = _saldo;
    foto = _foto;
    lenFoto = _lenFoto;
  } // ctor

} // class Cliente
