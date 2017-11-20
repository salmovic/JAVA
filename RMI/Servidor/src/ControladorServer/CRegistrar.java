/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorServer;

import DatosServer.DtsEmpleadoServer;
import ModeloServer.MRegistrar;
import VistaServer.VRegistrar;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Skynet
 */
public class CRegistrar implements ActionListener {
    private String ruta; //ruta de la imagen
    ArrayList<DtsEmpleadoServer> lista = new ArrayList<>();

    MRegistrar mR; //Modelo Registrar
    VRegistrar vR; //Vista Registrar

    DtsEmpleadoServer dtsES; //datos empleado
    //Metodo Constructor

    DefaultTableModel modelo;

    public CRegistrar(MRegistrar modR, VRegistrar vistR) {
        this.mR = modR;
        this.vR = vistR;
        this.dtsES = new DtsEmpleadoServer();
        llenarJTable();
        //agregamos evento ActionListener al los botones
        vR.btnActualizar.addActionListener(this);
        vR.btnBuscar.addActionListener(this);
        vR.btnEliminar.addActionListener(this);
        vR.btnGuardar.addActionListener(this);
        
        //agregamos evento KeyListener a busqueda avanzada
        vR.txtBuscarBy.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                eliminarFilaTabla();
                modelo = mR.getEmpleadoBy(vR.txtBuscarBy.getText());
                vR.jtRegistroEmpleado.setModel(modelo);
            }
        });

        vR.jtRegistroEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vR.txtClaveEmpleado.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 0));
                vR.txtNombre.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 1));
                vR.txtApellidos.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 2));
                vR.txtDireccion.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 3));
                vR.txtTelefono.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 4));
                vR.txtUsuario.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 5));
                vR.txtPasswd.setText((String) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 6));
                ImageIcon imgIcon = (ImageIcon) vR.jtRegistroEmpleado.getValueAt(vR.jtRegistroEmpleado.getSelectedRow(), 7);
                vR.lblFoto.setText(null);
                vR.lblFoto.setIcon(imgIcon);
            }
        });
        //agregar imagen
        vR.lblFoto.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {                
                cargarImagen();
            }
        });
    }
    /*Iniciar Interfaz Grafica*/
    void iniciarInterfazGrafica() {
        vR.setVisible(true);
        vR.setTitle("Registrar Aministrador");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //boton Guardar
        if (e.getSource() == vR.btnGuardar) {
            guardar();
        }
        if (e.getSource() == vR.btnActualizar) {
            actualizar();
        }
        if (e.getSource() == vR.btnEliminar) {
            eliminar();
        }
        if (e.getSource() == vR.btnBuscar) {
            buscar();
        }        
    }

    //datos empelado     
    void guardar() {
        if (dtsRequeridos()) {
            FileInputStream fis = null;
            try {
                dtsES.setIdEmpelado(vR.txtClaveEmpleado.getText());
                dtsES.setNombre(vR.txtNombre.getText());
                dtsES.setApellidos(vR.txtApellidos.getText());
                dtsES.setDireccion(vR.txtDireccion.getText());
                dtsES.setTelefono(vR.txtTelefono.getText());
                dtsES.setNomUsuario(vR.txtUsuario.getText());
                dtsES.setPasswd(vR.txtPasswd.getText());
                //imagen
                File f = new File(ruta);
                int len = (int) f.length();
                fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);
                byte[] img = new byte[len];
                bis.read(img);
                ByteArrayInputStream bais = new ByteArrayInputStream(img);

                dtsES.setImagen(bais);
                dtsES.setTamImagen(len);

                if (mR.setEmpleadoServer(dtsES)) {
                    JOptionPane.showMessageDialog(vR, "Se realizo con exito la operacion");
                    eliminarFilaTabla();
                    llenarJTable();
                } else {
                    JOptionPane.showMessageDialog(vR, "No se pudo realizar la operacion");
                }

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }

    }

    void actualizar() {
        if (dtsRequeridos()) {
            FileInputStream fis = null;
            try {
                dtsES.setIdEmpelado(vR.txtClaveEmpleado.getText());
                dtsES.setNombre(vR.txtNombre.getText());
                dtsES.setApellidos(vR.txtApellidos.getText());
                dtsES.setDireccion(vR.txtDireccion.getText());
                dtsES.setTelefono(vR.txtTelefono.getText());
                dtsES.setNomUsuario(vR.txtUsuario.getText());
                dtsES.setPasswd(vR.txtPasswd.getText());
                //imagen
                File f = new File(ruta);
                int len = (int) f.length();
                fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);
                byte[] img = new byte[len];
                bis.read(img);
                ByteArrayInputStream bais = new ByteArrayInputStream(img);

                dtsES.setImagen(bais);
                dtsES.setTamImagen(len);

                if (mR.updateEmpleadoServer(dtsES)) {
                    JOptionPane.showMessageDialog(vR, "Se realizo con exito la operacion");
                    eliminarFilaTabla();
                    llenarJTable();
                } else {
                    JOptionPane.showMessageDialog(vR, "No se pudo realizar la operacion");
                }

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    void eliminar() {
        if (vR.txtClaveEmpleado.getText().equals("")) {
            vR.txtClaveEmpleado.setBackground(Color.red);
            vR.txtClaveEmpleado.requestFocus();
            return;
        }
        int c = JOptionPane.showConfirmDialog(vR, "Estas seguro de que quieres eliminar.");
        if (c == 0) {
            dtsES.setIdEmpelado(vR.txtClaveEmpleado.getText());
            if (mR.deleteEmpleadoServer(dtsES)) {
                JOptionPane.showMessageDialog(vR, "Se realizo con exito la operacion");
                eliminarFilaTabla();
                llenarJTable();
            } else {
                JOptionPane.showMessageDialog(vR, "No se pudo realizar la operacion!");
            }
        }
    }

    void buscar() {
        if (vR.txtClaveEmpleado.getText().equals("")) {
            vR.txtClaveEmpleado.setBackground(Color.red);
            vR.txtClaveEmpleado.requestFocus();
            return;
        }
        eliminarFilaTabla();
        modelo = mR.getEmpleadoBy(vR.txtClaveEmpleado.getText());
        vR.jtRegistroEmpleado.setModel(modelo);

        //vR.jtRegistroEmpleado.setModel(mR.getEmpleadoBy(vR.txtClaveEmpleado.getText()));
    }

    void eliminarFilaTabla() {
        while (vR.jtRegistroEmpleado.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    void llenarJTable() {

        modelo = mR.getEmpleado();
        this.vR.jtRegistroEmpleado.setModel(modelo);
    }
    
    boolean dtsRequeridos() {
        if (vR.txtClaveEmpleado.getText().equals("")) {
            vR.txtClaveEmpleado.setBackground(Color.red);
            vR.txtClaveEmpleado.requestFocus();
            return false;
        }
        if (vR.txtNombre.getText().equals("")) {
            vR.txtNombre.setBackground(Color.red);
            vR.txtNombre.requestFocus();
            return false;
        }
        if (vR.txtApellidos.getText().equals("")) {
            vR.txtApellidos.setBackground(Color.red);
            vR.txtApellidos.requestFocus();
            return false;
        }
        if (vR.txtUsuario.getText().equals("")) {
            vR.txtUsuario.setBackground(Color.red);
            vR.txtUsuario.requestFocus();
            return false;
        }
        if (vR.txtPasswd.getText().equals("")) {
            vR.txtPasswd.setBackground(Color.yellow);
            vR.txtPasswd.requestFocus();
            return false;
        }
        if (ruta == null) {
            vR.lblFoto.setForeground(Color.red);            
            JOptionPane.showMessageDialog(vR, "Debe Cargar una imagen");
            cargarImagen();
            return false;
        }
        return true;
    }
    void cargarImagen()
    {
        final JFileChooser elegirImagen = new JFileChooser();
                elegirImagen.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
                elegirImagen.setMultiSelectionEnabled(false);
                int o = elegirImagen.showOpenDialog(vR);
                if (o == JFileChooser.APPROVE_OPTION) {
                    ruta = elegirImagen.getSelectedFile().getAbsolutePath();                                      
                    Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
                    if (preview != null) {                        
                        vR.lblFoto.setText(null);
                        ImageIcon icon = new ImageIcon(preview.getScaledInstance(vR.lblFoto.getWidth(), vR.lblFoto.getHeight(), Image.SCALE_DEFAULT));
                        vR.lblFoto.setIcon(icon);
                    }
                }
    }

}
