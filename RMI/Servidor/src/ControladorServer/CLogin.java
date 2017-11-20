/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorServer;

import DatosServer.DtsEmpleadoServer;
import ModeloServer.MLogin;
import ModeloServer.MRegistrar;
import ModeloServer.MServidor;
import VistaServer.VLogin;
import VistaServer.VRegistrar;
import VistaServer.VServidor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CLogin implements ActionListener{
    MLogin mL;//Modelo Login
    VLogin vL;//Vista o InterfazGrafica Login
    
    DtsEmpleadoServer dtsES;//Datso del empleado    
    
    public CLogin(MLogin mlog,VLogin vlog)
    {
        this.mL = mlog;
        this.vL = vlog;
        this.dtsES = new DtsEmpleadoServer();
        
        vL.btnIniciarSesion.addActionListener(this);
        vL.btnSalir.addActionListener(this);
        
        vL.btnIniciarSesion.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e) 
            {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) 
                {                    
                    login();
                }
            }
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        /*boton*/
        if (e.getSource()==vL.btnIniciarSesion) 
        {
            
            login();
        }
        /*Botono Salir*/
        if (e.getSource()==vL.btnSalir) {
            System.exit(0);
        }
    }
    /**
     * Funcion que me permite inicar la interfaz grafica del usuario
     */
    void iniciarInterfaz()
    {
        vL.setVisible(true);
        vL.setTitle("Iniciar Sesión");
    }
    /**
     * 
     */
    void login()
    {
        if (vL.txtUsuario.getText().equals("")) {
            vL.txtUsuario.setBackground(Color.red);
            vL.requestFocus();
            return;
        }
        if (vL.txtPasswd.getText().equals("")) 
        {
            vL.txtPasswd.setBackground(Color.red);
            vL.requestFocus();
            return;
        }        
        dtsES.setNomUsuario(vL.txtUsuario.getText()); 
        dtsES.setPasswd(vL.txtPasswd.getText());
        if (mL.getLogin(dtsES))
        {            
            try {
                VServidor vS = new VServidor();
                MServidor mS = new MServidor(vS);
                CServidor cS = new CServidor(mS, vS);
                vL.dispose();
                cS.initInterfazG(vL.txtUsuario.getText());
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
            }
        }else
        {
            JOptionPane.showMessageDialog(vL, "Verifique sus datos.");
        }
        
    }
    
}
