/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Apresentacao;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import apoio.IDAOT;

/**
 *
 * @author fabricio.pretto
 */
public class ApresentacaoDAO implements IDAOT<Apresentacao> {

    private ResultSet resultadoQ = null;

//    public boolean salvar(Apresentacao apres) {
//        try {
////            Statement st = automedquinta.AutoMedQuinta.conexao.createStatement();
//            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
//
//            String sql = "";
//            if (apres.getId() == 0) {
//                sql = "INSERT INTO apresentacao VALUES ("
//                        + "DEFAULT, "
//                        + "'" + apres.getDescricao() + "')";
//            } else {
//                sql = "UPDATE apresentacao "
//                        + "SET descricao = '" + apres.getDescricao() + "' "
//                        + "WHERE id = " + apres.getId();
//            }
//
//            System.out.println("SQL: " + sql);
//
//            int resultado = st.executeUpdate(sql);
//
//            return true;
//
//        } catch (Exception e) {
//            System.out.println("Erro salvar apresentação = " + e);
//            return false;
//        }
//    }
//
//    public boolean excluir(int id) {
//        try {
//            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
//
//            String sql = "DELETE "
//                    + "FROM apresentacao "
//                    + "WHERE id = " + id;
//
//            System.out.println("SQL: " + sql);
//
//            // executa consulta - exclusao
//            int resultado = st.executeUpdate(sql);
//
//            return true;
//
//        } catch (Exception e) {
//            System.out.println("Erro ao excluir: " + e);
//            return false;
//        }
//    }
//
//    public boolean editar(Apresentacao apres) {
//        return true;
//    }
//
//    public Apresentacao consultar(int id) {
//        Apresentacao apresentacao = null; //= new Apresentacao();
//
//        try {
//            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
//
//            String sql = "SELECT * "
//                    + "FROM apresentacao "
//                    + "WHERE id = " + id;
//
//            System.out.println("SQL: " + sql);
//
//            // executa consulta
//            resultadoQ = st.executeQuery(sql);
//
//            // avanca ResultSet
//            if (resultadoQ.next()) {
//                apresentacao = new Apresentacao();
//
//                // obtem dados do RS
//                apresentacao.setId(resultadoQ.getInt("id"));
//                apresentacao.setDescricao(resultadoQ.getString("descricao"));
//            }
//
//        } catch (Exception e) {
//            System.out.println("Erro ao consultar: " + e);
//        }
//
//        return apresentacao;
//    }
//
    @Override
    public boolean salvar(Apresentacao o) {
        boolean retorno = true;

        try {
//            Statement st = automedquinta.AutoMedQuinta.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";
            if (o.getId() == 0) {
                sql = "INSERT INTO apresentacao VALUES ("
                        + "DEFAULT, "
                        + "'" + o.getDescricao() + "') "
                        + "RETURNING id";
            } else {
                sql = "UPDATE apresentacao "
                        + "SET descricao = '" + o.getDescricao() + "' "
                        + "WHERE id = " + o.getId();
            }

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            System.out.println("ID usado no Insert: " + resultadoQ.getInt(1));

        } catch (Exception e) {
            System.out.println("Erro salvar apresentação = " + e);
            retorno = false;
        }

        return retorno;
    }

    @Override
    public boolean atualizar(Apresentacao o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE "
                    + "FROM apresentacao "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql);

            // executa consulta - exclusao
            int resultado = st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Apresentacao> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Apresentacao> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Apresentacao consultarId(int id) {
        Apresentacao apresentacao = null; //= new Apresentacao();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM apresentacao "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql);

            // executa consulta
            resultadoQ = st.executeQuery(sql);

            // avanca ResultSet
            if (resultadoQ.next()) {
                apresentacao = new Apresentacao();

                // obtem dados do RS
                apresentacao.setId(resultadoQ.getInt("id"));
                apresentacao.setDescricao(resultadoQ.getString("descricao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        return apresentacao;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM apresentacao "
                    + "WHERE "
                    + "DESCRICAO ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][2];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM apresentacao "
                    + "WHERE "
                    + "DESCRICAO ILIKE '%" + criterio + "%'");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.GREEN);
                } else {
                    setBackground(Color.LIGHT_GRAY);
                }
                return this;
            }
        });
    }
}
