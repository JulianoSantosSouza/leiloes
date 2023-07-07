

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
    
    
    
        
}

