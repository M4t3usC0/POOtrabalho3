package com.example.exceptions.produto;

/** A classe RemoveProdutoException é responsável por exibir uma mensagem de erro caso o tipo de produto seja inválido. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class TipoProdutoException extends IllegalArgumentException {

    /**
     * Construtor da classe TipoProdutoException
     * @param message Mensagem de erro
     */
    public TipoProdutoException(String message) {
        super(message);
    }
}
