/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import Common.DtsEmpleadoCliente;
import ModeloCliente.MAdministrador;
import ModeloCliente.MCliente;
import ModeloCliente.MLogin;
import VistaCliente.VAdministrador;
import VistaCliente.VCliente;
import VistaCliente.VLogin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CLogin implements ActionListener
{
    DtsEmpleadoCliente dtsEC = new DtsEmpleadoCliente();
    
    VLogin vl;
    MLogin ml;

    public CLogin(VLogin vl,MLogin ml) 
    {
        this.vl = vl;
        this.ml = ml;
        //EVENTO CLIK
        this.vl.btnIniciar.addActionListener(this);
        this.vl.btnSalir1.addActionListener(this);
        this.vl.btnEntrarAdmin.addActionListener(this);
        this.vl.btnEntrarCliente.addActionListener(this);
    }
    void iniciarInterfazGrafica()
    {          
        vl.setVisible(true);
    }
    //EVENTO CLICK DE LOS BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON INICIAR
        if (e.getSource()==vl.btnIniciar) {
            new RMI();
        }
        //BOTON SALIR
        if (e.getSource()==vl.btnSalir1) {
            System.exit(0);
        }
        //LOGIN ADMINISTRADOR
        if (e.getSource()==vl.btnEntrarAdmin) 
        {
            if (vl.txtUsuarioAdmi.getText().equals("")) {
                vl.txtUsuarioAdmi.setBackground(Color.red);
                vl.txtUsuarioAdmi.requestFocus();
                return;
            }
            if (vl.txtPassAdmin.getText().equals("")) {
                vl.txtPassAdmin.setBackground(Color.red);
                vl.txtPassAdmin.requestFocus();
                return;
            }            
            if (ml.logingAdmin(vl.txtUsuarioAdmi.getText(),vl.txtPassAdmin.getText())) 
            {        
              vl.dispose();
            VAdministrador va = new VAdministrador();
            MAdministrador ma = new MAdministrador();
            CAministrador ca = new CAministrador(va, ma);
            ca.inicarIG();
            }else
            {
                JOptionPane.showMessageDialog(vl, "Verifique sus datos");
            }
        }
        //LOGIN CLIENTE 
        if (e.getSource()==vl.btnEntrarCliente) 
        {
            if (vl.txtNoCuenta.getText().equals("")) {
                vl.txtNoCuenta.setBackground(Color.red);
                vl.txtNoCuenta.requestFocus();
                return;
            }
            if (vl.txtNipCliente.getText().equals("")) {
                vl.txtNipCliente.setBackground(Color.red);
                vl.txtNipCliente.requestFocus();
                return;
            }
            dtsEC.setNoCuenta(vl.txtNoCuenta.getText());
            dtsEC.setNip(vl.txtNipCliente.getText());
            if (ml.loginCliente(dtsEC)) 
            {
                vl.dispose();
                MCliente mc = new MCliente();
                VCliente vc = new VCliente();
                CCliente cc = new CCliente(vc, mc,vl.txtNoCuenta.getText());
                cc.inicarIGClinete();
            }else
            {
                JOptionPane.showMessageDialog(vl, "Verifique sus datos!");
            }
        }
    }
    
}
