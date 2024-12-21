# DSINT: Desarrollo de Sistemas Inteligentes

Este proyecto está diseñado para desarrollar un prototipo de sistema inteligente (SI) capaz de responder preguntas sobre mitología griega. Las preguntas giran en torno a la posibilidad de realizar hazañas mitológicas bajo ciertas condiciones iniciales.

## Objetivo

Desarrollar un SI que:
- Integre ontologías para representar el dominio de los mitos.
- Use motores de reglas para evaluar secuencias de eventos.
- Responda preguntas complejas sobre hazañas y decisiones en los mitos.

## Fases del Proyecto

1. **Fase 1: Ontología del dominio**
   - Diseñar y desarrollar una ontología usando Protégé.
   - Incluir conceptos clave como héroes, dioses, criaturas y objetos.

2. **Fase 2: Sistema Inteligente Básico**
   - Crear un SI que utilice reglas para responder preguntas relacionadas con el mito de Andrómeda y Perseo.

3. **Fase 3: Sistema Inteligente Completo**
   - Extender el SI para incluir nuevos mitos (como el laberinto de Creta) y generar historias alternativas.

## Entregables

1. **Entrega Parcial (opcional):**
   - Incluye resultados preliminares de las fases 1 y 2.

2. **Entrega Final (obligatoria):**
   - ZIP con los proyectos de cada fase y sus informes asociados.

## Requisitos Técnicos

- Herramientas:
  - Protégé para diseño de ontologías.
  - Eclipse y Drools para desarrollo del sistema.
- Formato del ejecutable:
  - Acepta directorios de escenarios y genera soluciones en archivos `.txt`.

## Evaluación

- **Fase 1**: Ontología (2 puntos)
- **Fase 2**: SI Básico (3 puntos)
- **Fase 3**: SI Completo (4 puntos)
- **Presentación Oral**: (1 punto, obligatoria)

## Uso

1. Diseñar la ontología en Protégé y exportar los archivos necesarios.
2. Implementar las reglas en Java usando Drools.
3. Ejecutar los escenarios desde la línea de comandos:
   ```bash
   java -jar sistema.jar escenarios soluciones
