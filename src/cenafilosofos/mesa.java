/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cenafilosofos;


import java.util.logging.Logger;
import java.util.logging.Level;


/**
 *
 * @author Alexa
 */

public class mesa {
    
    //hay un arreglo de booleanos que representa los tenedores
    //"Soltar un tenedor" es false "tomar un tenedor" es true
    private boolean[] tenedores;
    
    public mesa(int numTenedores){
        this.tenedores =new boolean[numTenedores];
    }
    
    //get para saber el indice del tenedor en izquierda, el de izquierda es el de mismo indice que el filosofo
    public int tenedorIzquierda(int i){
        return i;
    }
    
    //get para saber el indice del tenedor en derecha, el de derecha es el de mismo indice que el filosofo -1
    //si es el primero se va a el ultimo indice
    public int tenedorDerecha(int i){
        if(i==0){
            return this.tenedores.length -1;
            
        }else
            return i-1;
    }
    
    
    public synchronized void tomarTenedores(int numFilosofo){
        //si esta tomando cualquiera de los tenedores, espera al hilo
        //esto para que no todos se queden con un tenedor esperando y cree un lock
        while(tenedores[tenedorIzquierda(numFilosofo)]||
                tenedores[tenedorDerecha(numFilosofo)]){
            try{
                wait();
            }catch(InterruptedException ex){
                Logger.getLogger(mesa.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        
        //toma los tenedores
        tenedores[tenedorIzquierda(numFilosofo)]=true;
        tenedores[tenedorDerecha(numFilosofo)]=true;
    }
    
    public synchronized void soltarTenedor(int numFilosofo){
        tenedores[tenedorIzquierda(numFilosofo)]=false;
        tenedores[tenedorDerecha(numFilosofo)]=false;
        notifyAll();
    }
   
}

