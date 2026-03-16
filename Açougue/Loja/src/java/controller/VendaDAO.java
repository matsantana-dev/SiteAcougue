/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Banco;
import model.Venda;

/**
 *
 * @author mathe
 */
public class VendaDAO {
    public int gravar(Venda venda) throws Exception {
        Banco banco;
        int codVenda = -1;
        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("INSERT INTO Venda (total, datav, codcli) VALUES (?, ?, ?) RETURNING codigo");
            banco.comando.setDouble(1, venda.getTotal());
            banco.comando.setTimestamp(2, venda.getDatav());
            banco.comando.setInt(3, venda.getCodcli());
            banco.tabela = banco.comando.executeQuery();

            if (banco.tabela.next()) {
                codVenda = banco.tabela.getInt("codigo");
            }
            Banco.conexao.close();
            return codVenda;
        } catch (Exception ex) {
            throw new Exception("Erro na venda: " + ex.getMessage());
        }
    }
}
