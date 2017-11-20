/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloServer;
import BD.EmpladoServidor;
import DatosServer.DtsEmpleadoServer;
/**
 *
 * @author Skynet
 */
public class MLogin 
{
    public MLogin(){}
    public boolean getLogin(DtsEmpleadoServer dts)
    {
        return new EmpladoServidor().getLogin(dts);
    }
}
