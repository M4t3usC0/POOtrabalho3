package exceptions.notafiscal;

/**
 * A classe AddProdutoException é responsável por exibir uma mensagem de erro caso não seja possível adicionar um produto. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class NotaFiscalNotFoundException extends IllegalArgumentException {

    /**
     * Construtor da classe AddProdutoException
     * @param message Mensagem de erro
     */
    public NotaFiscalNotFoundException(String message) {
        super(message);
    }
}

