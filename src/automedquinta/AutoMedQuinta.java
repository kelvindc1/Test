/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automedquinta;

import apoio.ConexaoBD;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import tela.DlgLogin;
import tela.JanelaPrincipal;

/**
 *
 * @author fabricio.pretto
 */
public class AutoMedQuinta {

//    public static Connection conexao = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        if (abrirConexao()) {

        new DlgLogin(null, true).setVisible(true);

        // codigo removido em 16/04
        // motivo: criada tela de Login
//        if (ConexaoBD.getInstance() != null) {
//            JanelaPrincipal minhaJanela = new JanelaPrincipal();
//            minhaJanela.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco!");
//        }

    }

////    private static boolean abrirConexao() {
////        try {
////            String dbdriver = "org.postgresql.Driver";
////            String dburl = "jdbc:postgresql://localhost:5432/automedquinta";
////            String dbuser = "postgres";
////            String dbsenha = "postgres";
////
////            // Carrega Driver do Banco de Dados
////            Class.forName(dbdriver);
////
////            if (dbuser.length() != 0) // conexão COM usuário e senha
////            {
////                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
////            } else // conexão SEM usuário e senha
////            {
////                conexao = DriverManager.getConnection(dburl);
////            }
////
////            return true;
////
////        } catch (Exception e) {
////            System.err.println("Erro ao tentar conectar: " + e);
////            return false;
////        }
//
//    }
}
