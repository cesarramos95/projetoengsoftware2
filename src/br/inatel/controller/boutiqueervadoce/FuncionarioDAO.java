/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.controller.boutiqueervadoce;

import br.inatel.model.boutiqueervadoceprojeto.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public class FuncionarioDAO {

    /**
     * *************** CONEXÃO COM O BANCO DE DADOS ***********************
     */
    // objeto responsável pela conexão com o servidor do banco de dados
    Connection con;
    // objeto responsável por preparar as consultas dinâmicas
    PreparedStatement pst;
    // objeto responsável por executar as consultas estáticas
    Statement st;
    // objeto responsável por referenciar a tabela resultante da busca
    ResultSet rs;

    // NOME DO BANCO DE DADOS
    String database = "boutique";
    // URL: VERIFICAR QUAL A PORTA
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    // USUÁRIO
    String user = "root";
    // SENHA
    //String password = "J3susCr1st0Salvador";
    String password = "J3susCr1st0Salvador";

    boolean sucesso = false;

    // Conectar ao banco de dados
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão feita com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    /**
     * Funcionando 100% 
     ********************** INSERIR DADOS *********************************
     */
    public boolean inserirFuncionario(Funcionario novoFuncionario) {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO funcionario (nome,cpf,sexo,email,cidade,rua,num,estado,tel) values (?,?,?,?,?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoFuncionario.getNome()); //1- refere-se à primeira interrogação
            pst.setInt(2, novoFuncionario.getCpf());  //2- refere-se à segunda interrogação
            pst.setString(3, novoFuncionario.getSexo());
            pst.setString(4, novoFuncionario.getEmail());
            pst.setString(5, novoFuncionario.getCidade());
            pst.setString(6, novoFuncionario.getEndereco());
            pst.setInt(7, novoFuncionario.getNum());
            pst.setString(8, novoFuncionario.getEstado());//e assim por diante....
            pst.setInt(9, novoFuncionario.getTel());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }

    /**
     * Funcionando 100%
     * ********************** BUSCAR DADOS ********************************
     */
    public ArrayList<Funcionario> buscarUsuariosSemFiltro() {
        ArrayList<Funcionario> listaDeFuncionario = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM funcionario";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca

            System.out.println("Lista de Funcionario: ");
            while (rs.next()) {
                // Criando uma variavel temporária para armazenar os dados recebidos do banco
                Funcionario f = new Funcionario();
                // Atribuindo valores aos atributos deste funcionario
                f.setCpf(rs.getInt(1));                         // Primeira coluna: CPF
                f.setNome(rs.getNString(2));                    // Segunda coluna: Nome
                f.setSexo(rs.getNString(3));                    // Terceira coluna: Sexo
                f.setEmail(rs.getNString(4));                   // Quarta coluna: Email
                f.setCidade(rs.getNString(5));                  // Quinta coluna: Cidade
                f.setEndereco(rs.getNString(6));                // Sexta coluna: Rua
                f.setNum(rs.getInt(7));                         // Sétima coluna: Numero
                f.setEstado(rs.getNString(8));                  // Oitava coluna: Estado
                f.setTel(rs.getInt(9));                         // Nona coluna: Telefone
                
                // Printando os dados
                System.out.println("Nome = " + f.getNome());
                System.out.println("CPF = " + f.getCpf());
                System.out.println("Sexo = " + f.getSexo());
                System.out.println("Email = " + f.getEmail());
                System.out.println("Cidade = " + f.getCidade());
                System.out.println("Endereço = " + f.getEndereco());
                System.out.println("Numero = " + f.getNum());
                System.out.println("Estado = " + f.getEstado());
                System.out.println("Telefone = " + f.getTel());
                System.out.println("Loja = " + f.getLoja());
                System.out.println("Nome de usuario = " + f.getNome_usuario());

                System.out.println("---------------------------------");

                // Salvando no ArrayList
                listaDeFuncionario.add(f);
            }
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDeFuncionario;
    }

    /**
     * ********************** ATUALIZAR DADOS ********************************
     */
    public boolean atualizarNomeUsuario(int id, String novoNome) {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE funcionario SET nome=? WHERE id=?";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setInt(2, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }

        }
        return sucesso;
    }

    /**
     * ********************** DELETAR REGISTROS ******************************
     */
    public boolean deletarCliente(int id) {
        connectToDb();
        //Comando em SQL:
        String sql = "DELETE FROM funcionario WHERE id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
}
