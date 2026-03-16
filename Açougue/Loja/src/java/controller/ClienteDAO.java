/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Banco;
import model.Cliente;

/**
 *
 * @author mathe
 */
public class ClienteDAO {
    
    public Cliente login(String login, String senha) throws Exception {
        Banco banco;
        Cliente cliente = null;
        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("SELECT codigo, nome, login FROM Cliente WHERE login =? AND senha =?");
            banco.comando.setString(1, login);
            banco.comando.setString(2, senha);
            banco.tabela = banco.comando.executeQuery();
            
            if (banco.tabela.next()) {
                cliente = new Cliente();
                cliente.setCodigo(banco.tabela.getInt("codigo"));
                cliente.setNome(banco.tabela.getString("nome"));
                cliente.setLogin(banco.tabela.getString("login"));
            } 
            Banco.conexao.close();
            return cliente;
        } catch (Exception ex) {
            throw new Exception("Erro no login: " + ex.getMessage());
        }
    }
    
    public void cadastrar(String nome, String login, String senha) throws Exception {
        Banco banco;
        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("INSERT INTO Cliente (nome, login, senha) VALUES (?, ?, ?) RETURNING codigo");
            banco.comando.setString(1, nome);
            banco.comando.setString(2, login);
            banco.comando.setString(3, senha);
            banco.tabela = banco.comando.executeQuery();

            Cliente cliente = new Cliente();
            if (banco.tabela.next()) {
                cliente.setCodigo(banco.tabela.getInt("codigo"));
                cliente.setNome(nome);
                cliente.setLogin(login);
            }
            Banco.conexao.close();
        } catch (Exception ex) {
            throw new Exception("Erro no cadastro: " + ex.getMessage());
        }
    }
    
    public Cliente buscarLogin(String login) throws Exception {
        Banco banco;
        Cliente cliente = null;

        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("SELECT * FROM Cliente WHERE login = ?");
            banco.comando.setString(1, login);
            banco.tabela = banco.comando.executeQuery();

            if (banco.tabela.next()) {
                cliente = new Cliente();
                cliente.setCodigo(banco.tabela.getInt("codigo"));
                cliente.setNome(banco.tabela.getString("nome"));
                cliente.setLogin(banco.tabela.getString("login"));
                cliente.setSenha(banco.tabela.getString("senha"));
            }
            Banco.conexao.close();
            return cliente;
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar login: " + ex.getMessage());
        }
    }
}
