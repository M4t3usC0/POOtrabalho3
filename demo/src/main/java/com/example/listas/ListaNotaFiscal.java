package com.example.listas;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.baseclasse.Item;
import com.example.baseclasse.NotaFiscal;
import com.example.exceptions.notafiscal.AddNotaFiscalException;
import com.example.exceptions.notafiscal.NotaFiscalNotFoundException;
import com.example.exceptions.notafiscal.RemoveNotaFiscalException;

/**
* Classe responsável por controlar a lista de notas fiscais<br>
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class ListaNotaFiscal implements INotasFiscais {

    /**
     * Lista de notas fiscais
     */
    private ArrayList<NotaFiscal> listaNotaFiscal;

    /**
    * Construtor default da classe ListaNotaFiscal<br>
    * Inicializa a lista de itens
    */
    public ListaNotaFiscal() {
        listaNotaFiscal = new ArrayList<NotaFiscal>();
    }

    /**
    * Método responsável por adicionar uma nota fiscal na lista de notas fiscais
    * @param nf Nota fiscal a ser adicionada
    */
    @Override
    public void addNotaFiscal(NotaFiscal nf) {
        if(nf != null) {
            listaNotaFiscal.add(nf);
            return;
        }
        throw new AddNotaFiscalException("Não foi possível adicionar a nota fiscal.");
    }

    /**
    * Método responsável por remover uma nota fiscal da lista de notas fiscais
    * @param codigo Código da nota fiscal a ser removida
    */
    @Override
    public void removeNotaFiscal(int codigo) {
        for (NotaFiscal notaFiscal : listaNotaFiscal) {
            if (notaFiscal.getCodigo() == codigo) {
                listaNotaFiscal.remove(notaFiscal);
                return;
            }
        }
        throw new RemoveNotaFiscalException("Não foi possível encontrar nota fiscal com o código: " + codigo + ".");
    }

    /**
    * Método responsável por retornar uma nota fiscal da lista de notas fiscais
    * @param codigo Código da nota fiscal a ser retornada
    * @return Nota fiscal com o código informado
    */
    @Override
    public NotaFiscal getNotaFiscal(int codigo) throws Exception {
        for (NotaFiscal notaFiscal : listaNotaFiscal){
            if (notaFiscal.getCodigo() == codigo){
                return notaFiscal;
            }
        }
        throw new NotaFiscalNotFoundException("Código de nota fiscal não existente.");
    }

    /**
    * Método responsável por retornar o total de uma nota fiscal
    * @param codigo Código da nota fiscal a ser retornada
    * @return Total da nota fiscal com o código informado
    */
    @Override
    public double getTotal(int codigo) throws Exception {
        for (NotaFiscal notaFiscal : listaNotaFiscal){
            if (notaFiscal.getCodigo() == codigo){
                return notaFiscal.getTotal();
            }
        }
        throw new NotaFiscalNotFoundException("Nota fiscal não encontrada.");
    }

    /**
    * Método responsável por retornar o total de uma nota fiscal
    * @param codigo Código da nota fiscal a ser retornada
    * @return Total da nota fiscal com o código informado
    */
    @Override
    public void addItem(int codigo, Item item) throws Exception {
        for (NotaFiscal notaFiscal : listaNotaFiscal){
            if (notaFiscal.getCodigo() == codigo){
                notaFiscal.add(item);
                return;
            }
        }
        throw new NotaFiscalNotFoundException("Nota fiscal não encontrado.");
    }

    /**
    * Método responsável por remover um item de uma nota fiscal
    * @param codigo Código da nota fiscal a ser removida
    * @return nova lista de itens da nota fiscal
    */
    @Override
    public void removeItem(int codigo, Item item) throws Exception {
        for (NotaFiscal nf : listaNotaFiscal){
			if (nf.getCodigo() == codigo){
				nf.remove(item);
				return;
			}
		}
		throw new RemoveNotaFiscalException("Não foi possível remover a nota fiscal.");
    }

    /**
    * Método responsável por retornar a lista de notas fiscais
    * @return Lista de notas fiscais
    */
    public ArrayList<NotaFiscal> getListaNotaFiscal() {
        return listaNotaFiscal;
    }

    /**
    * Método responsável por retornar as notas fiscais de um dia específico
    * @param calendar Data a ser pesquisada
    * @return Lista de notas fiscais
    */
    public ArrayList<NotaFiscal> getNotasFiscaisPorData(Calendar calendar) throws Exception {
        ArrayList<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();

        String data = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);

        for(NotaFiscal notaFiscal : listaNotaFiscal) {
            if(notaFiscal.getData().equals(calendar)) {
                notasFiscais.add(notaFiscal);
            }
        }
        if(notasFiscais.size() > 0) {
            return notasFiscais;
        }
        throw new Exception("Não foi possível encontrar notas fiscais com a data: " + data + ".");
    }

}
