package com.br;

import java.time.LocalTime;

import static com.br.CalculoHorasNoturnas.calcularHorasNoturnas;

public class Main {
    public static void main(String[] args) {
        System.out.println("Horas noturnas: " + calcularHorasNoturnas(LocalTime.of(7, 0), LocalTime.of(21, 0)) + " minutos");
        System.out.println("Horas noturnas: " + calcularHorasNoturnas(LocalTime.of(7, 0), LocalTime.of(22, 15)) + " minutos");
        System.out.println("Horas noturnas: " + calcularHorasNoturnas(LocalTime.of(7, 0), LocalTime.of(22, 30)) + " minutos");
        System.out.println("Horas noturnas: " + calcularHorasNoturnas(LocalTime.of(22, 0), LocalTime.of(0, 0)) + " minutos");
        System.out.println("Horas noturnas: " + calcularHorasNoturnas(LocalTime.of(22, 0), LocalTime.of(5, 0)) + " minutos");
    }
}