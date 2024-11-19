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
            System.err.println("La carpeta de entrada no es valida: " + carpetaEntrada);
            return;
        }

        if (!carpetaSalidaDir.exists()) {
            carpetaSalidaDir.mkdirs(); // Crear la carpeta de salida si no existe
        }

        try {
            // Inicializacion de la base de conocimiento
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

                // Crear una nueva sesion para cada archivo de entrada
                KieSession kSession = kContainer.newKieSession("ksession-rules-dsin");

                // Crear una lista para almacenar mensajes
                List<String> mensajes = new ArrayList<>();
      

                kSession.setGlobal("mensajes", mensajes); // Configurar la variable global

                
                
                
                // Disparar reglas iniciales, que son independientes de las condiciones de entrada
                kSession.getAgenda().getAgendaGroup("MITO1").setFocus();
                kSession.fireAllRules(); // Disparar las reglas iniciales

                kSession.getAgenda().getAgendaGroup("MITO2").setFocus();
                kSession.fireAllRules(); // Disparar las reglas iniciales

                // Procesar el archivo de entrada
                Parser processor = new Parser(archivoEntrada.getAbsolutePath(), kSession);
                List<Object> generatedFacts = processor.getGeneratedFacts(); // Obtener los hechos generados desde el archivo
                
                //System.out.println(generatedFacts);
                // Insertar hechos de la entrada
                agregarHechos(kSession, generatedFacts);
                
                
                //Se disparan las reglas que requieren de los datos de la entrada
                kSession.getAgenda().getAgendaGroup("ESTADOS-INICIALES").setFocus();
                kSession.fireAllRules();
                
                // Ejecutar reglas despues de insertar hechos
                kSession.fireAllRules();
                
                //DEBUG
                /*
                if (archivoEntrada.getName().equals("Fase3.Escenario5.txt")) {
                	
                	for (Object fact : kSession.getObjects()) {
                	    System.out.println(fact);
                	}
                	
                	//System.out.println(mensajes);
                }
                */
				

                String respuesta = String.join("\n", mensajes);
                String inicial = "";



                if (respuesta.contains("STOP")) {
                	inicial = "Si, ha alcanzado el objetivo debido a: \n";
                    respuesta = respuesta.substring(0, respuesta.indexOf("STOP"));
                } else {
                	inicial = "No, no ha alcanzado el objetivo debido a: \n";
                }
                respuesta = inicial + respuesta;

                // Guardar la respuesta en el archivo correspondiente
                String nombreArchivoSalida = new File(carpetaSalidaDir, archivoEntrada.getName().replace(".txt", ".Respuesta.txt")).getAbsolutePath();
                guardarRespuesta(respuesta, nombreArchivoSalida);


                // Cerrar la sesion actual para liberar recursos
                kSession.dispose();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // Metodo para agregar hechos a la sesion de Drools
    public static <T> void agregarHechos(KieSession kSession, List<T> hechosGenerados) {
        for (T hecho : hechosGenerados) {
            kSession.insert(hecho);
        }
    }

    // Metodo para guardar la respuesta en un archivo
    public static void guardarRespuesta(String respuesta, String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(respuesta);
        }
    }
}
