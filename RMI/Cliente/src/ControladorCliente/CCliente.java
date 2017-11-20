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
public class CCliente implements ActionListener 
{
    String noCuenta;
    VCliente vc;
    MCliente mc;

    public CCliente(VCliente vc1, MCliente mc1,String nc) 
    {
        this.vc = vc1;
        this.mc = mc1; 
        noCuenta=nc;
        //CLASES EXTERNAS
        new CDeposito(vc, mc, noCuenta);
        new CSaldo(noCuenta, mc, vc);
        new CTiempoAire(noCuenta, mc, vc);
        new CPagoServicio(mc, vc, noCuenta);
        new CMovimientos(vc1, mc1, noCuenta);
        //BOTONES DE RETIRO
        vc.btnR100.addActionListener(this);
        vc.btnR1000.addActionListener(this);
        vc.btnR1500.addActionListener(this);
        vc.btnR200.addActionListener(this);
        vc.btnR2000.addActionListener(this);
        vc.btnR2500.addActionListener(this);
        vc.btnR500.addActionListener(this);
        vc.btnR5000.addActionListener(this);
        vc.btnRAceptar.addActionListener(this);
        vc.btnRBorrar.addActionListener(this);
        vc.btnRCancelar.addActionListener(this);
        vc.btnRotraCantidad.addActionListener(this);
        vc.btnRtiket.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vc.btnR100) 
        {
            vc.txtRotraCantidad.setText("100");
        }
        
        if (e.getSource()==vc.btnR1000) {
        vc.txtRotraCantidad.setText("1000");
        }
        
        if (e.getSource()==vc.btnR1500) 
        {
        vc.txtRotraCantidad.setText("1500");
        }
        
        if (e.getSource()==vc.btnR200) 
        {
            vc.txtRotraCantidad.setText("200");
        }
        
        if (e.getSource()==vc.btnR2000) {
        vc.txtRotraCantidad.setText("2000");
        }
        
        if (e.getSource()==vc.btnR2500) {
        vc.txtRotraCantidad.setText("2500");
        }
        
        if (e.getSource()==vc.btnR500) {
        vc.txtRotraCantidad.setText("500");
        }        
        if (e.getSource()==vc.btnR5000) {
        vc.txtRotraCantidad.setText("5000");
        }        
        if (e.getSource()==vc.btnRAceptar) {
            if (vc.txtRotraCantidad.getText().equals(""))
            {
                vc.txtRotraCantidad.setBackground(Color.red);
                vc.txtRotraCantidad.requestFocus();
                return;
            }
            try{
                double cant = Double.parseDouble(vc.txtRotraCantidad.getText());
                if (mc.multiploDeCien(cant)) 
                {
                    if (mc.retiroEfectivo(noCuenta, cant)) {
                        JOptionPane.showMessageDialog(vc, "Puede retirar su efectivo");
                    }
                }else
                {
                    JOptionPane.showMessageDialog(vc, "Solo acepta cantidaddes multiplos de 100");
                }
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(vc, "Debe ingrear un numero valido");
            }
            mc.retiroEfectivo(noCuenta, 0);
        }
        
        if (e.getSource()==vc.btnRCancelar) {
          vc.txtRotraCantidad.setText("");
          vc.txtRotraCantidad.setEnabled(false);
        }        
        
        if (e.getSource()==vc.btnRotraCantidad) {
         vc.txtRotraCantidad.setEnabled(true);
         vc.txtRotraCantidad.requestFocus();
         vc.txtRotraCantidad.setText("");
        }
        
        if (e.getSource()==vc.btnRtiket) {
        JOptionPane.showMessageDialog(vc, "Tiket");
        }        
    }
    
    void inicarIGClinete()
    {
        vc.setVisible(true);
    }   
}
