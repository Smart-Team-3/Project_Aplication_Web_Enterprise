package com.Proyecto_Ciclo_3.Project.entities;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="movimientos")
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String concepto;
    private long monto;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "empleado_id")
    private Empleado usuario;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "empresa_id")
    private Empresa enterprise;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fecha;

    private Double sumaMontos;

    public MovimientoDinero() {
    }

    public MovimientoDinero(long monto, String concepto, Empleado usuario,Empresa enterprise, Date fecha,Double sumaMontos) {
        this.monto = monto;
        this.concepto = concepto;
        this.usuario = usuario;
        this.enterprise = enterprise;
        this.fecha = fecha ;
        this.sumaMontos = sumaMontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Empleado getUsuario() {
        return usuario;
    }

    public void setUsuario(Empleado usuario) {
        this.usuario = usuario;
    }
    public Empresa getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Empresa enterprise) {
        this.enterprise = enterprise;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "MovimientoDinero{" +
                "id=" + id +
                ", concepto='" + concepto + '\'' +
                ", monto=" + monto +
                ", usuario=" + usuario +
                ", enterprise=" + enterprise +
                ", fecha=" + fecha +
                '}';
    }

    public Double getSumaMontos() {

        return sumaMontos;
    }

    public void setSumaMontos(Double sumaMontos) {
        this.sumaMontos = sumaMontos;
    }
}


