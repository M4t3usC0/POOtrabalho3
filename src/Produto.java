package src;

import Exceptions.AddProdutoException;

public class Produto implements IProdutos{
    
    private int codigo;
    private String nome;
    private double preco;
    private double quantidade;
    
    public Produto(int codigo, String nome, double preco, double quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public double getQuantidade() {
        return quantidade;
    }   
    
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return "Produto:" + "codigo:" + codigo + ", nome:" + nome + ", preco:R$" + preco + ", quantidade:" + quantidade;
    }
    
    @Override
    public void addProduto(Produto p) throws Exception {
        
        throw new AddProdutoException("Não foi possível adicionar o produto."); 
    }
    
    @Override
    public void removeProduto(int codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public Produto getProduto(int codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void updateQuantidade(int codigo, double nova) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void updatePreco(int codigo, double novo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void addQuantidade(int codigo, double quantidade) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void subQuantidade(int codigo, double quantidade) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
