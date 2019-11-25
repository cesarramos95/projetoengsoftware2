/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.controller.boutiqueervadoce;

import br.inatel.model.boutiqueervadoceprojeto.Cliente;
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
public class ClienteDAO {
    /***************** CONEXÃO COM O BANCO DE DADOS ************************/
    
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
    public void connectToDb() 
    {
        try 
        {  
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão feita com sucesso!");
        } 
        catch (SQLException ex) 
        {
             System.out.println("Erro: " + ex.getMessage());
        }
                
    }
    
     
    /************************ INSERIR DADOS *********************************/
    public boolean inserirCliente(Cliente novoCliente) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO cliente (nome,cpf,sexo,email,cidade,rua,num,estado,tel) values (?,?,?,?,?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoCliente.getNome()); //1- refere-se à primeira interrogação
            pst.setString(2, novoCliente.getCpf());  //2- refere-se à segunda interrogação
            pst.setString(3, novoCliente.getSexo());
            pst.setString(4, novoCliente.getEmail());
            pst.setString(5, novoCliente.getCidade());
            pst.setString(6, novoCliente.getRua());
            pst.setInt(7, novoCliente.getNum());
            pst.setString(8, novoCliente.getEstado());//e assim por diante....
            pst.setInt(9, novoCliente.getTel());
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {   //Encerra a conexão
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }

    /************************ BUSCAR DADOS *********************************/
    public ArrayList<Cliente> buscarClientes() 
    {
        ArrayList<Cliente> listaDeCliente = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM cliente";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Cliente: ");
            while(rs.next())
            {
                Cliente c = new Cliente();
                // Atribuindo valores aos atributos deste funcionario
                c.setNome(rs.getNString(1));           // Primeira coluna: CPF
                c.setCpf(rs.getString(2));                    // Segunda coluna: Nome             
                c.setSexo(rs.getNString(3));                    // Terceira coluna: Sexo
                c.setEmail(rs.getNString(4));                   // Quarta coluna: Email
                c.setCidade(rs.getNString(5));                  // Quinta coluna: Cidade
                c.setRua(rs.getNString(6));                // Sexta coluna: Rua
                c.setNum(rs.getInt(7));                         // Sétima coluna: Numero
                c.setEstado(rs.getNString(8));                  // Oitava coluna: Estado
                c.setTel(rs.getInt(9)); 
                /*Cliente clienteTemp = new Cliente(rs.getString("cpf"),
                                                  rs.getString("nome"),
                                                  rs.getString("sexo"),
                                                  rs.getString("email"),
                                                  rs.getString("cidade"),
                                                  rs.getString("rua"),
                                                  rs.getInt("num"),
                                                  rs.getString("estado"), 
                                                  rs.getInt("tel"));
                /*System.out.println("Nome = "+clienteTemp.getNome());
                System.out.println("CPF = "+clienteTemp.getCpf());
                System.out.println("Sexo = "+clienteTemp.getSexo());
                System.out.println("Email = "+clienteTemp.getEmail());
                System.out.println("Cidade = "+clienteTemp.getCidade());
                System.out.println("Rua = "+clienteTemp.getRua());
                System.out.println("Numero = "+clienteTemp.getNum());
                System.out.println("Estado = "+clienteTemp.getEstado());
                System.out.println("Telefone = "+clienteTemp.getTel());
                System.out.println("---------------------------------");*/
                
                listaDeCliente.add(c);
            }
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDeCliente;
    }
    
    /************************ ATUALIZAR DADOS *********************************/
    public boolean atualizarNomeCliente(String novoNome, String nCpf) 
    {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE usuario SET nome=? WHERE cpf=?";
         //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setString(2, nCpf);
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }

        }
        return sucesso;
    }
    
    /************************ DELETAR REGISTROS *******************************/
    public boolean deletarCliente(int id) 
    {
        connectToDb();
        //Comando em SQL:
        String sql = "DELETE FROM cliente WHERE cpf=?";
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
}
