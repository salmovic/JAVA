/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorServer;


import ModeloServer.MLogin;
import ModeloServer.MRegistrar;
import ModeloServer.MServidor;
import VistaServer.VLogin;
import VistaServer.VRegistrar;
import VistaServer.VServidor;

/**
 *
 * @author Skynet
 */
public class Main {
    public static void main(String[] args)
    {
        VLogin vL = new VLogin();
        MLogin mL = new MLogin();
        CLogin cL = new CLogin(mL, vL);
        cL.iniciarInterfaz();
    }
}
