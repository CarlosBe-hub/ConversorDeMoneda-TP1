package com.tech.tp1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MonedaModel modelo;

    // liveData para observar los cambios en la UI
    private MutableLiveData<String> dolaresResult = new MutableLiveData<>();
    private MutableLiveData<String> eurosResult = new MutableLiveData<>();
    private MutableLiveData<String> tasaActual = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public MainViewModel() {

        modelo = new MonedaModel(0.92);
        actualizarTextoTasa();
    }

    // getters para que la Activity pueda observar
    public LiveData<String> getDolaresResult() { return dolaresResult; }
    public LiveData<String> getEurosResult() { return eurosResult; }
    public LiveData<String> getTasaActual() { return tasaActual; }
    public LiveData<String> getMensajeError() { return mensajeError; }

    // logica principal de conversion
    public void realizarConversion(String montoStr, boolean aEuros) {
        if (montoStr == null || montoStr.trim().isEmpty()) {
            mensajeError.setValue("Por favor, ingrese un valor valido.");
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr); // Validar valores

            if (aEuros) {
                // convertir a Euros
                double resultado = modelo.dolaresAEuros(monto);
                eurosResult.setValue(String.format("%.2f", resultado));
            } else {
                // convertir a Dolares
                double resultado = modelo.eurosADolares(monto);
                dolaresResult.setValue(String.format("%.2f", resultado));
            }
        } catch (NumberFormatException e) {
            mensajeError.setValue("El formato numerico es incorrecto!.");
        }
    }

    // modificar el tipo de cambio
    public void cambiarTasaDeCambio(String nuevaTasaStr) {
        if (nuevaTasaStr == null || nuevaTasaStr.trim().isEmpty()) {
            mensajeError.setValue("Ingrese una tasa valida!.");
            return;
        }
        try {
            double nuevaTasa = Double.parseDouble(nuevaTasaStr);
            modelo.setTasaDeCambio(nuevaTasa);
            actualizarTextoTasa();
        } catch (NumberFormatException e) {
            mensajeError.setValue("Tasa de cambio invalida!.");
        }
    }

    private void actualizarTextoTasa() {

        tasaActual.setValue("1$ = " + modelo.getTasaDeCambio() + " EUR");
    }
}