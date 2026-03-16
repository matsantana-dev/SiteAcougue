/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package view;

import controller.ItemDAO;
import controller.ProdutoDAO;
import controller.VendaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Item;
import model.Venda;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import model.Cliente;
import model.Produto;

/**
 *
 * @author mathe
 */
@WebServlet(name = "Finalizar", urlPatterns = {"/Finalizar"})
public class Finalizar extends HttpServlet {

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
        HttpSession sessao = request.getSession();
        ArrayList<Item> carrinho = (ArrayList<Item>) sessao.getAttribute("carrinho");
        Venda venda;
        VendaDAO vdao;
        ItemDAO idao;
        double total = 0;
        Timestamp datav;
        int codv;
        int codcli;
        SimpleDateFormat x;
        ResultSet itensVenda;
        Cliente usuario;
        ProdutoDAO pdao;
        Produto produto;
        
        
        
        try {
            usuario = (Cliente) sessao.getAttribute("usuario");
            pdao = new ProdutoDAO();
            
            // Checa se o cliente está logado
            if (usuario == null){
            out.println("<html>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Você precisa estar LOGADO para continuar!');");
            out.println("window.location.href = 'login.html';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            return;
            }
            
            // checa se a quantidade de itens tem no estoque
            for (int i = 0; i <carrinho.size(); i++) {
                Item item = carrinho.get(i);
                produto = pdao.preencher(item.getCodproduto());
                
            if (item.getQtde() > produto.getQtde()) {
                carrinho.remove(i); // remove o item automaticamente se for maior que o estoque
                sessao.setAttribute("carrinho", carrinho);
                
                out.println("<html>");
                out.println("<body>");
                out.println("<script>alert('Escolha uma quantidade dentro do estoque existente para o produto: " + produto.getDescricao() + "');");
                out.println("window.location.href = 'view/Carrinho';</script>");
                out.println("</body></html>");
                return;
                }
                total += item.getQtde() * item.getPrecounit();
            }
            
            // Calcula o total
            if (carrinho != null) {
                for (Item item : carrinho) {
                    total += item.getQtde() * item.getPrecounit();
                }
            }
            
            // Info das venda
            datav = new Timestamp(System.currentTimeMillis());
            codcli = (int) sessao.getAttribute("codcli");
            venda = new Venda();
            venda.setDatav(datav);
            venda.setCodcli(codcli);
            venda.setTotal(total);
            
            // Grava a venda e pega o cod
            vdao = new VendaDAO();
            codv = vdao.gravar(venda);
            
            // grava os itens da venda
            idao = new ItemDAO();
            for (Item item : carrinho) {
                item.setCodvenda(codv);
                idao.gravar(item);
            
            // atualiza o estoque dos produtos
            produto = pdao.preencher(item.getCodproduto());
            int aux = produto.getQtde() - item.getQtde();
            pdao.atualizar(produto.getCodigo(), aux);
            }
            
            
            sessao.removeAttribute("carrinho"); // limpa o carrinho
            
            // pega os itens gravado pra estilização
            itensVenda = idao.itemPorCodVenda(codv);
            // Formata a data
            x = new SimpleDateFormat("dd/MM/yyyy"); // formato de data dia/mes/ano

            out.println("<!DOCTYPE html>");
            out.println("<html lang='pt-br'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>🎉 Venda Concluída! 🎉</title>");
            out.println("</head>");
            out.println("<body class='venda-finalizada'>");
            out.println("<div class='container'>");

            out.println("<header class='venda-header'>");
            out.println("<h1>Venda Concluída com Sucesso!</h1>");
            out.println("</header>");

            out.println("<section class='venda-detalhes'>");
            out.println("<h2>Detalhes da Venda</h2>");
            out.println("<p><strong>Código da Venda:</strong> " + codv + "</p>");
            out.println("<p><strong>Total da Venda:</strong> R$ " + String.format("%.2f", total) + "</p>");
            out.println("<p><strong>Data da Venda:</strong> " + x.format(datav) + "</p>");
            out.println("</section>");

            out.println("<section class='venda-info'>");
            out.println("<h3>Itens Comprados</h3>");

            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Produto</th>");
            out.println("<th>Descrição</th>");
            out.println("<th>Quantidade</th>");
            out.println("<th>Preço Unitário</th>");
            out.println("<th>Total</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (itensVenda.next()) {
                int qtde = itensVenda.getInt("qtde");
                double precounit = itensVenda.getDouble("precounit");
                String descricao = itensVenda.getString("descricao");
                String imagem = itensVenda.getString("imagem");

            out.println("<tr>");
            out.println("<td><img src='imagens/" + imagem + "' alt='Produto' width='50'></td>");
            out.println("<td>" + descricao + "</td>");
            out.println("<td>" + qtde + "</td>");
            out.println("<td>R$ " + String.format("%.2f", precounit) + "</td>");
            out.println("<td>R$ " + String.format("%.2f", qtde * precounit) + "</td>");
            out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</section>");

            out.println("<footer class='venda-footer'>");
            out.println("<p><a href='principal.html'align='center'>Voltar para a loja</a></p>");
            out.println("</footer>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            out.println("<footer class='footer'>");
            out.println("<p>&copy; Feito por Matheus Sant'Ana e Ana Clara - 3° Info - Turma 1</p>");
            out.println("</footer>");
        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Finalizar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro ao finalizar a compra: " + ex.getMessage() + "</h1>");
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
