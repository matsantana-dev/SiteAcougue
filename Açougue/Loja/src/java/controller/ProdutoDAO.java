/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.ResultSet;
import model.Banco;
import model.Produto;
/**
 *
 * @author prampero
 */
public class ProdutoDAO {
    
    public ResultSet listar(int codDep) throws Exception{
        Banco banco; //Banco é classe, banco é objeto
        try{
            banco = new Banco(); //instancia o banco
            banco.comando = Banco.conexao.prepareStatement("SELECT codigo, descricao, preco, qtde, imagem, coddep FROM Produto WHERE coddep = ?");
            banco.comando.setInt(1, codDep);
            banco.tabela = banco.comando.executeQuery();
            Banco.conexao.close();
            return(banco.tabela);
        }
        catch (Exception ex) {
            throw new Exception("Erro ao listar/ProdutoDAO: "+ ex.getMessage());
        }
        
    } 
    
    public Produto preencher(int cod) throws Exception{
        Banco bb;
        Produto produto = null;
        try {
            bb=new Banco();
            bb.comando=Banco.conexao.prepareStatement("SELECT * FROM Produto WHERE codigo= ?;");
           bb.comando.setInt(1,cod);
            bb.tabela=bb.comando.executeQuery();
            
        if(bb.tabela.next()){
            produto = new Produto();
            produto.setCodigo(bb.tabela.getInt("codigo"));
            produto.setDescricao(bb.tabela.getString("descricao"));
            produto.setPreco(bb.tabela.getDouble("preco"));
            produto.setQtde(bb.tabela.getInt("qtde"));
            produto.setImagem(bb.tabela.getString("imagem"));
            produto.setCoddep(bb.tabela.getInt("coddep"));    
        } else {
            throw new Exception("Produto não encontrado");
        }
            Banco.conexao.close();
            return(produto);     
        } catch (Exception ex) {
            throw new Exception("Erro ao preencher/ProdutoDAO: " + ex.getMessage());
        }
    }
    
    public void atualizar(int codproduto, int aux) throws Exception {
    try {
        Banco banco = new Banco();
        banco.comando = Banco.conexao.prepareStatement("UPDATE Produto SET qtde = ? WHERE codigo = ?");
        banco.comando.setInt(1, aux);
        banco.comando.setInt(2, codproduto);
        banco.comando.executeUpdate();
        Banco.conexao.close();
    } catch (Exception ex) {
        throw new Exception("Erro ao atualizar estoque: " + ex.getMessage());
        }
    }
}
