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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CServidor implements ActionListener, Runnable {

    Thread hilo;
    MServidor mS;
    VServidor vS;

    //METODO CONTRUCTOR

    public CServidor(MServidor mS, VServidor vS) {
        this.mS = mS;
        this.vS = vS;

        vS.btnRegistrarUsuario.addActionListener(this);
        vS.btnDetenerServidor.addActionListener(this);
        vS.btnIniciarServidor.addActionListener(this);
        vS.btnCerrarSesion.addActionListener(this);
    }

    //INICIAR INTERFAZ GRAFICA
    void initInterfazG(String text) {
        cargarImagen(text);
        vS.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vS.btnRegistrarUsuario) {
            MRegistrar mr = new MRegistrar();
            VRegistrar vr = new VRegistrar();
            CRegistrar cr = new CRegistrar(mr, vr);
            cr.iniciarInterfazGrafica();
        }
        if (e.getSource() == vS.btnCerrarSesion) {
            MLogin ml = new MLogin();
            VLogin vl = new VLogin();
            CLogin cl = new CLogin(ml, vl);
            vS.dispose();
            cl.iniciarInterfaz();
        }
        if (e.getSource() == vS.btnDetenerServidor) {
            try {                
                if (mS.getDetenerConRMI()) {
                    JOptionPane.showMessageDialog(vS, "Se detuvo el servidor");
                    vS.txtEstadoS.setText("OFFLINE");
                }
                
                
            } catch (NoSuchMethodError ec) {
                System.out.println(ec);
            }
        }
        if (e.getSource() == vS.btnIniciarServidor) {

            try {
                hilo = new Thread(this);
                hilo.start();

                System.out.println(hilo.getState());
            } catch (IllegalThreadStateException ex) {

                System.out.println(ex);
            }
        }
    }

    //CARGAR IMAGEN

    private void cargarImagen(String usr) {
        vS.lblFoto.setText(null);
        vS.lblFoto.setIcon(mS.getImagen(usr));
    }

    @Override
    public void run() {
        vS.txtEstadoS.setText(mS.getConexion(81, "rmi"));
    }

}
