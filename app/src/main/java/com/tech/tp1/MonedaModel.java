package com.tech.tp1;

public class MonedaModel {
    private double tasaDeCambio;

    public MonedaModel(double tasaInicial) {
        this.tasaDeCambio = tasaInicial;
    }

    public double getTasaDeCambio() {
        return tasaDeCambio;
    }

    public void setTasaDeCambio(double tasaDeCambio) {
        this.tasaDeCambio = tasaDeCambio;
    }

    public double dolaresAEuros(double dolares) {
        return dolares * tasaDeCambio;
    }

    public double eurosADolares(double euros) {
        return euros / tasaDeCambio;
    }
}
