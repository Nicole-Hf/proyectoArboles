/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herbas.ed2.treeproject;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Nicole
 */
public class ArbolBinario {
    protected Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public void agregarNodo(String especie, int edad, String region) {
        this.agregarNodo(this.raiz,especie,edad,region);
    }

    private void agregarNodo(Nodo nodo, String especie, int edad, String region) {
        Ave ave = new Ave(especie,edad,region);
        if (nodo == null) {
            this.setRaiz(new Nodo(ave));
        } else {
            //Por especie
            String especieAve = ave.getEspecie();
            String especieNodo = nodo.getData().getEspecie();
            if (especieAve.compareTo(especieNodo) < 0) {
                if (nodo.getIzquierda() == null) {
                    nodo.setIzquierda(new Nodo(ave));
                } else {
                    this.agregarNodo(nodo.getIzquierda(), especie, edad, region);
                }             
            } else if (especieAve.compareTo(especieNodo) > 0) {
                if (nodo.getDerecha() == null) {
                    nodo.setDerecha(new Nodo(ave));
                } else {
                    this.agregarNodo(nodo.getDerecha(), especie, edad, region);
                }
            } else {
                //Por edad
                int edadAve = ave.getEdad();
                int edadNodo = nodo.getData().getEdad();
                if (edadAve < edadNodo) {
                    if (nodo.getIzquierda() == null) {
                        nodo.setIzquierda(new Nodo(ave));
                    } else {
                        this.agregarNodo(nodo.getIzquierda(), especie, edad, region);
                    }                   
                } else if (edadAve > edadNodo) {
                    if (nodo.getDerecha() == null) {
                        nodo.setDerecha(new Nodo(ave));
                    } else {
                        this.agregarNodo(nodo.getDerecha(), especie, edad, region);
                    }
                } else {
                    //Por región
                    String regionAve = ave.getRegion();
                    String regionNodo = nodo.getData().getRegion();
                    if (regionAve.compareTo(regionNodo) < 0) {
                        if (nodo.getIzquierda() == null) {
                            nodo.setIzquierda(new Nodo(ave));
                        } else {
                            this.agregarNodo(nodo.getIzquierda(), especie, edad, region);
                        }
                    } else if (regionAve.compareTo(regionNodo) > 0) {
                        if (nodo.getDerecha() == null) {
                            nodo.setDerecha(new Nodo(ave));
                        } else {
                            this.agregarNodo(nodo.getDerecha(), especie, edad, region);
                        }
                    }
                } 
            }
        }       
    }
    
    public String verArbol() {
        return verArbol(raiz, 1);
    }
    
    private String verArbol(Nodo nodoActual, int nivel) {        
        String s = "";
        if(nodoActual != null) {
            s = nodoActual.getData().getEspecie();
            if(!"".equals(nodoActual.getData().getRegion())) {
                s = s + "/" + nodoActual.getData().getEdad() + "/" + nodoActual.getData().getRegion();
            }
            s = s + " { \n";           
            s = s + separador(nivel) + verArbol(nodoActual.getIzquierda(), nivel+1) + "\n";
            s = s + separador(nivel) + verArbol(nodoActual.getDerecha(), nivel+1) + "\n";
            s = s + separador(nivel-1) + "}";
        }
        return s;
    }
       
    private String separador(int nivel) {
        String s = "";
        for(int i = 0; i < nivel; i++) {
            s = s + "    ";
        }
        return s;
    }
    
    public List<Ave> recorridoPorNiveles() {
        List<Ave> recorrido = new LinkedList<>();
        if (this.raiz == null) {
            return recorrido;
        }
        
        Queue<Nodo> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        
        while (!colaDeNodos.isEmpty()) {
            Nodo nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getData());
            
            if (nodoActual.getIzquierda() != null) {
                colaDeNodos.offer(nodoActual.getIzquierda());
            }
            if (nodoActual.getDerecha() != null) {
                colaDeNodos.offer(nodoActual.getDerecha());
            }
        }
        return recorrido;
    }
    
    public List<String> mostrarAvesPorRegion(String region) {
        List<String> aves = new ArrayList<>();
        List<Ave> listaDeNodos = this.recorridoPorNiveles();
        for (int i = 0; i < listaDeNodos.size(); i++) {
            String regionActual = listaDeNodos.get(i).getRegion();
            if (region.equals(regionActual)) {
                String especie = listaDeNodos.get(i).getEspecie();
                aves.add(especie);
            }
        }
        return aves;
    }
    
    public List<String> mostrarUbicacionAves(String especie) {
        List<String> regiones = new ArrayList<>();
        List<Ave> listaDeNodos = this.recorridoPorNiveles();
        for (int i = 0; i < listaDeNodos.size(); i++) {
            String especieActual = listaDeNodos.get(i).getEspecie();
            if (especie.equals(especieActual)) {
                String region = listaDeNodos.get(i).getRegion();
                regiones.add(region);
            }
        }
        return regiones;
    }
    
    public void eliminar(String especie, String region) {     
        this.raiz = eliminar(this.raiz,especie, region);
    }

    private Nodo eliminar(Nodo nodoActual, String especie, String region) {
        String especieActual = nodoActual.getData().getEspecie();
        String regionActual = nodoActual.getData().getRegion();
        if (especie.compareTo(especieActual) < 0) {
            Nodo supuestoIzquierdo = eliminar(nodoActual.getIzquierda(),especie, region);
            nodoActual.setIzquierda(supuestoIzquierdo);
            return nodoActual;
        } 
        if (especie.compareTo(especieActual) > 0) {
            Nodo supuestoDerecho = eliminar(nodoActual.getDerecha(),especie, region);
            nodoActual.setDerecha(supuestoDerecho);
            return nodoActual;
        }
        if (region.compareTo(regionActual) < 0) {
            Nodo supuestoIzquierdo = eliminar(nodoActual.getIzquierda(),especie, region);
            nodoActual.setIzquierda(supuestoIzquierdo);
            return nodoActual;
        }
        if (region.compareTo(regionActual) > 0) {
            Nodo supuestoDerecho = eliminar(nodoActual.getDerecha(),especie, region);
            nodoActual.setDerecha(supuestoDerecho);
            return nodoActual;
        }
        if (nodoActual.esHoja()) {
            return null;
        }
        
        if (nodoActual.getIzquierda() != null && nodoActual.getDerecha() == null) {
            return nodoActual.getIzquierda();
        }
        
        if (nodoActual.getIzquierda() == null && nodoActual.getDerecha() != null) {
            return nodoActual.getDerecha();
        }
        
        Nodo nodoSucesor = buscarSucesor(nodoActual.getDerecha());
        Nodo supuestoHijo = eliminar(nodoActual.getDerecha(),
                nodoSucesor.getData().getEspecie(), nodoSucesor.getData().getRegion());
        nodoActual.setDerecha(supuestoHijo);
        nodoActual.setData(nodoSucesor.getData());
        return nodoActual;
    }

    private Nodo buscarSucesor(Nodo nodoActual) {
        Nodo nodoAnterior = null;
        while (nodoActual != null) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getIzquierda();
        }
        return nodoAnterior;
    }
    
    public String buscar(String especie) {
        Nodo nodoActual = this.raiz;
        
        while (nodoActual != null) {
            String especieActual = nodoActual.getData().getEspecie();
            if (especie.compareTo(especieActual) < 0) {
                nodoActual = nodoActual.getIzquierda();
            } else if (especie.compareTo(especieActual) > 0) {
                nodoActual = nodoActual.getDerecha();
            } else {
                return nodoActual.getData().getRegion();
            }
        }
        //la clave a buscar no se encuentra en el arbol
        return null;
    }
}
