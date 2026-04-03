# TP1: Conversor de Moneda 💵💶

"Hecho en Android 15"

## Descripción de la app
Aplicación móvil en Android (Java) que permite convertir valores entre Dolares y Euros. El usuario puede ingresar un monto, seleccionar el tipo de conversión (a USD o EUR) y visualizar el resultado. Tambien incluye la opción de modificar la tasa de cambio en el momento.

## Integrantes
* **Carlos Benenatti** - Documento: [44643276]


## Implementación de MVVM
El proyecto esta estructurado en tres capas para separar las responsabilidades:

* **Model (`MonedaModel`):** Maneja puramente la matematica. Guarda la tasa de cambio y hace las cuentas multiplicar/dividir.
* **ViewModel (`MainViewModel`):** Funciona como puente. Ejecuta las validaciones y usa `LiveData` para avisarle a la interfaz grafica que hay nuevos resultados listos para mostrar.
* **View (`MainActivity`):** Se encarga unicamente de lo visual. Dibuja la pantalla.
