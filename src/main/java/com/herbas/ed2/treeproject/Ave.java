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
public class Ave {
    private String especie;
    private int edad;
    private String region;

    public Ave(String especie, int edad, String region) {
        this.especie = especie;
        this.edad = edad;
        this.region = region;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public String mostrar() {
        String dataAve = "";
        dataAve += "Especie: " + this.getEspecie() + "\n";
        dataAve += "Edad: " + this.getEdad() + "\n";
        dataAve += "Región: " + this.getRegion() + "\n";
        return dataAve;
    }
}
