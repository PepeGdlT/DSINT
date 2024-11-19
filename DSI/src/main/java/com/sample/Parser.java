package com.sample;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import main.java.dsin.Objeto.*;
import main.java.dsin.Personaje.*;
import main.java.dsin.Accion.*;
import main.java.dsin.Comprobacion.*;
import main.java.dsin.Estado.*;

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
		generateFactsFromQuestion();
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

	
	private boolean checkLiberacion(Personaje libertador, Personaje liberado) {
	    QueryResults results = kSession.getQueryResults("getLiberar", libertador, liberado);
	    if( results.size() > 0) {
	    	kSession.halt();
	    	return true;
	    }
	    return false;
	}
	private boolean checkPoseer(Personaje p, Objeto objeto) {
	    QueryResults results = kSession.getQueryResults("getPoseer", p, objeto);
	    if( results.size() > 0) {
	    	kSession.halt();
	    	return true;
	    }
	    return false;
	}
	
	private void generateFactsFromQuestion() {
		parseQuestion(pregunta);
	}
	private void generateFactsFromConditions() {
		
		for (String condition : condiciones) {
			parseCondition(condition);
		}
		//System.out.println("Hechos generados: " + generatedFacts); // Debug
	}

	private void parseFile(String filePath) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isPregunta = true;
			while ((line = br.readLine()) != null) {
				if (isPregunta) {
					pregunta = line.trim();
					isPregunta = false;
				} else if(line.equals("Condiciones:")) {
					continue;
				} else {
					condiciones.add(line.trim());
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
		} else if(condition.contains("apresa")) {
			String[] parts = condition.split("apresa");
			Personaje sujeto = getPersonajeByName(parts[0].trim());
			Personaje afectado = getPersonajeByName(parts[1].trim());
			generatedFacts.add(new Apresar(sujeto, afectado));
		} else if(condition.contains("libera")) {
			String[] parts = condition.split("libera");
			Personaje sujeto = getPersonajeByName(parts[0].trim());
			Personaje afectado = getPersonajeByName(parts[1].trim());
			generatedFacts.add(new Liberar(sujeto, afectado));
		} 
	}


	private void parseQuestion(String pregunta) {
		if(pregunta.contains("liberar a")) {
			String[] parts = pregunta.split("liberar a");
			Personaje heroe =  new Heroe(parts[0].trim().replace("¿Puede ", ""));
			Personaje afectado = getPersonajeByName(parts[1].trim().replace("?", ""));
			
			kSession.insert(heroe);			
			kSession.insert(new Objetivo(heroe, afectado));
			
			

		} else if (pregunta.contains("tener")) {
			String[] parts = pregunta.split("tener");
			Personaje heroe = new Heroe(parts[0].trim().replace("¿Puede ", ""));
			Objeto objeto = getObjetoByName(parts[1].trim().replace("?", ""));
			
			  
			kSession.insert(heroe);
		    kSession.insert(new Objetivo(heroe, objeto));
			

			
			
		} else if (pregunta.contains("¿Quien salva a Andromeda?")) {
			pregunta = pregunta.replace("Quien salva a Andromeda? ", "");
			Personaje andromeda = getPersonajeByName("Andromeda");
			Objeto cabezaMedusa = getObjetoByName("Cabeza de Medusa");
			Objeto cabezaMinotauro = getObjetoByName("Cabeza de Minotauro");
			Objeto cabezaCeto = getObjetoByName("Cabeza de Ceto");
			String[] parts = pregunta.split(",");
			Heroe h1 = new Heroe(parts[0].trim());
			Heroe h2 = new Heroe(parts[1].trim());
			Heroe h3 = new Heroe(parts[2].trim());

			kSession.insert(h1);
			kSession.insert(h2);
			kSession.insert(h3);
			
			kSession.insert(new Objetivo(h1, andromeda));
			kSession.insert(new Objetivo(h1, cabezaMedusa));
			kSession.insert(new Objetivo(h1, cabezaMinotauro));
			kSession.insert(new Objetivo(h1, cabezaCeto));
			
			
			kSession.insert(new Objetivo(h2, andromeda));
			kSession.insert(new Objetivo(h2, cabezaMedusa));
			kSession.insert(new Objetivo(h2, cabezaMinotauro));
			kSession.insert(new Objetivo(h2, cabezaCeto));
			
			
			
			kSession.insert(new Objetivo(h3, andromeda));
			kSession.insert(new Objetivo(h3, cabezaMedusa));
			kSession.insert(new Objetivo(h3, cabezaMinotauro));
			kSession.insert(new Objetivo(h3, cabezaCeto));
			

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


