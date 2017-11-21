package model;

import java.util.Observable;

public class Torniquete extends Observable implements Runnable {
    private int tiempo;
    private int entrada;
    private int salida;
    private int numero;
    private int tiempo_cambio;
    private double delay;
    private boolean ent;
    private boolean chg;
    private boolean not_yet;

    public Torniquete(int e, int s, int d, int num) {
        this.entrada = e;
        this.salida = s;
        this.tiempo_cambio = d;
        this.tiempo = this.entrada;
        this.numero = num;
        this.ent = true;
        this.chg = false;
        this.not_yet = false;
    }

    public void run() {
        synchronized(this){}

        try {
            while(true) {
                while(true) {
                    try {
                        this.not_yet = true;
                        this.wait();
                        this.not_yet = false;
                        this.entrando();
                    } catch (InterruptedException var5) {
                        var5.printStackTrace();
                    }
                }
            }
        } catch (Throwable var6) {
            throw var6;
        }
    }

    private void entrando() {
        int tp = (int)(this.chg ? (double)this.tiempo + this.delay + (double)this.tiempo_cambio : (double)this.tiempo + this.delay);
        System.out.println("en el torniquete " + this.numero + " se demora entrando: " + tp);

        try {
            Thread.sleep((long)tp);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }

        this.chg = false;
        this.setChanged();
        this.notifyObservers(this.numero);

        try {
            Thread.sleep(200L);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    public void count(int n) {
        for(int i = 0; i < n * 100; ++i) {
            ;
        }

    }

    public synchronized void setVars(double i, boolean entrando) {
        this.delay = i;
        if (entrando != this.ent) {
            this.ent = entrando;
            this.tiempo = this.ent ? this.entrada : this.salida;
            this.chg = true;
        }

    }

    public boolean not_Yet() {
        return this.not_yet;
    }
}
