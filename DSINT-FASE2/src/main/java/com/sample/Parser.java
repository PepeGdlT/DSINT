package com.sample;

import main.java.dsin.Accion.*;
import main.java.dsin.Objeto.*;
import main.java.dsin.Personaje.*;
import main.java.dsin.Estado.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class Parser {
    private KieSession kSession;
    private String pregunta;
    private List<String> condiciones;
    private List<Object> generatedFacts;

    public Parser(String filePath, KieSession kSession) throws IOException {
        this.kSession = kSession;
        condiciones = new ArrayList<>();
        generatedFacts = new ArrayList<>();
        parseFile(filePath);
        generateFactsFromConditions();
    }

    private Personaje getPersonajeByName(String nombre) {
        QueryResults results = kSession.getQueryResults("getPersonajeByName", nombre);
        if (results.size() > 0) {
            return (Personaje) results.iterator().next().get("$p");
        }
        return null; 
    }

    private Objeto getObjetoByName(String nombre) {
        QueryResults results = kSession.getQueryResults("getObjetoByName", nombre);
        if (results.size() > 0) {
            return (Objeto) results.iterator().next().get("$o");
        }
        return null;
    }
    private void generateFactsFromConditions() {
        for (String condition : condiciones) {
            parseCondition(condition);
        }
        System.out.println("Hechos generados: " + generatedFacts); // Debug
    }
    
    private void parseFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isPregunta = true;
            while ((line = br.readLine()) != null) {
                if (isPregunta) {
                    pregunta = line.trim();
                    System.out.println("Pregunta leída: " + pregunta); // Debug
                    isPregunta = false;
                } else if(line.equals("Condiciones:")) {
                    continue;
                } else {
                    condiciones.add(line.trim());
                    System.out.println("Condición leída: " + line.trim()); // Debug
                }
            }
        }
    }
    private void parseCondition(String condition) {
        if (condition.contains("tiene el favor de")) {
            String[] parts = condition.split("tiene el favor de");
            Personaje favorecido = getPersonajeByName(parts[0].trim());
            Personaje sujeto = getPersonajeByName(parts[1].trim());
            generatedFacts.add(new FavoreceA(sujeto, favorecido));
        } else if (condition.contains("tiene el enojo de")) {
            String[] parts = condition.split("tiene el enojo de");
            Personaje afectado = getPersonajeByName(parts[0].trim());
            Personaje enojado = getPersonajeByName(parts[1].trim());
            generatedFacts.add(new TieneEnojoCon(enojado, afectado));
        } else if (condition.contains("no tiene") && !condition.contains("favor") && !condition.contains("enojo")) {
            String[] parts = condition.split("no tiene");
            Personaje sujeto = getPersonajeByName(parts[0].trim());
            Objeto objeto = getObjetoByName(parts[1].trim());
            generatedFacts.add(new Quitar(sujeto, objeto));
        } else if (condition.contains("tiene") && !condition.contains("favor") && !condition.contains("enojo")) {
            String[] parts = condition.split("tiene");
            Personaje sujeto = getPersonajeByName(parts[0].trim());
            Objeto objeto = getObjetoByName(parts[1].trim());
            generatedFacts.add(new Poseer(sujeto, objeto));
        }
    }
    
    
    public String getPregunta() {
        return pregunta;
    }

    public List<String> getCondiciones() {
        return condiciones;
    }

    public List<Object> getGeneratedFacts() {
        return generatedFacts;
    }
}


