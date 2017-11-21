package model;

import controller.App;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Entrada implements Observer{
    private boolean bn;
    private ArrayList<Persona> masaDeGente = new ArrayList();
    Torniquete[] torniquetes;
    boolean[] ocupado;

    public Entrada(int num) {
        this.torniquetes = new Torniquete[num];
        this.ocupado = new boolean[num];
        this.bn = false;
        System.out.println("creados " + num + " torniquetes");

        for(int i = 0; i < this.torniquetes.length; ++i) {
            this.torniquetes[i] = new Torniquete(App.getInstance().getTiempo(), App.getInstance().getSalida(),
                    App.getInstance().getDemora(), i);
            this.torniquetes[i].addObserver(this);
            this.ocupado[i] = false;
            (new Thread(this.torniquetes[i])).start();
        }

    }

    public synchronized void update(Observable o, Object arg) {
        App.getInstance().personaPaso(((Integer)arg).intValue());
        this.check(((Integer)arg).intValue());
    }

    public synchronized void set(int i, Persona p) {
        synchronized(this.torniquetes[i]) {
            App.getInstance().pasarPersona(i);
            this.torniquetes[i].notify();
            System.out.println("torniquete: " + i + " ocupado ");
            this.torniquetes[i].setVars(p.getRetraso(), p.isEntrada());
        }
    }

    public synchronized boolean chk() {
        boolean rt = true;
        int ctr = 0;
        boolean[] var6 = this.ocupado;
        int var5 = this.ocupado.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            boolean x = var6[var4];
            if (x) {
                ++ctr;
            }
        }

        if (ctr == 5) {
            rt = false;
        }

        return rt;
    }

    public synchronized void llegada(Persona p) {
        System.out.println("llega persona " + p);
        this.masaDeGente.add(p);
        this.check(-1);
    }

    public synchronized void check(int n) {
        boolean[] var2 = this.ocupado;
        synchronized(this.ocupado) {
            if (n != -1) {
                System.out.println("torniquete: " + n + " desocupado ");
                this.ocupado[n] = false;
            }

            if (!this.masaDeGente.isEmpty()) {
                for(int i = 0; i < this.ocupado.length; ++i) {
                    if (!this.ocupado[i] && this.torniquetes[i].not_Yet()) {
                        Persona p = (Persona)this.masaDeGente.remove(0);
                        this.ocupado[i] = true;
                        this.set(i, p);
                        break;
                    }
                }
            }

            System.out.println("gente esperando: " + this.masaDeGente.size());
        }
    }
}
