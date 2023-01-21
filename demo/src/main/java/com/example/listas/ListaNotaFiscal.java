package com.example.listas;

import java.util.ArrayList;

import com.example.baseclasse.Item;
import com.example.baseclasse.NotaFiscal;
import com.example.exceptions.notafiscal.AddNotaFiscalException;
import com.example.exceptions.notafiscal.NotaFiscalNotFoundException;
import com.example.exceptions.notafiscal.RemoveNotaFiscalException;

public class ListaNotaFiscal implements INotasFiscais {

    private ArrayList<NotaFiscal> listaNotaFiscal;

    public ListaNotaFiscal(ArrayList<NotaFiscal> listaNotaFiscal) {
        if(listaNotaFiscal == null) {
            throw new IllegalArgumentException("Lista de notas fiscais não pode ser nula.");
        }

        this.listaNotaFiscal = listaNotaFiscal;
    }

    @Override
    public void addNotaFiscal(NotaFiscal nf) {
        if(nf != null) {
            listaNotaFiscal.add(nf);
        }
        throw new AddNotaFiscalException("Não foi possível adicionar a nota fiscal.");
    }

    @Override
    public void removeNotaFiscal(int codigo) {
        for (NotaFiscal notaFiscal : listaNotaFiscal) {
            if (notaFiscal.getCodigo() == codigo) {
                listaNotaFiscal.remove(notaFiscal);
                return;
            }
        }
        throw new RemoveNotaFiscalException("Não foi possível remover a nota fiscal.");
    }

    @Override
    public NotaFiscal getNotaFiscal(int codigo) throws Exception {
        for (NotaFiscal notaFiscal : listaNotaFiscal){
            if (notaFiscal.getCodigo() == codigo){
                return notaFiscal;
            }
        }
        throw new NotaFiscalNotFoundException("Nota fiscal não encontrado.");
    }

    @Override
    public double getTotal(int codigo) throws Exception {
        for (NotaFiscal notaFiscal : listaNotaFiscal){
            if (notaFiscal.getCodigo() == codigo){
                return notaFiscal.getTotal();
            }
        }
        throw new NotaFiscalNotFoundException("Nota fiscal não encontrada.");
    }

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

}
