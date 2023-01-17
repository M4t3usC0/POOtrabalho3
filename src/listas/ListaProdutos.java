package listas;

import java.util.ArrayList;

import baseclasse.Produto;

import exceptions.produto.*;


public class ListaProdutos implements IProdutos {
    
    /**
     * 
     */
    private ArrayList<Produto> produtos;

    /**
     * 
     */
    public ListaProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }


    @Override
    public void addProduto(Produto p) throws Exception {
        if (p != null){
            produtos.add(p);
        }
        throw new AddProdutoException("Não foi possível adicionar o produto.");
    }
    
    @Override
    public void removeProduto(int codigo) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                produtos.remove(p);
                return;
            }
        }
        throw new RemoveProdutoException("Não foi possível remover o produto."); 
    }
    
    @Override
    public Produto getProduto(int codigo) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                return p;
            }
        }
        throw new ProdutoNotFoundException("Produto não encontrado."); 
    }
    
    @Override
    public void updateQuantidade(int codigo, double nova) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                p.setQuantidade(nova);
                return;
            }
        }
        throw new QuantidadeNotSupportedException("Quantidade não suportada."); 
    }       
    
    @Override
    public void updatePreco(int codigo, double novo) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                p.setPreco(novo);
                return;
            }
        }
        throw new PrecoNotSupportedException("Preço não suportado."); 
    }
    
    @Override
    public void addQuantidade(int codigo, double quantidade) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                p.setQuantidade(p.getQuantidade() + quantidade);
                return;
            }
        }
        throw new AddQuantidadeNotSupportedException("Não foi possível adicionar a quantidade.");
        
        

        //LANÇAR EXCEPTION

    }

    @Override
    public void subQuantidade(int codigo, double quantidade) throws Exception {
        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                if (p.getQuantidade() - quantidade >= 0){
                    p.setQuantidade(p.getQuantidade() - quantidade);
                    return;
                }
            }
        }
        throw new SubQuantidadeNotSupportedException("Não foi possível subtrair a quantidade."); 
    }

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
