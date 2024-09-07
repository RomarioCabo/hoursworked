package com.br;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculoHorasNoturnas {

    public static long calcularHorasNoturnas(LocalTime entrada, LocalTime saida) {
        // Definindo os horários de início e fim do período noturno
        LocalTime inicioNoturno = LocalTime.of(22, 0);
        LocalTime fimNoturno = LocalTime.of(5, 0);

        // Convertendo os horários de entrada e saída para LocalDateTime
        LocalDateTime entradaDateTime = LocalDateTime.now().with(entrada);
        LocalDateTime saidaDateTime = LocalDateTime.now().with(saida);

        // Caso a saída seja após a meia-noite (considerar o próximo dia)
        if (saida.isBefore(entrada)) {
            saidaDateTime = saidaDateTime.plusDays(1);
        }

        long minutosNoturnos = 0;

        // Se a entrada é entre 22:00 e 05:00, calcular tempo dentro do período
        if (entrada.isAfter(inicioNoturno) || entrada.equals(inicioNoturno)) {
            // Se a entrada for entre 22:00 e 00:00, calculamos até a saída
            if (saida.isBefore(fimNoturno)) {
                minutosNoturnos = Duration.between(entradaDateTime, saidaDateTime).toMinutes();
            } else {
                // Se a saída for após as 00:00, calculamos até o final do período noturno
                minutosNoturnos = Duration.between(entradaDateTime, LocalDateTime.now().with(fimNoturno).plusDays(1)).toMinutes();
            }
        }

        // Se a entrada é antes das 05:00 e a saída é após as 22:00, calculamos as horas noturnas
        if (entrada.isBefore(fimNoturno)) {
            LocalDateTime fimNoturnoDateTime = LocalDateTime.now().with(fimNoturno).plusDays(1);
            minutosNoturnos += Duration.between(entradaDateTime, fimNoturnoDateTime).toMinutes();
        }

        // Se a saída for depois das 22:00, calcular minutos entre 22:00 e a saída
        if (saida.isAfter(inicioNoturno)) {
            LocalDateTime inicioNoturnoDateTime = LocalDateTime.now().with(inicioNoturno);
            minutosNoturnos += Duration.between(inicioNoturnoDateTime, saidaDateTime).toMinutes();
        }

        return minutosNoturnos;
    }
}