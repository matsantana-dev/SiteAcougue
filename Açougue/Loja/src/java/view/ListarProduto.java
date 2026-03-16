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

/**
 *
 * @author prampero
 */
@WebServlet(name = "ListarProduto", urlPatterns = {"/ListarProduto"})
public class ListarProduto extends HttpServlet {

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
        ProdutoDAO produtoDAO;
        java.sql.ResultSet tabela;
        int codDep;
        
        try {
            
            codDep = Integer.parseInt(request.getParameter("codDep"));
            produtoDAO= new ProdutoDAO();
            tabela = produtoDAO.listar(codDep);

            out.println("<!DOCTYPE html>");
            out.println("<html lang='pt-br'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Produtos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='produtos-container'>");
        while (tabela.next()) {
            out.println("<form action='Comprar' method='post' class='produto'>");
            out.println("<input type='text' name='txtCodigo' value='" + tabela.getInt("codigo") + "' hidden/>");
            out.println("<input type='text' name='txtDescricao' value='" + tabela.getString("descricao") + "' hidden/>");
            out.println("<input type='text' name='txtImagem' value='" + tabela.getString("imagem") + "' hidden/>");
            out.println("<input type='text' name='txtPreco' value='" + tabela.getDouble("preco") + "' hidden/>");

            out.println("<img src='imagens/" + tabela.getString("imagem") + "' width='250px'/> <br/>");
            out.println("<font size='20'>" + tabela.getString("descricao") + "</font> <br/>");
            out.println("<font size='15'>Preço R$: " + tabela.getDouble("preco") + "</font> <br/>");
            
            int qtde = tabela.getInt("qtde");
            if (qtde > 0){
            out.println("Estoque: " + qtde + "<br/>");
            out.println("Quantidade: <input type='number' required min='1' name='txtQtde' value='1'/> <br/>");
            out.println("<input type='submit' name='b1' value='Comprar'/>");
            } else {
            out.println("<br/><strong style='color:red';>Produto esgotado</strong><br/>");
            }
            
            out.println("</form>");
        }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex){
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarDep</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro no ListarDep: " + ex.getMessage() + "</h1>");
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
