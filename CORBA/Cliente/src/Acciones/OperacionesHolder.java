package Acciones;

/**
* Acciones/OperacionesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Acciones.idl
* jueves 26 de mayo de 2016 12:02:55 AM CDT
*/


//interface de operaciones
public final class OperacionesHolder implements org.omg.CORBA.portable.Streamable
{
  public Acciones.Operaciones value = null;

  public OperacionesHolder ()
  {
  }

  public OperacionesHolder (Acciones.Operaciones initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Acciones.OperacionesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Acciones.OperacionesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Acciones.OperacionesHelper.type ();
  }

}
