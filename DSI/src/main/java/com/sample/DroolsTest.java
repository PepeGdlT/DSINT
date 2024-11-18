package com.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsTest {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Se requieren dos argumentos: <carpeta de entrada> <carpeta de salida>");
            return;
        }

        String carpetaEntrada = args[0];
        String carpetaSalida = args[1];

        // Verificar si las carpetas existen
        File carpetaEntradaDir = new File(carpetaEntrada);
        File carpetaSalidaDir = new File(carpetaSalida);

        if (!carpetaEntradaDir.isDirectory()) {
            System.err.println("La carpeta de entrada no es v�lida: " + carpetaEntrada);
            return;
        }

        if (!carpetaSalidaDir.exists()) {
            carpetaSalidaDir.mkdirs(); // Crear la carpeta de salida si no existe
        }

        try {
            // Inicializaci�n de la base de conocimiento
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();

            
            
            // Obtener todos los archivos de la carpeta de entrada
            File[] archivosEntrada = carpetaEntradaDir.listFiles((dir, name) -> name.endsWith(".txt"));
            if (archivosEntrada == null || archivosEntrada.length == 0) {
                System.err.println("No se encontraron archivos .txt en la carpeta de entrada.");
                return;
            }

            // Procesar cada archivo de entrada
            for (File archivoEntrada : archivosEntrada) {
                System.out.println("Procesando archivo: " + archivoEntrada.getName());

                // Crear una nueva sesi�n para cada archivo de entrada
                KieSession kSession = kContainer.newKieSession("ksession-rules-dsin");

                // Crear una lista para almacenar mensajes
                List<String> mensajes = new ArrayList<>();
                kSession.setGlobal("mensajes", mensajes); // Configurar la variable global
                
                
                
                
                // Disparar reglas iniciales
                kSession.getAgenda().getAgendaGroup("MITO1").setFocus();
                kSession.fireAllRules(); // Disparar las reglas iniciales

                kSession.getAgenda().getAgendaGroup("MITO2").setFocus();
                kSession.fireAllRules(); // Disparar las reglas iniciales

                // Procesar el archivo de entrada
                Parser processor = new Parser(archivoEntrada.getAbsolutePath(), kSession);
                List<Object> generatedFacts = processor.getGeneratedFacts(); // Obtener los hechos generados desde el archivo
                
                System.out.println(generatedFacts);
                // Insertar hechos en Drools
                agregarHechos(kSession, generatedFacts);

                // Ejecutar reglas despu�s de insertar hechos
                kSession.fireAllRules();
               

                // Crear respuesta a partir de los mensajes
                String respuesta = String.join("\n", mensajes);

                // Guardar respuesta en el archivo correspondiente
                String nombreArchivoSalida = new File(carpetaSalidaDir, archivoEntrada.getName().replace(".txt", ".Respuesta.txt")).getAbsolutePath();
                //System.out.println("Guardando en: " + nombreArchivoSalida);
                guardarRespuesta(respuesta, nombreArchivoSalida);

                // Cerrar la sesi�n actual para liberar recursos
                kSession.dispose();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // M�todo para agregar hechos a la sesi�n de Drools
    public static <T> void agregarHechos(KieSession kSession, List<T> hechosGenerados) {
        for (T hecho : hechosGenerados) {
            kSession.insert(hecho);
        }
    }

    // M�todo para guardar la respuesta en un archivo
    public static void guardarRespuesta(String respuesta, String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(respuesta);
        }
    }
}
