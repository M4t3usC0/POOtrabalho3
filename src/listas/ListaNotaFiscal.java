package listas;

import baseclasse.Item;
import baseclasse.NotaFiscal;
import exceptions.notafiscal.*;
import java.util.ArrayList;

public class ListaNotaFiscal implements INotasFiscais {

    private ArrayList<NotaFiscal> listaNotaFiscal;

    public ListaNotaFiscal(ArrayList<NotaFiscal> listaNotaFiscal) {
        this.listaNotaFiscal = listaNotaFiscal;
    }

    @Override
    public void addNotaFiscal(NotaFiscal nf) {
        if(nf != null) {
            listaNotaFiscal.add(nf);
        }
    }

    @Override
    public void removeNotaFiscal(int codigo) {
        for (NotaFiscal notaFiscal : listaNotaFiscal) {
            if (notaFiscal.getCodigo() == codigo) {
                listaNotaFiscal.remove(notaFiscal);
            }
        }
        

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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addItem(int codigo, Item item) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeItem(int codigo, Item item) throws Exception {
        for (NotaFiscal nf : listaNotaFiscal){
			if (nf.getCodigo() == codigo){
				listaNotaFiscal.remove(nf);
				return;
			}
		}
		throw new RemoveNotaFiscalException("Não foi possível remover a nota fiscal.");
		
    }

}
