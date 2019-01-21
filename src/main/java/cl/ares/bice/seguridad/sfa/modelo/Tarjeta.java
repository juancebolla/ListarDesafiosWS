package cl.ares.bice.seguridad.sfa.modelo;

import java.util.Hashtable;

public class Tarjeta {
    Hashtable<String, String> tarjeta = new Hashtable<String, String>();
    String[] cols = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    String[] rows = new String[] {"1", "2", "3", "4", "5"};
    public Tarjeta(String[] valores) {
        for(int rowIdx = 0; rowIdx < rows.length; rowIdx++){
            for(int colIdx = 0; colIdx < cols.length; colIdx++){
                int valIdx = colIdx + (rowIdx * cols.length);
                String col = cols[colIdx];
                String row = rows[rowIdx];
                String val = valores[valIdx];

               setValue(row, col, val);
            }
        }
    }

    private void setValue(String row, String col, String value){
        tarjeta.put(col + row, value);
    }

    public String getValue(int row, char col){
        return tarjeta.get("" + col + "" + row);
    }


}
