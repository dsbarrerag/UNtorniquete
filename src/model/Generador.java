package model;

import controller.App;

import java.awt.*;
import java.util.Random;

public class Generador implements Runnable {
    Random rand = new Random();
    int rutina;
    int secuencia;
    int contador;
    int numero;
    int personas;
    int t_generacion;
    int a;
    int b;
    int c;
    int d;
    int e;
    double p_salida;
    Entrada ent;

    public Generador(int g, double s, int num) {
        this.personas = g;
        this.p_salida = s;
        this.numero = num;
        this.rutina = 0;
    }

    public Generador(int num, int i) {
        this.numero = num;
        this.rutina = i;
        this.secuencia = 1;
        this.contador = 0;
    }

    private void secuencia() {
        ++this.contador;
        switch(this.secuencia) {
            case 1:
                if (this.contador == this.a) {
                    this.secuencia = 2;
                    this.contador = 0;
                } else {
                    this.t_generacion = 60 / this.a * 1000;
                }

                System.out.println(this.t_generacion);
                break;
            case 2:
                if (this.contador == this.b) {
                    this.secuencia = 3;
                    this.contador = 0;
                } else {
                    this.t_generacion = 60 / this.b * 1000;
                }
                break;
            case 3:
                if (this.contador == this.c) {
                    this.secuencia = 4;
                    this.contador = 0;
                } else {
                    this.t_generacion = 60 / this.c * 1000;
                }
                break;
            case 4:
                if (this.contador == this.d) {
                    this.secuencia = 5;
                    this.contador = 0;
                } else {
                    this.t_generacion = 60 / this.d * 1000;
                }
                break;
            case 5:
                if (this.contador == this.e) {
                    this.secuencia = 1;
                    this.contador = 0;
                } else {
                    this.t_generacion = 60 / this.e * 1000;
                }
        }

    }

    private void rutina() {
        switch(this.rutina) {
            case 1:
                this.p_salida = 0.2D;
                this.a = 50;
                this.b = 100;
                this.c = 80;
                this.d = 50;
                this.e = 20;
                break;
            case 2:
                this.p_salida = 0.8D;
                this.a = 50;
                this.b = 100;
                this.c = 80;
                this.d = 50;
                this.e = 20;
                break;
            case 3:
                this.p_salida = 0.5D;
                this.a = 80;
                this.b = 120;
                this.c = 90;
                this.d = 70;
                this.e = 40;
        }

        this.secuencia();
    }

    private void manual() {
        this.t_generacion = 60 / this.personas;
        this.t_generacion *= 1000;
    }

    public void run() {
        this.ent = new Entrada(this.numero);

        while(true) {
            if (this.rutina == 0) {
                this.manual();
            } else {
                this.rutina();
            }

            this.generar();

            try {
                Thread.sleep((long)this.t_generacion);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }
    }

    private synchronized void generar() {
        Integer a = (int)(Math.random() * 20.0D + 1.0D);
        int dmr = (int)(Math.random() * 1.0D * 1000.0D);
        float r = (float)((double)(this.rand.nextFloat() / 2.0F) + 0.5D);
        float g = (float)((double)(this.rand.nextFloat() / 2.0F) + 0.5D);
        float b = (float)((double)(this.rand.nextFloat() / 2.0F) + 0.5D);
        Persona p = new Persona(dmr, this.exp(this.p_salida), a.toString(), new Color(r, g, b));
        this.ent.llegada(p);
        App.getInstance().llegaPersona(p);
    }

    private boolean exp(double p) {
        return Math.random() * 1.0D > p;
    }
}
