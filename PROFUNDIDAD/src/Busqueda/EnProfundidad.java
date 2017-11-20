/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import static Aplicacion.Mapa.lblCarro;
import Aplicacion.MoveCarro1;
import java.util.ArrayList;

/**
 *
 * @author Skynet
 */
public class EnProfundidad extends Thread{
    ArrayList liProfundidad;
    Thread h;
    MoveCarro1 car;
    public EnProfundidad(ArrayList liProfundidad) {
        this.liProfundidad = liProfundidad;
        car = new MoveCarro1();
        h = new Thread(this);
        h.start();        
    }    
    @Override
    public void run()
    {
        for (Object c : liProfundidad) {
                    switch (c.toString()) {
                        case "CHICONTEPEC":
                            lblCarro.setLocation(6, 24);
                            break;
                        case "JUAREZ":
                            car.chicon_juarez();
                            break;
                        case "LLANO":
                            car.juarez_llano();
                            break;
                        case "OXITEMPA":
                            car.llano_oxitempa();
                            break;
                        case "JABONERA":
                            car.oxitempa_jabonera();
                            break;
                        case "NARANJAL":
                            car.jabonera_naranjal();
                            break;
                        case "TLACHICHILCO":
                            car.naranjal_janonera();
                            car.jabonera_tlachi();
                            break;
                        case "ITZUATLAN":
                            car.tlachi_jabonera();
                            car.jabonera_oxitempa();
                            car.oxitempa_itzuatlan();
                            break;
                        case "ALAMO":
                            car.itzuatlan_oxitempa();
                            car.oxitempa_llano();
                            car.llano_alamo();
                            break;
                    }
    }
}}
