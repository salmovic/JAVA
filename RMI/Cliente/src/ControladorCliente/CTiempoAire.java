/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import ModeloCliente.MCliente;
import VistaCliente.VCliente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CTiempoAire implements ActionListener
{
    String noCuenta;
    MCliente mc;
    VCliente vc;

    public CTiempoAire(String noCuenta, MCliente mc1, VCliente vc1) {
        this.noCuenta = noCuenta;
        this.mc = mc1;
        this.vc = vc1;
        
        vc.btnTAceptar.addActionListener(this);
        vc.btnTAimprimirTikec.addActionListener(this);
        vc.btnTCancelar.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vc.btnTAceptar) 
        {
            if (vc.txtTCantidad.getText().equals("")) {
               vc.txtTCantidad.setBackground(Color.red);
               vc.txtTCantidad.requestFocus();
               return;
            }
            if (vc.txtTTelefono.getText().equals("")) {                
                vc.txtTTelefono.setBackground(Color.red);
                vc.txtTTelefono.requestFocus();
                return;
            }
            if (vc.jComboBox2.getSelectedIndex()==0) {
                vc.jComboBox2.requestFocus();
                JOptionPane.showMessageDialog(vc, "Debe seleccionar una compañia");
                return;
            }
            try
            {
                if (mc.tiempoAire(noCuenta, Double.parseDouble(vc.txtTCantidad.getText()))) 
                {
                    JOptionPane.showMessageDialog(vc, "Gracias por su preferencia");
                }else
                {
                    JOptionPane.showMessageDialog(vc, "No se pudo realizar el pago, intentelo mas tarde");
                }
            }catch(NumberFormatException ex){}
        }
        if (e.getSource()==vc.btnTAimprimirTikec) {
            JOptionPane.showMessageDialog(vc, "Implimir Tiket");
        }
        if (e.getSource()==vc.btnTCancelar) {
            vc.txtTCantidad.setText("");
            vc.txtTTelefono.setText("");
            vc.jComboBox2.setSelectedIndex(0);
            
        }
    }
    
}
