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
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class CSaldo implements ActionListener{
    String noCuenta;
    MCliente mc;
    VCliente vc;

    public CSaldo(String noCuenta, MCliente mc, VCliente vc) {
        this.noCuenta = noCuenta;
        this.mc = mc;
        this.vc = vc;
        
        vc.btnSBuscar.addActionListener(this);
        vc.btnSLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vc.btnSBuscar) {
            double saldo=mc.getSaldo(noCuenta);
            if (saldo!=-1) {
                vc.txtSaldo.setText("$"+String.valueOf(saldo));
            }else
            {
                JOptionPane.showMessageDialog(vc, "Por el momento no podemos mosatrar el saldo, intentelo mas tarde");
            }
        }
        if (e.getSource()==vc.btnSLimpiar) 
        {
            vc.txtSaldo.setText("");
        }
    }
}
