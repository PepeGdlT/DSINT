package com.sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import rules.Constantes;

public class DroolsTest {

    public static void main(String[] args) {
    	
        try {
            // Inicializaci�n de la base de conocimiento
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules-dsin");

            
            // Crear una lista para almacenar mensajes
            List<String> mensajes = new ArrayList<>();
            kSession.setGlobal("mensajes", mensajes); // Configurar la variable global


            // HECHOS INICIALES
            kSession.getAgenda().getAgendaGroup("iniciales").setFocus();
            kSession.fireAllRules(); // Dispara las reglas iniciales

        

            // Procesar la pregunta y condiciones de cada archivo de entrada
            for (int i = 0; i < args.length; i++) {
            	System.out.println("Fichero " + args[i]);
                Parser processor = new Parser(args[i], kSession);
                List<Object> generatedFacts = processor.getGeneratedFacts(); // Obtener los hechos generados desde el archivo

                // Insertar hechos en Drools
                agregarHechos(kSession, generatedFacts);
                
                kSession.getFactHandles().stream().forEach(n -> System.out.println(n.toString()));
             
                
                
                
                // Ejecutar reglas despu�s de insertar hechos
                kSession.fireAllRules();
                System.out.println("Mensajes collected: " + String.join(", \n", mensajes));

                // Crear respuesta a partir de los mensajes
                String respuesta = String.join("\n", mensajes);

                // Guardar respuesta en el archivo correspondiente
                String nombreArchivoSalida = "src/main/resources/RespuestaGen/Fase2.Perseo" + (i + 1) + ".Respuesta.txt";
                guardarRespuesta(respuesta, nombreArchivoSalida);
                
                // Limpiar mensajes para el siguiente archivo de entrada
                mensajes.clear();
                // Limpiar los hechos generados si es necesario
                generatedFacts.clear();
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
