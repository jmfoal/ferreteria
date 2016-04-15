package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Excepciones.HerramientaInexistente;
import java.util.*;
import java.io.*;

/**
 * Clase Ferreteria que almacena los datos de una ferreteria y un stock de Herramienta
 * @author Foal
 */
public class Ferreteria {
    String razon;
    String direccion;
    String titular;
    ArrayList<Herramienta> stock;

    /**
     * Constructor de Ferreteria
     * @param razon
     * @param direccion
     * @param titular 
     */
    public Ferreteria(String razon, String direccion, String titular) {
        this.razon = razon;
        this.direccion = direccion;
        this.titular = titular;
        stock = new ArrayList();
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRazon() {
        return razon;
    }

    public ArrayList<Herramienta> getStock() {
        return stock;
    }

    public String getTitular() {
        return titular;
    }
    
    /**
     * Metodo para agregar herramientas al stock
     * @param nueva 
     */
    public void añadirHerramienta(Herramienta nueva){
        stock.add(nueva);
        stock.sort(null);
    }
    
    /**
     * Metodo que realiza una busqueda binaria en el stock de una herramienta por marca y modelo
     * devuelve -1 si la herramienta no existe
     * @param marca
     * @param modelo
     * @return posicion de la herramienta o -1
     */
    public int busqueda(String marca, String modelo){
        Herramienta buscada = new Herramienta(marca,modelo);
        
        int inicio=0;
        int fin=stock.size()-1;
        int medio;
        int posicion=-1;
        
        while (inicio<=fin){
            medio=(inicio+fin)/2;
            if (stock.get(medio).compareTo(buscada)<0){
                inicio=medio+1;
            }
            else if(stock.get(medio).compareTo(buscada)>0){
                fin=medio-1;
            }
            else{
                posicion=medio;
                inicio=fin+1;
            }
        }
        return posicion;
    }   

    
    /**
     * metodo que elimina una herramienta del stock, devuelve la excepcion "HerramientaInexistente" en caso de que no exista
     * @param marca
     * @param modelo
     * @throws HerramientaInexistente 
     */
    public void eliminarHerramienta(String marca, String modelo) throws HerramientaInexistente{
        int posicion = this.busqueda(marca, modelo);
        
        if(posicion >= 0){
            stock.remove(posicion);
        } else {
            throw new HerramientaInexistente("la herramienta no existe");
        }
    }
    
    public void eliminarHerramienta(int indice){
        stock.remove(indice);
    }
    
    /**
     * Metodo para cambiar las existencias de una herramienta pasada por marca y modelo
     * devuelve la excepcion "HerramientaInexistente" en caso de que no encuentre la herramienta
     * @param marca
     * @param modelo
     * @param existencias
     * @throws HerramientaInexistente 
     */
    public void ModificarExistenticas(String marca, String modelo, int existencias) throws HerramientaInexistente{
        int posicion = this.busqueda(marca, modelo);
        
        if(posicion >= 0){
            stock.get(posicion).setExistencias(existencias);
        } else {
            throw new HerramientaInexistente("la herramietna no existe");
        }
    }
    
    /**
     * Metodo para modificar el precio de una herramienta pasada por marca y modelo
     * devuelve la excepcion "HerramientaInexistente" en caso de que la herramienta no exista
     * @param marca
     * @param modelo
     * @param precio
     * @throws HerramientaInexistente 
     */
    public void ModificarPrecio(String marca, String modelo, int precio) throws HerramientaInexistente{
        int posicion = this.busqueda(marca, modelo);
        
        if(posicion >= 0){
            stock.get(posicion).setPrecio(precio);
        } else {
            throw new HerramientaInexistente("la herramietna no existe");
        }
    }
    
    /**
     * metodo para guardar la Ferreteria en un archivo
     * @throws IOException 
     */
    public void guardar() throws IOException{
        ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(razon + ".fer"));
        archivo.writeUTF(direccion);
        archivo.writeUTF(titular);
        archivo.writeObject(stock);
            
        archivo.close();           
        
    }
    
    /**
     * Constructor para cargar la Ferreteria desde un archivo
     * puede devolver la excepcion "IOException" en caso de no encontrarla.
     * @param razon
     * @throws IOException 
     */
    public Ferreteria(String razon) throws IOException{
        ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(razon + ".fer"));        
        
        this.razon = razon;
        direccion = archivo.readUTF();
        titular = archivo.readUTF();
        stock = new ArrayList();
        try {
            stock = (ArrayList<Herramienta>) archivo.readObject();
        } catch (ClassNotFoundException ex) {
            
        }
    }
    
    /**
     * metodo que devuelve un tabla con los datos de las herramientas.
     * el primer indice distingue las herramientas
     * el segundo indice ddistingue los datos de las herramietnas
     * @return latabla bidimensional de las herramietnas
     */
    public String[][] mostrarHerramientas(){
        String herramientas[][] = new String[stock.size()][6];
        Herramienta a;
        int indice = 0;
        
        Iterator<Herramienta> iterador = this.stock.iterator();
        
        while(iterador.hasNext()){
            a = iterador.next();
            
            herramientas[indice][0] = a.getNombre();
            herramientas[indice][1] = a.getMarca();
            herramientas[indice][2] = a.getModelo();
            herramientas[indice][3] = Integer.toString(a.getTamaño());
            herramientas[indice][4] = Double.toString(a.getPrecio());
            herramientas[indice][5] = Integer.toString(a.getExistencias());
            
            indice++;
        }
        
        return herramientas;
            
    }
}


