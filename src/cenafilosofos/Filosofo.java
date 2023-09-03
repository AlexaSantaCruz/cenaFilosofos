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
    private Object planificador;

    public boolean isEstaComiendo() {
        return estaComiendo;
    }

    public void setEstaComiendo(boolean estaComiendo) {
        this.estaComiendo = estaComiendo;
    }
    
    
    public Filosofo(mesa mesa, int filosofos, Object planificador){
        this.mesa=mesa;
        this.filosofos=filosofos;
        this.numFilosofo=filosofos-1;
        this.planificador=planificador;
        this.estaComiendo=false;
    }
    
    public void run() {
        while (true) {
            this.pensar();
            this.mesa.tomarTenedores(this.numFilosofo);

            synchronized (planificador) {
                // Notificar al planificador cuando comienza a comer
                planificador.notifyAll();
            }

            this.comer();
            System.out.println("Filosofo " + filosofos +  " deja de comer, tenedores libres " + (this.mesa.tenedorIzquierda(this.numFilosofo) + 1) + ", " + (this.mesa.tenedorDerecha(this.numFilosofo) + 1) );
            this.mesa.soltarTenedor(this.numFilosofo);

            synchronized (planificador) {
                // Notificar al planificador cuando deja de comer
                planificador.notifyAll();
            }
        }
    }

    public void pensar(){
        System.out.println("Filosofo"+ filosofos + "esta pensando");
        setEstaComiendo(false);
        try{
            sleep((long)(Math.random()*10000));
            
        }catch(InterruptedException ex){ 
        }
        
    }
    
    public void comer(){
        System.out.println("Filosofo"+ filosofos + "esta comiendo");
        setEstaComiendo(true);
        try{
            sleep((long)(Math.random()*10000));
            
        }catch(InterruptedException ex){ 
        }
        
    }
}
