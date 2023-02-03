package com.example.exceptions.geral;

/**
 * A classe CampoVazioException é responsável por exibir uma mensagem de erro caso não seja possível adicionar uma Nota Fiscal. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class CampoVazioException extends Exception {

    /**
     * Construtor da classe CampoVazioException
     * @param message Mensagem de erro
     */
    public CampoVazioException(String message) {
        super(message);
    }
}

