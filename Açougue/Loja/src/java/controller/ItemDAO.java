/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.ResultSet;
import model.Banco;
import model.Item;

/**
 *
 * @author mathe
 */
public class ItemDAO {
    
    public int gravar(Item item) throws Exception {
        try {
            Banco banco;
            banco = new Banco();
            int codigo = 0;
            banco.comando = Banco.conexao.prepareStatement("INSERT INTO Item(codproduto, codvenda, precounit, qtde) VALUES (?, ?, ?, ?) RETURNING codigo");
            banco.comando.setInt(1, item.getCodproduto());
            banco.comando.setInt(2, item.getCodvenda());
            banco.comando.setDouble(3, item.getPrecounit());
            banco.comando.setInt(4, item.getQtde());
            banco.tabela = banco.comando.executeQuery();
            
            if (banco.tabela.next()) {
                codigo = banco.tabela.getInt("codigo");
            }
            Banco.conexao.close();
            return codigo;
        } catch (Exception ex) {
            throw new Exception("Erro com os itens: " + ex.getMessage());
        }
    }
    
    public ResultSet itemPorCodVenda(int codVenda) throws Exception {
        try {
            Banco banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("SELECT i.qtde, i.precounit, p.descricao, p.imagem FROM Item i JOIN Produto p ON i.codproduto = p.codigo WHERE i.codvenda = ?");
            banco.comando.setInt(1, codVenda);
            banco.tabela = banco.comando.executeQuery();
            Banco.conexao.close();
            return banco.tabela;
        } catch (Exception ex) {
            throw new Exception("Erro ao encontrar os itens na venda " + ex.getMessage());
        }
    }
}
