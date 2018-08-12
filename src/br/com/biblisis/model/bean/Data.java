/*
 * 
 * Formatacao para o SQL 
 * DateTime -> Data dt = new Data("yyyy-MM-dd HH:mm:ss")
 * Date     -> new Data("yyyy-MM-dd");
 * 
 */


package br.com.biblisis.model.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Data {
    private Date dataParaManipular;
    private GregorianCalendar gc;
    private SimpleDateFormat formataData;
    private Calendar calendar;

    /**
     * @param formatacao o formato para a data (Date)
     * @see new Data("yyyy-MM-dd HH:mm:ss");
     */
    public Data(String formatacao) {
        this.dataParaManipular  = new Date();
        this.gc                 = new GregorianCalendar();
        this.gc.setTime(dataParaManipular);
        this.formataData        = new SimpleDateFormat(formatacao);
        
    }
    
    /**
     * @param formatacao o formato para a data (Date)
     * @param data recebe uma data personalizada do tipo string na formatacao especificada
     * @see new Data("yyyy-MM-dd HH:mm:ss", "2018-02-24 00:11:52");
     */
    public Data(String formatacao, String data) throws ParseException {
        this.dataParaManipular   =  (new SimpleDateFormat(formatacao)).parse(data);
        this.gc                  = new GregorianCalendar();
        this.gc.setTime(dataParaManipular);
        this.formataData         = new SimpleDateFormat(formatacao);
    }
    
    public int getMinutos() {
        return getCampo(Calendar.MINUTE);
    }

    public int getHoras() {
        return getCampo(Calendar.HOUR);
    }

    private int getCampo(int tipoCampo) {
        return gc.get(tipoCampo);
    }
    
    public boolean depoisDaDataAtual() {
          return dataParaManipular.after(new Date());
    }

    public int comparar(Data data) {
        Calendar c = new GregorianCalendar();
        c.setTime(data.dataParaManipular);
        return gc.compareTo(c);
    } 
    
    /*Metodos Essenciais*/
    public void adicionarDias(int dias) {
       adicionar(dias, Calendar.DAY_OF_MONTH);
    }

    public void adicionarMeses(int meses) {
        adicionar(meses, Calendar.MONTH);
    }

    public void adicionarAnos(int anos) {
        adicionar(anos, Calendar.YEAR);
    }

    private void adicionar(int quantidade, int tipoCampo) {
        gc.add(tipoCampo, quantidade);
        dataParaManipular = gc.getTime();
    }
    
    public boolean antesDe(Data data) {
        return dataParaManipular.before(data.getData());
    }
    
    public boolean depoisDe(Data data) {
        return dataParaManipular.after(data.getData());
    }
    
    public Date getData() {
        return this.dataParaManipular;
    }
    
    public String toString() {
        return formataData.format(dataParaManipular);
    }
}


     /* Adiciona Dias
        // minha data (no caso o dia de hoje)  
        Date minhaData = new Date();  

        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(dtDevolucao.getData());  

        // incrementa minha data mais sete dias  
        calendar.add(Calendar.DAY_OF_MONTH, 4);  

        // imprime a data incrementada  
        System.out.println(calendar.getTime());
    */



    /*  Converte Date para String
        public String getDataString() {
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            return formataData.format(dataParaManipular);
        }

        //SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*Convertendo String Para Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = formato.parse("2015-11-21 23:11:52");
        System.out.println(data);    
    */

