/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;



import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Skynet
 */
public class NodoGrafico {

    JLabel dato;
    ArrayList<NodoGrafico> listaNodosHijos;
    
    //constructor

    public NodoGrafico(JLabel dato) {
        this.dato = dato;
        listaNodosHijos = new ArrayList<>();
    }
    //obtener un dato
    JLabel getDato() {
        return this.dato;
    }
    //Agregar Nodo Hijo
    void addNodoHijo(NodoGrafico nodo) {
        listaNodosHijos.add(nodo);
    }
    //obtener todos los nodos
    ArrayList<NodoGrafico> getListaNodoHijos() {
        return this.listaNodosHijos;
    }
    
    
}
