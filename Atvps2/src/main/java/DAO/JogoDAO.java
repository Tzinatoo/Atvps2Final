
package DAO;
import Conexao.conexao;
import Model.Jogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.List;

public class JogoDAO {
    public void inserirJogo (Jogo j){
        try {
        String SQL = "INSERT INTO thaleszd.jogo (nome, jogo) VALUES (?,?)";
        Connection minhaConexao = conexao.getConexao();
        PreparedStatement comando = minhaConexao.prepareStatement(SQL);
        comando.setString(1, j.getNomej());
        comando.setInt(2, j.getCodjogo());
        int retorno = comando.executeUpdate();
        if(retorno>0){
            JOptionPane.showMessageDialog(null, "Jogo " +j.getNomej()+ " inserido com sucesso." );
        }
        else{
            JOptionPane.showMessageDialog(null, "Erro ao inserir o Jogo " +j.getNomej()+ ", verifique os LOGS.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public List <Jogo> listaDeJogo(){
        try {
            String SQL="SELECT nome, jogo   FROM thaleszd.jogo";
            List <Jogo>listaDeJogo = new ArrayList<Jogo>();
            Connection c =conexao.getConexao();
            PreparedStatement ps=c.prepareStatement(SQL);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Jogo atual=new Jogo();
                atual=this.pegaDados(resultado);
                listaDeJogo.add(atual);
            }

            return listaDeJogo;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private Jogo pegaDados(ResultSet resultado){
        try {
            Jogo atual=new Jogo();
            atual.setNomej (resultado.getString("nome"));
            atual.setCodjogo(resultado.getInt("jogo"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
