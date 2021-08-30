/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herbas.ed2.treeproject;

/**
 *
 * @author Nicole
 */
public class Nodo {
    private Nodo izquierda;
    private Nodo derecha;
    private Ave data;

    public Nodo(Ave data) {
        this.izquierda = null;
        this.derecha = null;
        this.data = data;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Ave getData() {
        return data;
    }

    public void setData(Ave data) {
        this.data = data;
    }  
    
    public String visualizar() {
        String dataAve = "";
        dataAve += "Especie: " + this.getData().getEspecie() + " | ";
        dataAve += "Edad: " + this.getData().getEdad() + " | ";
        dataAve += "Región: " + this.getData().getRegion() + " | ";
        return dataAve;
    }
    
    public boolean esHoja() {
        return this.izquierda == null && this.derecha == null;
    }
}
