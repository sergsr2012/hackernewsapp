# hackernewsapp

La app se puede probar de la forma común con los siguientes comandos y con un dispositivo Android conectado por usb:

```
gradlew assembleDebug
gradlew installDebug
```

o usando Android Studio click en el botón run eligiendo como un destino uno de los  simuladores del AVD Manager o un teléfono conectado.



# Aclaraciones y justificación de la solución plateada.




- En una arquitectura domain-oriented el dominio tiene que estar completamente desacoplada del framework y otros componentes. Sin embargo, aunque aplique el principio de inversión de dependencias para acceder al repositorio desde el dominio, por practicidad compartí varias veces las clases modelo/pojo/data class entre clases; ya que debido a la lógica del negocio ligera de este ejercicio, todos los modelos serían muy parecidos y los mappers repetitivos. Aun así, hubo partes en donde si separé los modelos cuando parecía que era provechoso para este caso particular como por ejemplo, mapear la respuesta del API bastante irregular y con muchos valores nulos o para ejemplificar la estructura a seguir.

- En general, todos los “detalles” deberían ser abstraídos, pero en este caso, por tiempo no alcancé a implementar todas las interfaces y composiciones necesarias. 

- Los UseCases están simplificados por practicidad y toman JavaRx de forma transversal. Si hubiera alguna lógica de negocio compleja, estos tomarían el stream de Observables y modificarían los datos que proviene del repositorio. Pero, dependiendo del caso particular, puede ser útil que los UseCases sean reactivos también. 

- La lógica de cambio de hilo se abstrae usando JavaRx y los viewmodels usan el ciclo de suscripción para mapear los estados posibles (LOADING, ÉXITO, ERROR) y “postearlos” a las vistas. Luego se deshace de  las suscripciones usando un compositeDisposable cuando el ciclo de vida del fragmento muere.

- La aplicación carga los primeros 20 elementos y luego puede llegar hasta la pagina 10 paginando cuando se realiza scroll down (lazy load). Carga datos (todos, incluyendo los que no tienen url) durante el inicio del app y almacena en una cache los datos ara ser consultada sin conexion.

- Seguí la estructura propuesta por Google con una activity que hospeda varios fragmentos agrupando secciones y por eso usé Navigation de Jetpack como complemento. Ya que facilita estas tareas y maneja el back stack automaticamente. Esta librería podrías ser útil para abstraer parte del problema de la navegación y alto acoplamiento entre vistas.
