package com.tech.tp1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    // componentes de la UI
    private EditText etDolares, etEuros, etNuevaTasa;
    private TextView tvTasaActual;
    private RadioButton rbAEuros, rbADolares;
    private Button btnConvertir, btnCambiarValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // vincular componentes de la UI
        etDolares = findViewById(R.id.etDolares);
        etEuros = findViewById(R.id.etEuros);
        etNuevaTasa = findViewById(R.id.etNuevaTasa);
        tvTasaActual = findViewById(R.id.tvTasaActual);
        rbAEuros = findViewById(R.id.rbAEuros);
        rbADolares = findViewById(R.id.rbADolares);
        btnConvertir = findViewById(R.id.btnConvertir);
        btnCambiarValor = findViewById(R.id.btnCambiarValor);

        observarViewModel();
        configurarBotones();
    }

    private void observarViewModel() {
        // observar datos del ViewModel y actualizar la UI
        viewModel.getDolaresResult().observe(this, resultado -> etDolares.setText(resultado));
        viewModel.getEurosResult().observe(this, resultado -> etEuros.setText(resultado));
        viewModel.getTasaActual().observe(this, tasa -> tvTasaActual.setText(tasa));

        // mostrar mensajes de error
        viewModel.getMensajeError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarBotones() {
        // boton para ejecutar la operacion
        btnConvertir.setOnClickListener(v -> {
            boolean convertirAEuros = rbAEuros.isChecked();
            String montoAConvertir;

            if (convertirAEuros) {
                montoAConvertir = etDolares.getText().toString();
            } else {
                montoAConvertir = etEuros.getText().toString();
            }

            viewModel.realizarConversion(montoAConvertir, convertirAEuros);
        });

        // boton para modificar el tipo de cambio
        btnCambiarValor.setOnClickListener(v -> {
            String nuevaTasa = etNuevaTasa.getText().toString();
            viewModel.cambiarTasaDeCambio(nuevaTasa);
        });
    }
}