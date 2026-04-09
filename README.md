# Bank App

Sistema de simulacion bancaria orientado a objetos, desarrollado en Java con Gradle.

El proyecto modela operaciones y entidades de un banco (clientes, sucursales, cuentas y registros), y valida el comportamiento mediante pruebas unitarias y pruebas funcionales BDD con Cucumber + Gherkin.

## Objetivos del proyecto

- Modelar reglas de negocio bancarias de forma clara y mantenible.
- Validar escenarios funcionales con lenguaje natural (Gherkin).
- Mantener trazabilidad entre implementacion, pruebas y documentacion tecnica.

## Funcionalidades principales

- Alta, busqueda y baja de clientes.
- Alta, modificacion y baja de sucursales.
- Creacion y administracion de cuentas bancarias con CBU y alias unicos.
- Operaciones de deposito, extraccion y transferencias por CBU o alias.
- Registro de transacciones (deposito, extraccion, transferencias).
- Busqueda de sucursal asociada a una cuenta.
- Registro y consulta de fecha de matrimonio entre clientes.

## Tecnologias

- Java
- Gradle
- JUnit 5
- Cucumber (cucumber-java, cucumber-junit)
- Gherkin

## Estructura del repositorio

```text
Bank-APP/
├─ README.md
└─ MeMo1-Ejercicio1/
	├─ app/
	│  ├─ src/main/java/memo1/ejercicio1/
	│  └─ src/test/
	│     ├─ java/memo1/ejercicio1/
	│     └─ resources/memo1/ejercicio1/   (features Gherkin)
	├─ docs/
	│  ├─ DC/
	│  ├─ ddbb/
	│  ├─ HDD/
	│  ├─ MDD/
	│  ├─ Prototypes/
	│  └─ reports/
	└─ gradlew / gradlew.bat
```

## Como ejecutar el proyecto

### Requisitos

- JDK 17 o superior (recomendado)
- Gradle Wrapper (incluido en el repositorio)

### Ejecutar pruebas (Linux/macOS)

```bash
cd MeMo1-Ejercicio1
./gradlew clean test
```

### Ejecutar pruebas (Windows)

```bat
cd MeMo1-Ejercicio1
gradlew.bat clean test
```

## Pruebas

El proyecto contiene dos capas de validacion:

- Pruebas unitarias en `app/src/test/java/memo1/ejercicio1/`.
- Pruebas funcionales BDD en `app/src/test/resources/memo1/ejercicio1/`.

Reportes generados:

- `MeMo1-Ejercicio1/app/build/reports/tests/test/index.html`
- `MeMo1-Ejercicio1/app/build/reports/cucumber/cucumber-html-report.html`

## Documentacion disponible

Ademas del codigo fuente, el proyecto incluye documentacion de analisis, modelado y diseno en `MeMo1-Ejercicio1/docs/`:

- `DC/Ejercicio3DCVersion2.pdf`
- `ddbb/DiseñoDeDatos.pdf`
- `HDD/HDDVersion3.pdf`
- `MDD/MDDVersion4.pdf`
- `Prototypes/Prototipos.pdf`
- `Prototypes/Link.me`

Tambien hay reportes consolidados en:

- `MeMo1-Ejercicio1/docs/reports/`

## Notas

- La carpeta `app/build/` contiene artefactos y reportes generados automaticamente por Gradle.
- El proyecto esta orientado al aprendizaje y validacion de reglas de negocio bancarias con enfoque de pruebas.
