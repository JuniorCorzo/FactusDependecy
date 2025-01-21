# Factus Dependency
Este proyecto consiste en una dependencia desarrollada en Java
para gestionar la facturación electrónica en Colombia.
La dependencia utiliza Factus, una API desarrollada por
**Haltec**, que se encarga de la creación y validación
de las facturas ante la DIAN (Dirección de Impuestos y
Aduanas Nacionales).

## Diseño de la Dependencia
![Microservices Managing Instruments.svg](docs/Diseño%20de%20Factus%20Dependency.svg)

El objetivo de las tareas es permitir la ejecución de
ciertas operaciones en segundo plano. Sin embargo, esto
no impide que los servicios puedan ser utilizados
directamente cuando sea necesario. 
Además, se implementó un contexto con la finalidad de evitar
llamados recurrentes a la API para datos que no presentan
cambios frecuentes. Esto mejora el rendimiento y reduce la
dependencia de solicitudes innecesarias.