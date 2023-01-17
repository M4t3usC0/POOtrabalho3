package baseclasse;

import java.util.ArrayList;
import java.util.Calendar;

public class NotaFiscal {

    private int codigo;
    private Calendar data;
    private ArrayList<Item> itens;
    private static int codigoUnico = 10000;

    public NotaFiscal(int codigo, Calendar data, ArrayList<Item> itens) {
        this.codigo = codigoUnico;
        this.data = data;
        this.itens = itens;
        codigoUnico++;
    }
    
    public int getCodigo() { return codigo; }
    
    public Calendar getData() { return data; }

    public ArrayList<Item> getItens() { return itens; }

    public void setData(Calendar data) { this.data = data; }

    public void setItens(ArrayList<Item> itens) { this.itens = itens; }

}