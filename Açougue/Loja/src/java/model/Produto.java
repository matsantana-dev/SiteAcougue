/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aluno
 */
public class Produto {
    public int codigo;
    public String descricao;
    public double preco;
    public int qtde;
    public String imagem;
    public int coddep;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setCodigo(String c) {
        this.setCodigo(Integer.parseInt(c));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws Exception
    {
        if(descricao.trim().length()>1) 
            //se a descr pedir uma letra descricao.contains("a")
        this.descricao = descricao;
        else
            throw new Exception("Descrição inválida");
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    //metodo polimorfico chama o metodo que recebe o tipo da variável.
    public void setPreco(String p) {
        this.setPreco(Double.parseDouble(p));
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) throws Exception{
        if(qtde>0)
        this.qtde = qtde;
        else throw new Exception("Qtde deve ser maior que zero");
    }
    public void setQtde(String q) throws Exception{
        this.setQtde(Integer.parseInt(q));
    }
    public double getTotalProduto(){
         double t=0; 
          t=this.qtde * this.preco;
        
        return(t);
    }

    public int getCoddep() {
        return coddep;
    }

    public void setCoddep(int coddep) {
        this.coddep = coddep;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    
}
