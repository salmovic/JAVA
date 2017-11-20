package Acciones;


/**
* Acciones/OperacionesPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Acciones.idl
* jueves 26 de mayo de 2016 12:02:52 AM CDT
*/


//interface de operaciones
public abstract class OperacionesPOA extends org.omg.PortableServer.Servant
 implements Acciones.OperacionesOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("setCliente", new java.lang.Integer (0));
    _methods.put ("updateCliente", new java.lang.Integer (1));
    _methods.put ("deleteCliente", new java.lang.Integer (2));
    _methods.put ("getLoginCliente", new java.lang.Integer (3));
    _methods.put ("getLoginAdmin", new java.lang.Integer (4));
    _methods.put ("getClienteBy", new java.lang.Integer (5));
    _methods.put ("getCliente", new java.lang.Integer (6));
    _methods.put ("mensaje", new java.lang.Integer (7));
    _methods.put ("getSaldo", new java.lang.Integer (8));
    _methods.put ("retiro", new java.lang.Integer (9));
    _methods.put ("depositar", new java.lang.Integer (10));
    _methods.put ("tiempoAire", new java.lang.Integer (11));
    _methods.put ("pagoServicio", new java.lang.Integer (12));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {

  //insertar
       case 0:  // Acciones/Operaciones/setCliente
       {
         Acciones.Cliente dts = Acciones.ClienteHelper.read (in);
         boolean $result = false;
         $result = this.setCliente (dts);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  //actualizar
       case 1:  // Acciones/Operaciones/updateCliente
       {
         Acciones.Cliente dts = Acciones.ClienteHelper.read (in);
         boolean $result = false;
         $result = this.updateCliente (dts);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  //eliminar
       case 2:  // Acciones/Operaciones/deleteCliente
       {
         String id = in.read_string ();
         boolean $result = false;
         $result = this.deleteCliente (id);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  //login cliente
       case 3:  // Acciones/Operaciones/getLoginCliente
       {
         String noc = in.read_string ();
         String nip = in.read_string ();
         boolean $result = false;
         $result = this.getLoginCliente (noc, nip);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  //login admin
       case 4:  // Acciones/Operaciones/getLoginAdmin
       {
         String usr = in.read_string ();
         String pass = in.read_string ();
         boolean $result = false;
         $result = this.getLoginAdmin (usr, pass);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  //buscar cliente
       case 5:  // Acciones/Operaciones/getClienteBy
       {
         String txt = in.read_string ();
         Acciones.Cliente $result[] = null;
         $result = this.getClienteBy (txt);
         out = $rh.createReply();
         Acciones.liClienteHelper.write (out, $result);
         break;
       }


  //obtener todos los clientes
       case 6:  // Acciones/Operaciones/getCliente
       {
         Acciones.Cliente $result[] = null;
         $result = this.getCliente ();
         out = $rh.createReply();
         Acciones.liClienteHelper.write (out, $result);
         break;
       }

       case 7:  // Acciones/Operaciones/mensaje
       {
         String nom = in.read_string ();
         String op = in.read_string ();
         String tipUs = in.read_string ();
         String desk = in.read_string ();
         String ip = in.read_string ();
         this.mensaje (nom, op, tipUs, desk, ip);
         out = $rh.createReply();
         break;
       }


  //OPERACIONES
       case 8:  // Acciones/Operaciones/getSaldo
       {
         String noCuenta = in.read_string ();
         double $result = (double)0;
         $result = this.getSaldo (noCuenta);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 9:  // Acciones/Operaciones/retiro
       {
         String no = in.read_string ();
         double cant = in.read_double ();
         boolean $result = false;
         $result = this.retiro (no, cant);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 10:  // Acciones/Operaciones/depositar
       {
         String no = in.read_string ();
         double cant = in.read_double ();
         boolean $result = false;
         $result = this.depositar (no, cant);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 11:  // Acciones/Operaciones/tiempoAire
       {
         String no = in.read_string ();
         double cant = in.read_double ();
         boolean $result = false;
         $result = this.tiempoAire (no, cant);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 12:  // Acciones/Operaciones/pagoServicio
       {
         String no = in.read_string ();
         double cant = in.read_double ();
         boolean $result = false;
         $result = this.pagoServicio (no, cant);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Acciones/Operaciones:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Operaciones _this() 
  {
    return OperacionesHelper.narrow(
    super._this_object());
  }

  public Operaciones _this(org.omg.CORBA.ORB orb) 
  {
    return OperacionesHelper.narrow(
    super._this_object(orb));
  }


} // class OperacionesPOA
