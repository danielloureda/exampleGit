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

La vista tiene una referencia al presenter provista mediante inyección de dependencias y es la encargada de establecer en el presenter la referencia a sí misma. Cada vez que ocurra un evento deberá delegar en el presenter y será este el que decida qué hacer.



![diagrama de presentación][presentationDiagram]

Aunque no existe una manera única de aplicar este patrón, MEDUSA recomienda seguir las siguientes directrices:
- **No** es necesario delegar **absolutamente** todo al presenter. Es decir, si la vista tiene que realizar una serie de inicializaciones **puramente visuales** no es necesario que delegue en el presenter.
- **No** puede haber referencias a componentes nativos de la plataforma en el presenter. De lo contrario estaríamos acoplando el presenter a la forma de mostrar la información.
- **No** es estrictamente necesario tener un view model para cada vista. Dependerá de cada caso si merece la pena crear un view model o no.
- **No** es obligatorio tener una interfaz para el presenter.
- **Sí** es obligatorio tener una interfaz para la vista.

## Domain
## Data
***



[dependencyRules]: /imgs/medusa_layers.jpg "Reglas de dependencia"
[mvpMvcDifferences]: /imgs/mvp_mvc_differences.png "Diferencias entre MVC y MVP"
[presentationDiagram]: /imgs/presentation_diagram.png "Diagrama de presentación"




Dillinger is a cloud-enabled, mobile-ready, offline-storage, AngularJS powered HTML5 Markdown editor.

  - Type some Markdown on the left
  - See HTML in the right
  - Magic

# New Features!

  - Import a HTML file and watch it magically convert to Markdown
  - Drag and drop images (requires your Dropbox account be linked)


You can also:
  - Import and save files from GitHub, Dropbox, Google Drive and One Drive
  - Drag and drop markdown and HTML files into Dillinger
  - Export documents as Markdown, HTML and PDF

Markdown is a lightweight markup language based on the formatting conventions that people naturally use in email.  As [John Gruber] writes on the [Markdown site][df1]

> The overriding design goal for Markdown's
> formatting syntax is to make it as readable
> as possible. The idea is that a
> Markdown-formatted document should be
> publishable as-is, as plain text, without
> looking like it's been marked up with tags
> or formatting instructions.

This text you see here is *actually* written in Markdown! To get a feel for Markdown's syntax, type some text into the left window and watch the results in the right.

### Tech

Dillinger uses a number of open source projects to work properly:

* [AngularJS] - HTML enhanced for web apps!
* [Ace Editor] - awesome web-based text editor
* [markdown-it] - Markdown parser done right. Fast and easy to extend.
* [Twitter Bootstrap] - great UI boilerplate for modern web apps
* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework [@tjholowaychuk]
* [Gulp] - the streaming build system
* [Breakdance](http://breakdance.io) - HTML to Markdown converter
* [jQuery] - duh

And of course Dillinger itself is open source with a [public repository][dill]
 on GitHub.

### Installation

Dillinger requires [Node.js](https://nodejs.org/) v4+ to run.

Install the dependencies and devDependencies and start the server.

```sh
$ cd dillinger
$ npm install -d
$ node app
```

For production environments...

```sh
$ npm install --production
$ npm run predeploy
$ NODE_ENV=production node app
```

### Plugins

Dillinger is currently extended with the following plugins. Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md] [PlDb] |
| Github | [plugins/github/README.md] [PlGh] |
| Google Drive | [plugins/googledrive/README.md] [PlGd] |
| OneDrive | [plugins/onedrive/README.md] [PlOd] |
| Medium | [plugins/medium/README.md] [PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md] [PlGa] |


### Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantanously see your updates!

Open your favorite Terminal and run these commands.

First Tab:
```sh
$ node app
```

Second Tab:
```sh
$ gulp watch
```

(optional) Third:
```sh
$ karma test
```
#### Building for source
For production release:
```sh
$ gulp build --prod
```
Generating pre-built zip archives for distribution:
```sh
$ gulp build dist --prod
```
### Docker
Dillinger is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 80, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.

```sh
cd dillinger
docker build -t joemccann/dillinger:${package.json.version}
```
This will create the dillinger image and pull in the necessary dependencies. Be sure to swap out `${package.json.version}` with the actual version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8000 of the host to port 80 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart="always" <youruser>/dillinger:${package.json.version}
```

Verify the deployment by navigating to your server address in your preferred browser.

```sh
127.0.0.1:8000
```

#### Kubernetes + Google Cloud

See [KUBERNETES.md](https://github.com/joemccann/dillinger/blob/master/KUBERNETES.md)


### Todos

 - Write MOAR Tests
 - Add Night Mode

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
