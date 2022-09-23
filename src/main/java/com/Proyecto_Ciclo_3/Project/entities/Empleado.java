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
    @Column(name = "foto")
    private String foto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo" , unique = true)
    private String correo;
    @Column(name = "auth0Id" , unique = true)
    private String auth0Id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="empresa_id")//enlaza
    private Empresa empresa;//llamo a Empresa debo asociar
    @Column(name = "rol", unique = false)
    private String rol;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<MovimientoDinero> movements;

    //contructor vacio
    public Empleado() {
    }

    //constructor sin id
    public Empleado(String nombre, String correo, String rol, Empresa empresa,String foto,String auth0Id ) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.empresa = empresa;
        this.foto=foto;
        this.auth0Id=auth0Id;
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

    public List<MovimientoDinero> getMovements() {
        return movements;
    }

    public void setMovements(List<MovimientoDinero> movements) {
        this.movements = movements;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }
}
