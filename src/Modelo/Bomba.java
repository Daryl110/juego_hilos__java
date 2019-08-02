/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.FrmPrincipal;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Daryl Ospina
 */
public class Bomba extends Thread {

    private String colorGenerado, colorIngresado;
    private final JLabel lblSegundos;
    private final FrmPrincipal ventanaPrincipal;

    public Bomba(String name, JLabel lblSegundos, FrmPrincipal ventanaPrincipal) {
        super(name);
        this.colorIngresado = "";
        this.lblSegundos = lblSegundos;
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @Override
    public void run() {
        this.colorGenerado = this.generarColor();
        System.out.println(Thread.currentThread().getName()+ " : "+this.colorGenerado); //--ver colores solo para pruebas
        try {
            for (int i = 20; i >= 0; i--) {
                if (i < 10) {
                    this.lblSegundos.setForeground(Color.red);
                    this.lblSegundos.setText("0" + i);
                } else {
                    this.lblSegundos.setForeground(Color.black);
                    this.lblSegundos.setText("" + i);
                }
                if (this.colorGenerado.equals(this.colorIngresado)) {
                    JOptionPane.showMessageDialog(null, Thread.currentThread().getName() + " a sido desactivada");
                    this.ventanaPrincipal.verificarJuegoGanado(Thread.currentThread().getName());
                    this.interrupt();
                } else if (!this.colorIngresado.equalsIgnoreCase("")
                        && !this.colorIngresado.equals(this.colorGenerado)) {
                    this.lblSegundos.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, "GAME OVER!!!");
                    this.ventanaPrincipal.pararBombas();
                }
                this.sleep(1000);
            }
            this.lblSegundos.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "GAME OVER!!!");
        } catch (InterruptedException e) {

        }
    }

    public void cortarCable(String color) {
        this.colorIngresado = color;
    }

    private String generarColor() {
        String[] colores = {"AZUL", "NEGRO", "ROJO"};
        int numero = (int) (Math.random() * 3);
        return colores[numero];
    }
}
