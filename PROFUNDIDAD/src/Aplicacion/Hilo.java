/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import static Aplicacion.Mapa.lblCarro;
import java.util.ArrayList;

/**
 *
 * @author Skynet
 */
public class Hilo extends Thread{
    public static boolean band;
    Thread h;
    MoveCarro1 car = new MoveCarro1();
    ArrayList liProfundidad;
    ArrayList liAnchura;
    int tiAl;

    public Hilo() {
        h = new Thread(this);
    }
    
    public Hilo(int tAlg,ArrayList prof,ArrayList anchur) {
        this.liProfundidad=prof;
        liAnchura=anchur;
        this.tiAl = tAlg;
        h=new Thread(this);
    }    
    public void run()
    {
        
            logica();
        
    }
    public void star()
    {
        h.start();
    }
    void logica()
    {
        switch (tiAl) {
            case 1:
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
                
                break;
            case 2:
                for (Object c : liAnchura) {
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
                            car.alamo_llano();
                            car.llano_oxitempa();
                            car.oxitempa_jabonera();
                            break;
                        case "NARANJAL":
                            car.itzuatlan_oxitempa();
                            car.oxitempa_jabonera();
                            car.jabonera_naranjal();
                            break;
                        case "TLACHICHILCO":
                            car.naranjal_janonera();
                            car.jabonera_tlachi();
                            break;
                        case "ITZUATLAN":
                            car.jabonera_oxitempa();
                            car.oxitempa_itzuatlan();
                            break;
                        case "ALAMO":
                            car.oxitempa_llano();
                            car.llano_alamo();
                            break;
                    }
                }
                
                break;
        }
    }
   
}
