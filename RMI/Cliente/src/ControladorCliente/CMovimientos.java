/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import Common.DtsMovimiento;
import ModeloCliente.MCliente;
import VistaCliente.VCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Skynet
 */
public class CMovimientos implements ActionListener{
    VCliente vc;
    MCliente mc;
    String noCuenta;
    
    
    public CMovimientos(VCliente vc1, MCliente mc1, String noCuenta) {
        this.vc = vc1;
        this.mc = mc1;
        this.noCuenta = noCuenta;
        
        vc.btnMHistorial.addActionListener(this);
        vc.btnMTikec.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vc.btnMHistorial) {            
            llenarTb(noCuenta);            
        }
        if (e.getSource()==vc.btnMTikec) {
           limpiarTB();
        }
    }
    //llenar tabla
    void llenarTb(String noC)
    {    
        
        String[] titulos ={"ID","No Cuenta","Operacion","Fecha","Hora"};
        String[] row;
        DefaultTableModel modelo=new DefaultTableModel(null,titulos);        
        
        ArrayList<DtsMovimiento> li= (ArrayList<DtsMovimiento>) mc.getMovimientos(noC);
        for (DtsMovimiento ob:li) {  
            row = new String[5];
           row[0]=ob.getIdMov();
            row[1]=ob.getNoCuenta();
            row[2]=ob.getOperacion();
            row[3]=ob.getFecha();
            row[4]=ob.getHora();
            modelo.addRow(row);
        }
        vc.jtMovimientos.setModel(modelo);
    }
    //limpiar tabla
    void limpiarTB()
    {
       // while(vc.jtMovimientos.getRowCount()>0) modelo.removeRow(0);
    }
}
