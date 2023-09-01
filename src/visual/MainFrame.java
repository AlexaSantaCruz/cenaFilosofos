/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual;

import cenafilosofos.Filosofo;
import cenafilosofos.mesa;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    private JLabel accionLabel;

    public MainFrame() {
        setTitle("Estado de los Filósofos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        accionLabel = new JLabel();
        add(accionLabel, BorderLayout.CENTER);
        
        // Crear e iniciar hilos de filósofos y el hilo de actualización de la interfaz
        mesa mesa = new mesa(5);
        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(mesa, i + 1);
            filosofos[i].start();
        }

        Thread uiUpdateThread = new Thread(() -> {
            while (true) {
                // Actualizar la interfaz gráfica en el hilo de la UI
                SwingUtilities.invokeLater(() -> updateUI(filosofos));

                try {
                    Thread.sleep(2000); // Esperar antes de actualizar nuevamente
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        uiUpdateThread.start();
    }

    private void updateUI(Filosofo[] filosofos) {
        for (Filosofo filosofo : filosofos) {
            if (filosofo.isEstaComiendo()) {
                // Cambiar la imagen en el JLabel para el filósofo que está comiendo
                accionLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("comiendo.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            } else {
                // Cambiar la imagen en el JLabel para el filósofo que está pensando
                accionLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("pensando.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
