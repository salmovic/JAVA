/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import ModeloCliente.MCliente;
import VistaCliente.VCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CDeposito implements ActionListener{
    String noCuenta;
    VCliente vc;
    MCliente mc;

    public CDeposito(VCliente vc, MCliente mc,String noCuenta) {
        this.vc = vc;
        this.mc = mc;
        this.noCuenta = noCuenta;
        
        vc.btnDAceptar.addActionListener(this);
        vc.btnDBorrar.addActionListener(this);
        vc.btnDCancel.addActionListener(this);
        vc.btnDTiket.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource()==vc.btnDAceptar) 
        {
            try
            {
                double ca = Double.parseDouble(vc.txtDCantidad.getText());
                if (mc.multiploDeCien(ca)) {
                    if (mc.depositoEfectivo(noCuenta,ca)) {
                        JOptionPane.showMessageDialog(vc,"Se realizo con exito la operacion");
                    }else
                        JOptionPane.showMessageDialog(vc, "No se pudo realizar la operacion");
                }else
                    JOptionPane.showMessageDialog(vc, "Debe ingresar un numero multiplo de 100"
                        +" Si quiere establecer otra cantidad acuda a ventanilla");
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(vc, "Debe ingresar un numero multiplo de 100"
                        +" Si quiere establecer otra cantidad acuda a ventanilla");
                vc.txtDCantidad.requestFocus();
            }
        }
        if (e.getSource()==vc.btnDBorrar) {
            vc.txtDCantidad.setText("");
        }
        if (e.getSource()==vc.btnDCancel) {
           vc.txtDCantidad.setText("");
        }
        if (e.getSource()==vc.btnDTiket) {
            JOptionPane.showMessageDialog(vc, "Imprimir Tiket");
        }
    }
    
}
