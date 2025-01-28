package com.automata.automata.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AutomataModel {

    private Set<String> estados;
    private Set<String> alfabeto;
    private Map<String, Map<String, String>> transiciones;
    private String estadoInicial;
    private Set<String> estadosFinales;

    public AutomataModel(Set<String> estados, Set<String> alfabeto, Map<String, Map<String, String>> transiciones, String estadoInicial, Set<String> estadosFinales) {
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.transiciones = transiciones;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
    }

    public Map<String, Object> procesar(String cadenaEntrada) {
        StringBuilder registro = new StringBuilder();
        String estadoActual = estadoInicial;

        registro.append("Estado Inicial: ").append(estadoActual).append("\n");

        for (char simbolo : cadenaEntrada.toCharArray()) {
            String simboloStr = String.valueOf(simbolo);
            if (!alfabeto.contains(simboloStr)) {
                throw new IllegalArgumentException("El símbolo " + simbolo + " no está en el alfabeto");
            }

            String siguienteEstado = transiciones.get(estadoActual).get(simboloStr);
            registro.append("Con el símbolo ").append(simbolo)
                    .append(" pasamos del estado ").append(estadoActual)
                    .append(" al estado ").append(siguienteEstado).append("\n");

            estadoActual = siguienteEstado;
        }

        registro.append("Estado Final: ").append(estadoActual).append("\n");

        boolean esAceptada = estadosFinales.contains(estadoActual);

        if (esAceptada) {
            registro.append("El binario es aceptado.\n");
        } else {
            registro.append("El binario no es aceptado.\n");
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("registro", registro.toString());
        resultado.put("esAceptada", esAceptada);

        return resultado;
    }

}
