/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.controller.boutiqueervadoce;


import br.inatel.model.boutiqueervadoceprojeto.Empresa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author RaryagnesdePaulaAlva
 */
public class EmpresaDAO {
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
    public boolean inserirEmpresa(Empresa novoEmpresa) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO empresa (nome,cnpj,tel,email,cidade,estado,rua,num) values (?,?,?,?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoEmpresa.getNome()); //1- refere-se à primeira interrogação
            pst.setInt(2, novoEmpresa.getCnpj());  //2- refere-se à segunda interrogação
            pst.setInt(3, novoEmpresa.getTel());  
            pst.setString(4, novoEmpresa.getEmail());
            pst.setString(4, novoEmpresa.getCidade());
            pst.setString(5, novoEmpresa.getEstado());
            pst.setString(6, novoEmpresa.getRua());//e assim por diante....
             pst.setInt(7, novoEmpresa.getNum()); 
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
    public ArrayList<Empresa> buscarUsuariosSemFiltro() 
    {
        ArrayList<Empresa> listaDeEmpresa = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM empresa";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Empresa: ");
            while(rs.next())
            {
                Empresa lojaTemp = new Empresa(rs.getString("nome"),rs.getString("cnpj"));
                System.out.println("Nome = "+lojaTemp.getNome());
                System.out.println("CNPJ = "+lojaTemp.getCnpj());
                System.out.println("Telefone = "+lojaTemp.getTel());
                System.out.println("Email = "+lojaTemp.getEmail());
                System.out.println("Cidade = "+lojaTemp.getCidade());
                System.out.println("Estado = "+lojaTemp.getEstado());
                System.out.println("Rua = "+lojaTemp.getRua());
                System.out.println("Numero = "+lojaTemp.getNum());
                System.out.println("---------------------------------");
                listaDeEmpresa.add(lojaTemp);
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
        return listaDeEmpresa;
    }
    
    /************************ ATUALIZAR DADOS *********************************/
    public boolean atualizarNomeUsuario(int id, String novoNome) 
    {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE empresa SET nome=? WHERE id=?";
         //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setInt(2, id);
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
        String sql = "DELETE FROM empresa WHERE id=?";
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
