package com.example.exceptions.notafiscal;

/**
 * A classe RemoveNotaFiscalException é responsável por exibir uma mensagem de erro caso não seja possível adicionar um produto. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class RemoveNotaFiscalException extends IllegalArgumentException {

    /**
     * Construtor da classe RemoveNotaFiscalException
     * @param message Mensagem de erro
     */
    public RemoveNotaFiscalException(String message) {
        super(message);
    }
}