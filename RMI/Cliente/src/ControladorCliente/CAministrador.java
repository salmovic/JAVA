/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import Common.DtsEmpleadoCliente;
import ModeloCliente.MAdministrador;
import ModeloCliente.MLogin;
import VistaCliente.VAdministrador;
import VistaCliente.VLogin;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
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
public class CAministrador implements ActionListener {

    Object[] titulo = {"Nombre", "Apellidos", "Direccion", "No. Cuenta", "NIP", "Saldo", "Foto"};
    Object[] row = new Object[7];
    DefaultTableModel modelo = new DefaultTableModel(null, titulo) {
        @Override
        public Class getColumnClass(int indiceColumna) {
            Object stefany = getValueAt(0, indiceColumna);
            if (stefany == null) {
                return Object.class;
            } else {
                return stefany.getClass();
            }
        }
    };

    String rutaImagen;
    DtsEmpleadoCliente dts = new DtsEmpleadoCliente();
    ArrayList<DtsEmpleadoCliente> lista;

    VAdministrador va;
    MAdministrador ma;

    //METODO CONSTRUCTOR
    public CAministrador(VAdministrador va1, MAdministrador ma1) {
        this.va = va1;
        this.ma = ma1;
        cargarTbCliente();
        //agregamos evento
        this.va.btnActualizar.addActionListener(this);
        this.va.btnEliminar.addActionListener(this);
        this.va.btnSalir.addActionListener(this);
        this.va.btnGuardar.addActionListener(this);
        this.va.btnFoto.addActionListener(this);
        
        //EVENTO CLICK TABLA
        this.va.jtAdministrador.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) 
          {
              va.txtNombre.setText((String) va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 0));
               va.txtApellidos.setText((String) va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 1));
                va.txtDireccion.setText((String) va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 2));
                 va.txtNoCuenta.setText((String) va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 3));
                  va.txtNip.setText((String) va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 4));
                   va.txtSaldo.setText(String.valueOf( va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 5)));
                   ImageIcon img = (ImageIcon) va.jtAdministrador.getValueAt(va.jtAdministrador.getSelectedRow(), 6);
                   va.lblImagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 120,0)));
          }
        });
    }

    //INICIAR INTERFAZ GRAFICA

    public void inicarIG() {
        va.setVisible(true);
    }

    //EVENTO DE BOTONES

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == va.btnActualizar) {
            actualizar();//actualizar datos
            deleteRowTable(); //limpiar tabla
            cargarTbCliente();// llenar tabla
        }
        if (e.getSource() == va.btnEliminar) {
            eliminar();
            deleteRowTable(); //limpiar tabla
            cargarTbCliente();// llenar tabla            
        }
        if (e.getSource() == va.btnGuardar) {
            guardar();
            deleteRowTable(); //limpiar tabla
            cargarTbCliente();// llenar tabla
        }
        if (e.getSource() == va.btnFoto) {
            JOptionPane.showConfirmDialog(va, "foto");
            cargarImagen();
        }
        if (e.getSource() == va.btnSalir) {
            va.dispose();
            VLogin vl = new VLogin();
            MLogin ml = new MLogin();
            CLogin cl = new CLogin(vl, ml);
           cl.iniciarInterfazGrafica();
        }
    }

    //BOTON GUARDAR

    private void guardar() {
        if (datosRequeridos()) {
            dts.setNombre(va.txtNombre.getText());
            dts.setApellido(va.txtApellidos.getText());
            dts.setDireccion(va.txtDireccion.getText());
            dts.setNoCuenta(va.txtNoCuenta.getText());
            dts.setNip(va.txtNip.getText());

            try {
                dts.setSaldo(Double.parseDouble(va.txtSaldo.getText()));
                //image
                File f = new File(rutaImagen);
                FileInputStream fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);
                int len = (int) f.length();
                byte[] img = new byte[len];
                bis.read(img);
                //ByteArrayInputStream bais = new ByteArrayInputStream(img);
                dts.setImagen(img); //envio de bytes de la imagen
                dts.setTamImagen(len);
        //dts.setImagen(bais);
                //
                if (ma.setCliente(dts)) {
                    JOptionPane.showMessageDialog(va, "Se guardo con exito!!");
                } else {
                    JOptionPane.showMessageDialog(va, "No se pudo realizar la operacion!!");
                }
            } catch (NumberFormatException ex) {
                va.txtSaldo.setBackground(Color.red);
                JOptionPane.showMessageDialog(va, "Debe ingresar una cantidad en el campo saldo!");
                va.txtSaldo.requestFocus();
                System.out.println(ex.getMessage());
                return;
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //BOTON GUARDAR

    private void actualizar() {
        if (datosRequeridos()) {
            dts.setNombre(va.txtNombre.getText());
            dts.setApellido(va.txtApellidos.getText());
            dts.setDireccion(va.txtDireccion.getText());
            dts.setNoCuenta(va.txtNoCuenta.getText());
            dts.setNip(va.txtNip.getText());

            try {
                dts.setSaldo(Double.parseDouble(va.txtSaldo.getText()));
                //image
                File f = new File(rutaImagen);
                FileInputStream fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);
                int len = (int) f.length();
                byte[] img = new byte[len];
                bis.read();

                dts.setImagen(img);
                dts.setTamImagen(len);
        //dts.setImagen(bais);
                //
                if (ma.updateCliente(dts)) {
                    JOptionPane.showMessageDialog(va, "Se actualizo con exito!!");
                } else {
                    JOptionPane.showMessageDialog(va, "No se pudo realizar la operacion!!");
                }
            } catch (NumberFormatException ex) {
                va.txtSaldo.setBackground(Color.red);
                JOptionPane.showMessageDialog(va, "Debe ingresar una cantidad en el campo saldo!");
                va.txtSaldo.requestFocus();
                System.out.println(ex.getMessage());
                return;
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //BOTON ELIMINAR

    private void eliminar() {
        if (va.txtNoCuenta.getText().equals("")) {
            va.txtNoCuenta.setBackground(Color.red);
            va.txtNoCuenta.requestFocus();
            return;
        }
        int c = JOptionPane.showConfirmDialog(va, "Esta seguro que desea eliminar?");
        dts.setNoCuenta(va.txtNoCuenta.getText());
        if (c == 0) {
            JOptionPane.showMessageDialog(va, ma.deleteCliente(dts) ? "Se elimino con exito" : "No se pudo realizar la operacion!");
        }
    }

    //METODO PARA CARGAR UNA IMAGE

    void cargarImagen() {
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        elegirImagen.setMultiSelectionEnabled(false);
        int o = elegirImagen.showOpenDialog(va);
        if (o == JFileChooser.APPROVE_OPTION) {
            rutaImagen = elegirImagen.getSelectedFile().getAbsolutePath();
            Image preview = Toolkit.getDefaultToolkit().getImage(rutaImagen);
            if (preview != null) {
                va.lblImagen.setText(null);
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(va.lblImagen.getWidth(), va.lblImagen.getHeight(), Image.SCALE_DEFAULT));
                va.lblImagen.setIcon(icon);
            }
        }
    }

    //DATOS REQUERIDOS

    boolean datosRequeridos() 
    {
        if (va.txtNombre.getText().equals("")) {
            va.txtNombre.setBackground(Color.red);
            va.txtNombre.requestFocus();
            return false;
        }
        if (va.txtApellidos.getText().equals("")) {
            va.txtApellidos.setBackground(Color.red);
            va.txtApellidos.requestFocus();
            return false;
        }
        if (va.txtDireccion.getText().equals("")) {
            va.txtDireccion.setBackground(Color.red);
            va.txtDireccion.requestFocus();
            return false;
        }
        if (va.txtNoCuenta.getText().equals("")) {
            va.txtNoCuenta.setBackground(Color.red);
            va.txtNoCuenta.requestFocus();
            return false;
        }
        if (va.txtNip.getText().equals("")) {
            va.txtNip.setBackground(Color.red);
            va.txtNip.requestFocus();
            return false;
        }
        if (va.txtSaldo.getText().equals("")) {
            va.txtSaldo.setBackground(Color.red);
            va.txtSaldo.requestFocus();
            return false;
        }
        if (rutaImagen == null) {
            JOptionPane.showMessageDialog(va, "Necesitar cargar una imagen!!!");
            cargarImagen();
            return false;
        }
        return true;
    }
    //CARGAR TABLA CLIENTE
    void cargarTbCliente()
    {
        try {
            lista = (ArrayList<DtsEmpleadoCliente>) ma.modeloCliente();
            for (DtsEmpleadoCliente cl : lista) {
                row[0] = cl.getNombre();
                row[1] = cl.getApellido();
                row[2] = cl.getDireccion();
                row[3] = cl.getNoCuenta();
                row[4] = cl.getNip();
                row[5] = cl.getSaldo();
                ImageIcon img = new ImageIcon(cl.getImagen());
                row[6] = new ImageIcon(img.getImage().getScaledInstance(120, 110, 0));
                modelo.addRow(row);
            }
            va.jtAdministrador.setModel(modelo);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    //ELIMINAR CELDA DE LA TABLA
    void deleteRowTable() {
        while (va.jtAdministrador.getRowCount() != 0) {
            modelo.removeRow(0);
        }
    }

}
