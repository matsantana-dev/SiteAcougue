/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package view;

import controller.ProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Produto;
import model.Item;

/**
 *
 * @author mathe
 */
@WebServlet(name = "Carrinho", urlPatterns = {"/Carrinho"})
public class Carrinho extends HttpServlet {

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
        HttpSession sessao;
        Produto produto;
        ProdutoDAO dao;
        Item item;
        ArrayList<Item> carrinho;
        double total = 0;
        double subtotal;
        int i;

        try {
            sessao = request.getSession();
            carrinho = (ArrayList<Item>) sessao.getAttribute("carrinho");
            if (carrinho == null) {
                out.println("<html><head><title>Carrinho Vazio</title></head><body>");
                out.println("<h2 align='center'>Seu carrinho está vazio!</h2>");
                out.println("<div align='center'><a href='principal.html'>Voltar à Loja</a></div>");
                out.println("</body></html>");
                return;
            }
            if (carrinho == null) {
                carrinho = new ArrayList<>();
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Carrinho</title>");
            out.println("<link href='./css/style.css' rel='stylesheet' />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align='center'>Seu Carrinho</h1>");
            out.println("<table border='1' align='center'>");
            out.println("<tr>");
            out.println("<th>Imagem</th>");
            out.println("<th>Quantidade</th>");
            out.println("<th>Preço Unitário</th>");
            out.println("<th>Subtotal</th>");
            out.println("<th>Remoção</th>");
            out.println("</tr>");
            
            dao = new ProdutoDAO();
            
            for (i = 0; i < carrinho.size(); i++) {
                item = carrinho.get(i);
                subtotal = item.getQtde() * item.getPrecounit();
                total += subtotal;
                
            int codproduto = item.getCodproduto();
            produto = dao.preencher(codproduto);

            out.println("<tr>");
            out.println("<td><img src='imagens/" + produto.getImagem() + "' width='100px' /></td>");
            out.println("<td align='center'>" + item.getQtde() + "</td>");
            out.println("<td align='center'>R$ " + String.format("%.2f", item.getPrecounit()) + "</td>");
            out.println("<td align='center'>R$ " + String.format("%.2f", subtotal) + "</td>");
            out.println("<td align='center'>");
            out.println("<a href='Remover?codproduto=" + item.getCodproduto() + "'>");
            out.println("<img src='imagens/lixeira.jpg' alt='Remover' width='20px' >");
            out.println("</a>");
            out.println("</td>");
            out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("<h2 align='center'>Total: R$ " + String.format("%.2f", total) + "</h2>");

            out.println("<div align='center' style='margin-top: 20px;'>");
            out.println("<form action='Finalizar' method='post'>");
            out.println("<input type='submit' value='Finalizar Compra' />");
            out.println("</form>");
            out.println("<br/>");
            out.println("<a href='principal.html'>Voltar à Loja</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro no carrinho: " + ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
