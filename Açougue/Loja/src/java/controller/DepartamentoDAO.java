/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.ResultSet;
import model.Banco;
/**
 *
 * @author prampero
 */
public class DepartamentoDAO {
    
    public ResultSet listar() throws Exception{
        Banco banco; //Banco é classe, banco é objeto
        try {
            banco = new Banco(); //instancia o banco
            banco.comando = Banco.conexao.prepareStatement("SELECT codigo, nome FROM Departamento ORDER BY 2");
            banco.tabela = banco.comando.executeQuery();
            Banco.conexao.close();
            return(banco.tabela);
        } catch (Exception ex) {
            throw new Exception("Erro nos departamentos " + ex.getMessage());
        }
    }
}
