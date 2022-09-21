package com.Proyecto_Ciclo_3.Project.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity//entidad
@Table(name="empleados")//para base de datos
public class Empleado {

    @Id// autoincrementable
    @GeneratedValue(strategy = GenerationType.AUTO)//estrategia
    private int id;//key
    private String nombre;
    private String correo;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<MovimientoDinero> movements;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="empresa_id")//enlaza
    private Empresa empresa;//llamo a Empresa debo asociar
    private String rol;

    //contructor vacio
    public Empleado() {
    }

    //constructor sin id
    public Empleado(String nombre, String correo, String rol, Empresa empresa) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.empresa = empresa;
    }

    //get y set
    public Empresa getEmpresa(){
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if(Objects.equals(rol, "administrativo") || Objects.equals(rol, "operativo")){
            this.rol = rol;
        }
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", empresa=" + empresa +
                ", rol='" + rol + '\'' +
                '}';

    }
}
