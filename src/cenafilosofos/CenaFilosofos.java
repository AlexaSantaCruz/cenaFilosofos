/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cenafilosofos;

/**
 *
 * @author Alexa
 */
public class CenaFilosofos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mesa m = new mesa(5);
        for (int i = 1; i <= 5; i++) {
            Filosofo f = new Filosofo(m, i);
            f.start();
        }    }
    
}
