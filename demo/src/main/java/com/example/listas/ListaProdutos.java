package com.example.listas;

import java.util.ArrayList;

import com.example.baseclasse.Produto;
import com.example.exceptions.produto.AddProdutoException;
import com.example.exceptions.produto.AddQuantidadeNotSupportedException;
import com.example.exceptions.produto.PrecoNotSupportedException;
import com.example.exceptions.produto.ProdutoNotFoundException;
import com.example.exceptions.produto.QuantidadeNotSupportedException;
import com.example.exceptions.produto.RemoveProdutoException;
import com.example.exceptions.produto.SubQuantidadeNotSupportedException;
import com.example.baseclasse.ProdutoUnidade;

/**
* Classe responsável por controlar a lista de produtos.
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class ListaProdutos implements IProdutos {
    
    /**
     * Lista de produtos
     */
    private ArrayList<Produto> produtos;

    /**
    * Construtor default da classe ListaProdutosl<br>
    * Inicializa a lista de produtos
    */
    public ListaProdutos() {
        this.produtos = new ArrayList<Produto>();
    }

    /**
    * Construtor da classe ListaProdutos<br>
    * Inicializa a lista de produtos com a lista passada por parâmetro
    * @param produtos Lista de produtos
    */
    public ListaProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
    * Método responsável por adicionar um produto na lista de produtos
    * @param p produto a ser adicionado
    */
    @Override
    public void addProduto(Produto p) throws Exception {
        if (p != null){
            produtos.add(p);
            return;
        }
        throw new AddProdutoException("Não foi possível adicionar o produto.");
    }
    
    /**
    * Método responsável por remover um produto da lista de produtos
    * @param codigo código do produto a ser removido
    */
    @Override
    public void removeProduto(int codigo) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                produtos.remove(p);
                return;
            }
        }
        throw new RemoveProdutoException("Produto não encontrado."); 
    }
    
    /**
    * Método responsável por retornar um produto da lista de produtos
    * @param codigo código do produto a ser retornado
    * @return produto
    */
    @Override
    public Produto getProduto(int codigo) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                return p;
            }
        }
        throw new ProdutoNotFoundException("Produto não encontrado."); 
    }
    
    /**
    * Método responsável por atualizar a quantidade de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param nova nova quantidade do produto
    * @return lista de produtos
    */
    @Override
    public void updateQuantidade(int codigo, double nova) throws Exception {

        if(nova <= 0) {
            throw new QuantidadeNotSupportedException("Quantidade não suportada.");
        }

        for (Produto p : produtos){
            if (p.getCodigo() == codigo){

                if(p instanceof ProdutoUnidade) {
                    int quantidadeInt = (int) nova;

                    if(quantidadeInt != nova) {
                        throw new QuantidadeNotSupportedException("Quantidade não suportada.");
                    }
                }

                p.setQuantidade(nova);
                return;
            }
        }
        throw new QuantidadeNotSupportedException("Quantidade não suportada."); 
    }       
    
    /**
    * Método responsável por atualizar o preço de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param novo novo preço do produto
    * @return lista de produtos
    */
    @Override
    public void updatePreco(int codigo, double novo) throws Exception {

        if(novo < 0) {
            throw new PrecoNotSupportedException("Preço não suportado.");
        }
        
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                p.setPreco(novo);
                return;
            }
        }
        throw new PrecoNotSupportedException("Preço não suportado."); 
    }
    
    /**
    * Método responsável por adicionar a quantidade de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param quantidade quantidade a ser adicionada
    * @return lista de produtos
    */
    @Override
    public void addQuantidade(int codigo, double quantidade) throws Exception {

        if(quantidade <= 0) {
            throw new AddQuantidadeNotSupportedException("Não foi possível adicionar a quantidade.");
        }

        for (Produto p : produtos){
            if (p.getCodigo() == codigo){

                if(p instanceof ProdutoUnidade) {
                    int quantidadeInt = (int) quantidade;

                    if(quantidadeInt != quantidade) {
                        throw new AddQuantidadeNotSupportedException("Não foi possível adicionar a quantidade.");
                    }
                }

                p.setQuantidade(p.getQuantidade() + quantidade);
                return;
            }
        }
        throw new AddQuantidadeNotSupportedException("Não foi possível adicionar a quantidade.");
    }

    /**
    * Método responsável por subtrair a quantidade de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param quantidade quantidade a ser subtraída
    * @return lista de produtos
    */
    @Override
    public void subQuantidade(int codigo, double quantidade) throws Exception {

        if(quantidade <= 0) {
            throw new SubQuantidadeNotSupportedException("Não foi possível subtrair a quantidade.");
        }

        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                if (p.getQuantidade() - quantidade >= 0){

                    if(p instanceof ProdutoUnidade) {
                        int quantidadeInt = (int) quantidade;
                        
                        if(quantidadeInt != quantidade) {
                            throw new SubQuantidadeNotSupportedException("Não foi possível subtrair a quantidade.");
                        }
                    }

                    p.setQuantidade(p.getQuantidade() - quantidade);
                    return;
                }
            }
        }
        throw new SubQuantidadeNotSupportedException("Não foi possível subtrair a quantidade."); 
    }


    /**
    * Método responsável por substituir um produto da lista de produtos
    * @param produtoASerSubstituido produto a ser substituido
    * @param produtoSubstituto produto substituto
    * @return lista de produtos
    */
    public void substituirProduto(Produto produtoASerSubstituido, Produto produtoSubstituto) {
        int index = produtos.indexOf(produtoASerSubstituido);
        produtos.set(index, produtoSubstituto);
    }

    /**
    * Método responsável por verificar se a lista de produtos está vazia
    * @return lista de produtos
    */
    public boolean isEmpty() {
        return produtos.isEmpty();
    }

    /**
    * Método responsável por verificar o tamanho da lista de produtos
    * @return lista de produtos
    */
    public int size() {
        return produtos.size();
    }

    /** 
     * @return String que identifica as informações da lista de produtos
     */
    @Override
    public String toString() {
        String conteudo = "";
        if(produtos.size() != 0) {
            for (Produto p : produtos){
                conteudo += p.toString() + "\n";
            }
            return conteudo;
        }
        return "Lista vazia";
    }

}
