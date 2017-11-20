/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import ModeloCliente.MLogin;
import VistaCliente.VLogin;
/**
 *
 * @author Skynet
 */
public class Main {
    public static void main(String[] args) 
    {
        VLogin vl = new VLogin();        
        MLogin ml = new MLogin();
        CLogin cl = new CLogin(vl,ml);
        cl.iniciarInterfazGrafica();
    }
}
