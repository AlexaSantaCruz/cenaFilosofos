/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cenafilosofos;

/**
 *
 * @author Alexa
 */
public class Filosofo extends Thread{
    private mesa mesa;
    private int filosofos;
    private int numFilosofo;
    private boolean estaComiendo;

    public boolean isEstaComiendo() {
        return estaComiendo;
    }

    public void setEstaComiendo(boolean estaComiendo) {
        this.estaComiendo = estaComiendo;
    }
    
    
    public Filosofo(mesa mesa, int filosofos){
        this.mesa=mesa;
        this.filosofos=filosofos;
        this.numFilosofo=filosofos-1;
    }
    
      public void run(){
         
        while(true){
            this.pensar();
            this.mesa.tomarTenedores(this.numFilosofo);
            this.comer();
            System.out.println("Filosofo " + filosofos +  " deja de comer, tenedores libres " + (this.mesa.tenedorIzquierda(this.numFilosofo) + 1) + ", " + (this.mesa.tenedorDerecha(this.numFilosofo) + 1) );
            this.mesa.soltarTenedor(this.numFilosofo);
        }
         
    }
    public void pensar(){
        System.out.println("Filosofo"+ filosofos + "esta pensando");
        setEstaComiendo(false);
        try{
            sleep((long)(Math.random()*3000));
            
        }catch(InterruptedException ex){ 
        }
        
    }
    
    public void comer(){
        System.out.println("Filosofo"+ filosofos + "esta comiendo");
        setEstaComiendo(true);
        try{
            sleep((long)(Math.random()*2000));
            
        }catch(InterruptedException ex){ 
        }
        
    }
}
