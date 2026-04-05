package com.tech.tp1;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.tech.tp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding; // usamos binding para acceder a los componentes de la activity main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // configuramos el Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

       // llamamos a los metodos de la configuracion inicial
        observarViewModel();
        configurarBotones();
    }

    private void observarViewModel() {
        // accedemos a los componentes a través de 'binding'
        viewModel.getDolaresResult().observe(this, resultado -> binding.etDolares.setText(resultado));
        viewModel.getEurosResult().observe(this, resultado -> binding.etEuros.setText(resultado));
        viewModel.getTasaActual().observe(this, tasa -> binding.tvTasaActual.setText(tasa));

        // mostrar mensajes de error
        viewModel.getMensajeError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarBotones() {
        // boton para ejecutar la operacion
        binding.btnConvertir.setOnClickListener(v -> {
            boolean convertirAEuros = binding.rbAEuros.isChecked();
            String montoAConvertir;

            if (convertirAEuros) {
                montoAConvertir = binding.etDolares.getText().toString();
            } else {
                montoAConvertir = binding.etEuros.getText().toString();
            }

            viewModel.realizarConversion(montoAConvertir, convertirAEuros);
        });

        // boton para modificar el tipo de cambio
        binding.btnCambiarValor.setOnClickListener(v -> {
            String nuevaTasa = binding.etNuevaTasa.getText().toString();
            viewModel.cambiarTasaDeCambio(nuevaTasa);
        });
    }
}