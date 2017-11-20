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
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CPagoServicio implements ActionListener{

    MCliente mc;
    VCliente vc;
    String noCuenta;

    public CPagoServicio(MCliente mc1, VCliente vc1, String noCuenta) {
        this.mc = mc1;
        this.vc = vc1;
        this.noCuenta = noCuenta;
        
        vc.btnPLimpiar.addActionListener(this);
        vc.btnPPagar.addActionListener(this);
        vc.btnPTike.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vc.btnPLimpiar) {
            vc.txtPagoServicio.setText("");
            vc.txtPNoCuenta.setText("");
            vc.cbxPSelecComp.setSelectedIndex(0);
            vc.txtPagoServicio.setBackground(Color.white);
            vc.txtPNoCuenta.setBackground(Color.white);
        }
        if (e.getSource()==vc.btnPPagar) 
        {
           
            if (vc.txtPagoServicio.getText().equals("")) {
               vc.txtPagoServicio.setBackground(Color.red);
               vc.txtPagoServicio.requestFocus();
               return;
            }
             if (vc.txtPNoCuenta.getText().equals("")) {
                vc.txtPNoCuenta.setBackground(Color.red);
                vc.txtPNoCuenta.requestFocus();
                return;
            }
            if (vc.cbxPSelecComp.getSelectedIndex()==0) {
                vc.cbxPSelecComp.requestFocus();
                JOptionPane.showMessageDialog(vc, "Seleccione un servicio");
                return;
            }
            try{
                if (mc.pagoServicio(noCuenta, Double.parseDouble(vc.txtPagoServicio.getText()))) {
                    JOptionPane.showMessageDialog(vc, "Se ha realizado con exito");
                }else
                {
                    JOptionPane.showMessageDialog(vc, "No se pudo realizar la operacion");
                }
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(vc, "Introduzca un moto válido");
                return;
            }
        }
        if (e.getSource()==vc.btnPTike) {
            JOptionPane.showMessageDialog(vc, "Tiket");
        }
    }
    
}
