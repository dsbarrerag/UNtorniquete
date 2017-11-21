package model;

public class Tiempos {
    private int t_entrada;
    private int t_salida;
    private int t_demora;

    public Tiempos(double d, double e, int demora) {
        this.t_entrada = (int)d;
        this.t_salida = (int)e;
        this.t_demora = demora;
    }

    public int getT_entrada() {
        return this.t_entrada;
    }

    public void setT_entrada(int t_entrada) {
        this.t_entrada = t_entrada;
    }

    public int getT_salida() {
        return this.t_salida;
    }

    public void setT_salida(int t_salida) {
        this.t_salida = t_salida;
    }

    public int getT_demora() {
        return this.t_demora;
    }

    public void setT_demora(int t_demora) {
        this.t_demora = t_demora;
    }
}
