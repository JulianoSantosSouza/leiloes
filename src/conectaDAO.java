
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;


public class conectaDAO {
    Connection conn = null;
    PreparedStatement prep;
    ResultSet rs;
    
        
    public Connection connectDB(){
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "sistemas01");
            properties.setProperty("useSSL", "false");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UC11", properties);
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }
    
}
