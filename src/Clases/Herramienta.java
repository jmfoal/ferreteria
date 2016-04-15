package Clases;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase Herramienta que guarda uan serie de datos relacionados con estas
 * @author Foal
 */
public class Herramienta implements Comparable<Herramienta>, Serializable{
    private String nombre;
    private String marca;
    private String modelo;
    private int tamaño;
    private double precio;
    private int existencias;

    /**
     * Constructor pr defecto
     * @param nombre
     * @param marca
     * @param modelo
     * @param tamaño
     * @param precio
     * @param existencias 
     */
    public Herramienta(String nombre, String marca, String modelo, int tamaño, double precio, int existencias) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.tamaño = tamaño;
        this.precio = precio;
        this.existencias = existencias;
    }
    
    /**
     * Constructor para las busquedas
     * @param marca
     * @param modelo 
     */
    public Herramienta(String marca,String modelo){
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getExistencias() {
        return existencias;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getTamaño() {
        return tamaño;
    }    
    
    /**
     * metodo compareTo para comparar las herramientas por marca y modelo
     * retorna un entero negativo en caso de que la herramienta sea anterior,
     * retorna un positivo en caso de que sea posterior
     * retorna 0 en caso de que las herramientas sean iguales
     * @param o
     * @return <0 || 0 || >0
     */
    @Override    
    public int compareTo(Herramienta o) {
        int orden;
        if(this.marca.equalsIgnoreCase(o.marca)){
            orden = this.modelo.compareToIgnoreCase(o.modelo);
            
        } else {
            orden = this.marca.compareToIgnoreCase(o.marca);
        }
        
        return orden;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
    

    
}
