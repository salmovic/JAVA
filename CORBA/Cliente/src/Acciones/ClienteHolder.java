package Acciones;

/**
* Acciones/ClienteHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Acciones.idl
* jueves 26 de mayo de 2016 12:02:55 AM CDT
*/

public final class ClienteHolder implements org.omg.CORBA.portable.Streamable
{
  public Acciones.Cliente value = null;

  public ClienteHolder ()
  {
  }

  public ClienteHolder (Acciones.Cliente initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Acciones.ClienteHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Acciones.ClienteHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Acciones.ClienteHelper.type ();
  }

}
