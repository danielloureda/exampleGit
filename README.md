# MEDUSA
MEDUSA es la arquitectura para aplicaciones móviles que se utiliza en el departamento de Movilidad de Minsait. Pretende **unificar** los desarrollos nativos en iOS y Android y estabecer unas pautas que todos los proyectos **deben** seguir. Con ello se persiguen los siguientes objetivos:

- Interoperabilidad entre proyectos: Cualquier miembro del departamento podrá trabajar en cualquier proyecto desarrollado con esta arquitectura.
- Desaparición del tiempo de adaptación en el cambio de proyectos: Solo habría que dedicar tiempo a conocer las particularidades de cada proyecto.
- Mejora de calidad de los entregables.
- Mejora del propio producto: MEDUSA no es un producto cerrado. A medida que se van desarrollando proyectos se van corrigiendo posibles errores y se van incorporando mejoras.

Para tener una aplicación mantenible, poco acoplada y altamente cohesiva se hace necesario separarla en capas:
- App (presentation): Se encarga de la interacción con el usuario, de la lógica de vista y de la inyección de dependencias.
- Domain: Contiene los casos de uso, es decir, la lógica de negocio de la aplicación.
- Data: Se encarga del acceso a datos, bien estén en cloud, en base de datos, en caché...

En la siguiente imagen se puede observar que la capa de dominio es totalmente independiente de las demás. Esto tiene sentido ya que la lógica de dominio no tiene relación con la vista en la que se va a pintar ni con cómo acceder a los datos que se necesitan. La capa da datos es independiente de la capa de aplicación pero conoce a dominio. Por último, app conoce a ambas. Más adelante explicaremos por qué y las ventajas e inconvenientes que tiene.

![reglas de dependencia][dependencyRules]

A continuación se explican detalladamente cada una de las capas.

## App (presentation)
La capa de aplicación se encarga de presentar y obtener información al y del usuario, de controlar la navegación entre pantallas y de gestionar la inyección de dependencias.
Para lo primero se utiliza el patrón de diseño *Model View Presenter* (**MVP**). MVP deriva del ya conocido *Model View Controller* o MVC y permite separar la pura presentación de la lógica derivada de la misma, de manera que el cómo funciona la interfaz queda separado del cómo se representa. Idealmente, con la misma lógica, podría haber diferentes vistas para un mismo presenter y podríamos intercambiarlas y todo funcionaría correctamente. A continuación se muestra la diferencia entre MVC y MVP:

![diferencias MVP y MVC][mvpMvcDifferences]

El **presenter** contiene toda la lógica **no visual** de la interfaz:
- Comunica a la interfaz los datos que va a mostrar.
- Decide qué hacer ante eventos en la interfaz.
- Es la puerta de salida hacia la capa de dominio y la navegación.

La **vista** se encarga de saber cómo mostrar la información que le proporciona el presenter. **No** conoce el modelo de negocio, pero sí el **View model**. El view model es una transformación del modelo de negocio que conoce la vista. La vista sabe cómo representar un objeto view model pero no debería saber cómo representar un objeto de negocio.
Normalmente la transformación de un objeto de modelo a un view model se realiza a través de un **mapper** o de un **wrapper**. Ambos crean un objeto view model de uno de modelo pero, mientras que el primero crea un view model a partir del modelo, el segundo envuelve el objeto del modelo en un view model. En general, utilizaremos el primer método cuando view model y model no tengan mucho que ver y el segundo cuando ambos sean muy parecidos.

La vista tiene una referencia al presenter provista mediante inyección de dependencias y es la encargada de establecer en el presenter la referencia a sí misma. Cada vez que ocurra un evento deberá delegar en el presenter y será este el que decida qué hacer. La vista es **pasiva**, es decir, nunca pregunta al presenter sobre los datos que tiene que mostrar sino que es el presenter el que envía los datos a la vista cuando haya algo que mostrar.

El **navegador** (o *navigator*) es el encargado de controlar el flujo de la aplicación. Ante ciertos eventos el presenter delega en el navigator para que decida qué se tiene que hacer o qué pantalla hay que mostrar.

![diagrama de presentación][presentationDiagram]

Aunque no existe una manera única de aplicar este patrón, MEDUSA recomienda seguir las siguientes directrices:
- **No** es necesario delegar **absolutamente** todo al presenter. Es decir, si la vista tiene que realizar una serie de inicializaciones **puramente visuales** no es necesario que delegue en el presenter.
- **No** puede haber referencias a componentes nativos de la plataforma en el presenter. De lo contrario estaríamos acoplando el presenter a la forma de mostrar la información.
- **No** es estrictamente necesario tener un view model para cada vista. Dependerá de cada caso si merece la pena crear un view model o no.
- **No** es obligatorio tener una interfaz para el presenter.
- **Sí** es obligatorio tener una interfaz para la vista.

## Domain
Dominio es la capa más importante de una aplicación ya que define el comportamiento de la misma. Está dividida en **casos de uso**. Por caso de uso entendemos una secuencia o flujo de interacciones entre distintos subsistemas o componentes para realizar una función determinada. Normalmente suelen estar relacionados con requisitos funcionales aunque no necesariamente tiene que ser así. Los casos de uso deben ser pequeños y bien definidos.
Como se explicaba previamente, dominio tiene que ser una capa totalmente independiente de las demás y tiene que poder funcionar por sí misma independientemente de las otras.
Es la capa de aplicación exclusivamente la que se comunica con dominio. El punto de entrada son los casos de uso.

![diagrama de componentes][componentsDiagram]

Dominio expone unas interfaces para acceder a almacenamiento local o en *cloud* para que data las implemente. Para dominio es transparente la implementación ya que se comunica solo con las interfaces. Es por tanto aquí, en el caso de uso, donde se toma la decisión de ir a por datos a cloud, a base de datos, a caché... 
Dominio trabaja con el modelo de datos de dominio única y exclusivamente ya que no conoce modelo ni de presentation ni de data.

## Data
MEDUSA concibe la capa de data como un almacén de datos puro. Data sabe cómo devolver datos (ya sea de base de datos, de servicio, de caché...) pero **no** decide de dónde devolverlos. Como se explica previamente, esa decisión pertenece a dominio. Data implementa las interfaces que expone dominio y dominio elige a cuál de ellas llamar para obtener datos.
Data tiene su propio modelo de datos y como solo tiene como punto de entrada a dominio y dominio tiene su propio modelo de datos, es responsabilidad de data transformar mediante mappers o wrappers los datos para que los pueda manejar dominio.

![diagrama de data][dataDiagram]

### Conclusiones
Utilizar MEDUSA tiene como principal objetivo unificar todos los desarrollos de aplicaciones móviles que se realicen en el departamento de movilidad de Minsait, tanto para iOS como para Android.
Con ello se consigue tener un código:
- Mantenible.
- Robusto.
- Flexible.
- Testable.

***


[dependencyRules]: /imgs/medusa_layers.jpg "Reglas de dependencia"
[mvpMvcDifferences]: /imgs/mvp_mvc_differences.png "Diferencias entre MVC y MVP"
[presentationDiagram]: /imgs/presentation_diagram.png "Diagrama de presentación"
[componentsDiagram]: /imgs/components_diagram.png "Diagrama de componentes"
[dataDiagram]: /imgs/data_diagram.png "Diagrama de data"
