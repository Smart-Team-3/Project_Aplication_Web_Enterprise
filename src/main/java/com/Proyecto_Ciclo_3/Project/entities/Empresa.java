package com.Proyecto_Ciclo_3.Project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="empresas")

public class Empresa {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Empleado> users;
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private List<MovimientoDinero> movements;

    public Empresa() {

    }

    public Empresa(String nombre, String direccion, String telefono, String NIT, ArrayList<Empleado> users,ArrayList<MovimientoDinero> movements) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = NIT;
        this.users = users;
        this.movements = movements;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public List<Empleado> getUsers() {
        return users;
    }

    public void setUsers(List<Empleado> users) {
        this.users = users;
    }

    public List<MovimientoDinero> getMovements() {
        return movements;
    }

    public void setMovements(List<MovimientoDinero> movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return
                nombre ;
    }

}
