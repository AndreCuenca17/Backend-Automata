package com.automata.automata.controller;

import com.automata.automata.model.AutomataModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AutomataController {

    @GetMapping("/verificar/{cadena}")
    public Map<String, String> verificarConAutomata(@PathVariable String cadena) {
        // Definir el autómata
        Set<String> estados = Set.of("q0", "q1", "q2", "q3", "q4");
        Set<String> alfabeto = Set.of("0", "1");

        Map<String, Map<String, String>> transiciones = new HashMap<>();

        transiciones.put("q0", Map.of("0", "q1", "1", "q3"));
        transiciones.put("q1", Map.of("0", "q1", "1", "q2"));
        transiciones.put("q2", Map.of("0", "q1", "1", "q2"));
        transiciones.put("q3", Map.of("0", "q4", "1", "q3"));
        transiciones.put("q4", Map.of("0", "q4", "1", "q3"));

        String estadoInicial = "q0";
        Set<String> estadosFinales = Set.of("q2", "q4");

        AutomataModel automata = new AutomataModel(estados, alfabeto, transiciones, estadoInicial, estadosFinales);

        String cadenaEntrada = cadena;
        Map<String, Object> resultado = automata.procesar(cadenaEntrada);
        Map<String, String> respuesta = new HashMap<>();
        if (Boolean.TRUE.equals(resultado.get("esAceptada"))) {
            respuesta.put("aceptada", "true");
            respuesta.put("cadena", cadenaEntrada);
            respuesta.put("informacion", resultado.get("registro").toString());
            System.out.println("El binario " + cadenaEntrada + " es aceptado");
        } else {
            respuesta.put("aceptada", "false");
            respuesta.put("cadena", cadenaEntrada);
            System.out.println("El binario " + cadenaEntrada + " no es aceptado");
        }
        return respuesta;
    }
}
