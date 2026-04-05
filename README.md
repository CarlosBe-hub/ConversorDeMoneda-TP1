# TP1: Conversor de Moneda 💵💶
**Desarrollado en Android 15**

## Descripción de la app
Aplicación móvil en Android (Java) que permite convertir valores entre Dolares y Euros. El usuario puede ingresar un monto, seleccionar el tipo de conversión (a USD o EUR) y visualizar el resultado. Tambien incluye la opción de modificar la tasa de cambio en el momento.

## Integrantes
* **Carlos Benenatti** - Documento: 44643276

## Implementación de MVVM
El proyecto esta estructurado en tres capas para separar las responsabilidades:

* **View Binding:** Se uso `viewBinding` para la vinculación de vistas. 
* **Model (`MonedaModel`):** Contiene la logica de negocio. Gestiona la tasa de cambio y realiza las operaciones matematicas de conversión de forma independiente a la plataforma Android.
* **ViewModel (`MainViewModel`):** Actua como el cerebro de la aplicación. Valida los datos de entrada y expone el estado de la app mediante `LiveData`, permitiendo que la interfaz reaccione automaticamente a los cambios de datos.
* **View (`MainActivity`):** Se encarga de la representación visual y la interaccion con el usuario. Infla el layout mediante Binding y observa los flujos de datos del ViewModel para actualizar los componentes de la pantalla.
