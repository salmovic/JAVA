/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosServer;

import java.io.ByteArrayInputStream;

/**
 *
 * @author Skynet
 */
public class DtsEmpleadoServer {
    private String idEmpelado;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String nomUsuario;
    private String passwd;
    
    private int tamImagen;
    private ByteArrayInputStream imagen;
    
    public DtsEmpleadoServer(){}
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the nomUsuario
     */
    public String getNomUsuario() {
        return nomUsuario;
    }

    /**
     * @param nomUsuario the nomUsuario to set
     */
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return the tamImagen
     */
    public int getTamImagen() {
        return tamImagen;
    }

    /**
     * @param tamImagen the tamImagen to set
     */
    public void setTamImagen(int tamImagen) {
        this.tamImagen = tamImagen;
    }

    /**
     * @return the imagen
     */
    public ByteArrayInputStream getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(ByteArrayInputStream imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the idEmpelado
     */
    public String getIdEmpelado() {
        return idEmpelado;
    }

    /**
     * @param idEmpelado the idEmpelado to set
     */
    public void setIdEmpelado(String idEmpelado) {
        this.idEmpelado = idEmpelado;
    }
    
}
