# Trabajo Práctico 1: Sistema de Gestión de Biblioteca "BiblioTech"
## Curso: Programación en Java - 4to Año Ingeniería en Informática

---

### Datos del Alumno

| Campo | Respuesta |
| :--- | :--- |
| **Nombre** | IAN |
| **Apellido** | OLMEDO |
| **Legajo** | 62199 |

# BiblioTech - Sistema de Gestión de Biblioteca

## Descripción

BiblioTech es un sistema de gestión de biblioteca desarrollado en Java como trabajo práctico para la materia Programación II.

Permite administrar libros, socios y el ciclo completo de préstamos, aplicando buenas prácticas de diseño y arquitectura.

---

## Funcionalidades

-  Registro de libros
-  Registro de socios (Estudiantes y Docentes)
-  Préstamo de libros con validaciones
-  Devolución con cálculo de retraso
-  Búsqueda de libros (por título, autor y categoría)
-  Historial de préstamos
-  Interfaz por consola (CLI)

---

##  Arquitectura

El proyecto sigue una arquitectura por capas:

model → Entidades del dominio
repository → Acceso a datos (in-memory)
service → Lógica de negocio
exception → Manejo de errores
Main → Interfaz de usuario (CLI)


---

## Principios aplicados

### SOLID

- **S (Single Responsibility):** Cada clase tiene una única responsabilidad
- **O (Open/Closed):** Se pueden agregar nuevos tipos de socios sin modificar código existente
- **L (Liskov):** Los tipos de socio son intercambiables
- **I (Interface Segregation):** Uso de interfaces específicas (`Repository`)
- **D (Dependency Inversion):** Los servicios dependen de interfaces, no de implementaciones

---

## Tecnologías utilizadas

- Java
- Programación orientada a objetos
- Streams (Java moderno)
- Optional
- Manejo de excepciones personalizadas

---

## Cómo ejecutar

1. Clonar el repositorio:
git clone git@github.com:IanOlmedo/2026.tp1.template.git

2. Abrir el proyecto en IntelliJ o IDE preferido
3. Ejecutar la clase Main.java


