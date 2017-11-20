/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Skynet
 */
public class MoveCarro1{
    int mx, my;
    Mapa v;

    //CHICON-JUAREZ
    public void chicon_juarez() 
    {
        posicionCarro(v.lblChicon);
        imgCarroDerecha(v.lblChicon);
        my = 1;
        while (v.lblCarro.getLocation().y <= v.lblJuarez.getLocation().y) 
        {
            tiempo();
            v.lblCarro.setLocation(new Point(v.lblCarro.getLocation().x, v.lblCarro.getLocation().y + my));
        
        }
        
    }
    public void juarez_chicon() 
    {
        imgCarroIzquierda(v.lblJuarez);
        my = -1;
        while (v.lblCarro.getLocation().y >= v.lblChicon.getLocation().y) 
        {
            tiempo();
            v.lblCarro.setLocation(new Point(v.lblCarro.getLocation().x, v.lblCarro.getLocation().y + my));
        }
    }

    //JUAREZ-LLANO
    public void juarez_llano() {
        imgCarroDerecha(v.lblJuarez);
        mx = 1;        
        while (v.lblCarro.getLocation().x <= v.lblLlano.getLocation().x) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y);
        }
    }

    public void llano_juarez() 
    {
        imgCarroIzquierda(v.lblLlano);
        mx = -1;
        while (v.lblCarro.getLocation().x >= v.lblJuarez.getLocation().x) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y);
        }
    }
    
    //LLANO-OXITEMPA
    public void llano_oxitempa() 
    {    
        imgCarroDerecha(v.lblLlano);
        my = 1;
        mx = 1;
        while (v.lblCarro.getLocation().y <= v.lblOxitempa.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblOxitempa);
    }

    public void oxitempa_llano() 
    {
        imgCarroIzquierda(v.lblOxitempa);
        my = -1;
        mx = -1;
        while (v.lblCarro.getLocation().y >= v.lblLlano.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblLlano);
    }

    //LLANO-ALAMO
    public void llano_alamo() 
    {
        imgCarroDerecha(v.lblLlano);
        my = -1;
        mx = 1;
        while (v.lblCarro.getLocation().y >= v.lblAlamo.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblAlamo);
    }

    public void alamo_llano() 
    {
        imgCarroDerecha(v.lblAlamo);
        my = 1;
        mx = -1;
        while (v.lblCarro.getLocation().y <= v.lblLlano.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblLlano);
    }
    //OXITEMPA - JABONER

    public void oxitempa_jabonera() 
    {
        imgCarroDerecha(v.lblOxitempa);
        my = 1;
        mx = 0;
        while (v.lblCarro.getLocation().y <= v.lblJabonera.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
    }

    public void jabonera_oxitempa() 
    {
        imgCarroIzquierda(v.lblJabonera);
        my = -1;
        mx = 0;
        while (v.lblCarro.getLocation().y >= v.lblOxitempa.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
    }

    //OXITEMPA ITZUATLAN
    public void oxitempa_itzuatlan() 
    {
        imgCarroDerecha(v.lblOxitempa);
        my = -1;
        mx = 1;
        while (v.lblCarro.getLocation().y >= v.lblItzuatlan.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblItzuatlan);
    }

    public void itzuatlan_oxitempa() 
    {
        imgCarroIzquierda(v.lblItzuatlan);
        my = 1;
        mx = -1;
        while (v.lblCarro.getLocation().y <= v.lblOxitempa.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblOxitempa);
    }
//JABONERA-NARANJAL
    public void jabonera_naranjal() 
    {
        imgCarroIzquierda(v.lblJabonera);
        my = 1;
        mx = -1;
        while (v.lblCarro.getLocation().y <= v.lblNaranjal.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblNaranjal);
    }
    public void naranjal_janonera() 
    {
        imgCarroDerecha(v.lblNaranjal);
        my = -1;
        mx = 1;
        while (v.lblCarro.getLocation().y >= v.lblJabonera.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblJabonera);
    }
    //JABONERA-TLACHI
    public void jabonera_tlachi()
    {
        imgCarroDerecha(v.lblJabonera);
        my = 1;
        mx = 1;
        while (v.lblCarro.getLocation().y <= v.lblTlachichilco.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblTlachichilco);
    }
    public void tlachi_jabonera()
    {
        imgCarroIzquierda(v.lblTlachichilco);
        my = -1;
        mx = -1;
        while (v.lblCarro.getLocation().y >= v.lblJabonera.getLocation().y) {
            tiempo();
            v.lblCarro.setLocation(v.lblCarro.getLocation().x + mx, v.lblCarro.getLocation().y + my);
        }
        posicionCarro(v.lblJabonera);
    }
    //TIEMPO
    public void tiempo() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {}
    }
    public void posicionCarro(JLabel nodo)
    {        
        v.lblCarro.setLocation(nodo.getLocation().x,nodo.getLocation().y);
    }
    
    
    void imgCarroIzquierda(JLabel nodo)
    {
        Mapa.lblAlamo.setIcon(null);
        Mapa.lblCarro.setIcon(new ImageIcon("src/Img/carro_izq.png"));
        Mapa.lblCarro.setLocation(nodo.getLocation().x, nodo.getLocation().y);
    }
    void imgCarroDerecha(JLabel nodo)
    {
        Mapa.lblAlamo.setIcon(null);
        Mapa.lblCarro.setIcon(new ImageIcon("src/Img/carro_der.png"));
        Mapa.lblCarro.setLocation(nodo.getLocation().x, nodo.getLocation().y);
    }
}
