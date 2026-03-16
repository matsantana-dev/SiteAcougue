/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package view;

import controller.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Produto;
import model.Item;

/**
 *
 * @author aluno
 */
@WebServlet(name = "Comprar", urlPatterns = {"/Comprar"})
public class Comprar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        Produto produto;
        ProdutoDAO dao;
        HttpSession sessao;
        ArrayList<Item> carrinho;
        Item item;
        int qtde;
        int i;
        int codigo;
        
        // Recupera sessão e carrinho
        sessao = request.getSession(true);
        carrinho = (ArrayList<Item>) sessao.getAttribute("carrinho");
        // Se não existe cria o carrinho
        if (carrinho == null) {
            carrinho = new ArrayList<Item>();
            }
        
        try {
            
            dao = new ProdutoDAO();
            codigo = Integer.parseInt(request.getParameter("txtCodigo"));
            qtde = Integer.parseInt(request.getParameter("txtQtde"));
            produto = dao.preencher(codigo);
            
            item = new Item();
            
            item.setCodigo(produto.getCodigo());
            item.setQtde(qtde);
            item.setPrecounit(produto.getPreco());
            item.setCodproduto(produto.getCodigo());
            item.setCodvenda(0);
            
            
            boolean itemExist = false;
            
            for(i = 0; i < carrinho.size(); i++) {
                if (carrinho.get(i).getCodproduto() == item.getCodproduto()) {
                    int nqtde = carrinho.get(i).getQtde() + item.getQtde();
                    carrinho.get(i).setQtde(nqtde);
                    itemExist = true;
                    break;
                }
            }
            
        if (!itemExist) {
            carrinho.add(item);
        }
                
        sessao.setAttribute("carrinho", carrinho);
        response.sendRedirect("Carrinho");
        } catch (Exception ex) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro: " + ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            System.out.print("Erro no servidor" + ex.getMessage());
            
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}