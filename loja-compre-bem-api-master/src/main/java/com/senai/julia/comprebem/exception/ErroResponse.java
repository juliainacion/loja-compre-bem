package com.senai.julia.comprebem.exception;

import java.util.Map;

/**
 * Formato padrão de erro devolvido pela API.
 * "mensagem" é um texto pronto para ser mostrado ao usuário;
 * "erros" traz o detalhe por campo (só nos erros de validação).
 */
public record ErroResponse(
        int status,
        String mensagem,
        Map<String, String> erros
) {
}
