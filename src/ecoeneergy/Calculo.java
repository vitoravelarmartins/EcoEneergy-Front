/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoeneergy;

import com.mysql.fabric.xmlrpc.base.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author V
 */
public class Calculo {

    private final Conexao conexao;
    private String data1;
    private String data2;

    public Calculo() throws SQLException {
        conexao = Conexao.getIntancia();
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;

    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data1) {
        this.data2 = data1;
    }

    public Double selctdata(String data1, String data2) throws SQLException, ParseException {
        final String SQL = "select sum(valor_potencia*0.001*(0.014)) "
                + " from potencia where data_po between ? and ? ";
        SimpleDateFormat formatdata = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date1 = formatdata.parse(data1);
        java.util.Date date2 = formatdata.parse(data2);
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setDate(1, new java.sql.Date(date1.getTime()));
        pst.setDate(2, new java.sql.Date(date2.getTime()));
        
        //pst.setString(1, data.getData1());
        //pst.setString(2, data.getData2());

        ResultSet rs = pst.executeQuery();
        double valor = 0.0;
        
        if (rs.next()) {
            
            valor = rs.getDouble(1);
        }
        rs.close();
        pst.close();
        return valor;
    }
}
