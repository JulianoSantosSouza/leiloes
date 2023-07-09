

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        int sta;
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            
            sta = prep.executeUpdate();
            
            return sta; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }    
    }
    
    public List<ProdutosDTO> listagem() {
        
        conn = new conectaDAO().connectDB();

        String sql = "select * from produtos";

        try {
            //Conexão
            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();

            List<ProdutosDTO> lista = new ArrayList<>(); //crinado uma listaset

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id")); //Nome da tabela banco de dados "id"
                produto.setNome(resultset.getString("nome")); //Nome da tabela banco de dados "nome"
                produto.setValor(resultset.getInt("valor")); //Nome da tabela banco de dados "Valor"
                produto.setStatus(resultset.getString("status")); //Nome da tabela banco de dados "status"
              
               
                lista.add(produto);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }

    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    public int venderProduto(ProdutosDTO produtosDTO){
        int status;
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            prep.setString(1, produtosDTO.getStatus());
            prep.setInt(2, produtosDTO.getId());
            status = prep.executeUpdate();
            return status;
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
            return e.getErrorCode();
        }
    }
    
    public List<ProdutosDTO> listarProdutoVendido() {
        conn = new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos WHERE status LIKE 'Vendido'";
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            List<ProdutosDTO> listProdutos = new ArrayList<>();

            while (resultset.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();

                produtosDTO.setId(resultset.getInt("id"));
                produtosDTO.setNome(resultset.getString("nome"));
                produtosDTO.setValor(resultset.getInt("valor"));
                produtosDTO.setStatus(resultset.getString("status"));

                listProdutos.add(produtosDTO);
            }
            return listProdutos;
            
            
        } catch (Exception e) {
            System.out.println("Erro ao Listar o Produto: " + e.getMessage());
            return null;
        }
    }
    
        
}

