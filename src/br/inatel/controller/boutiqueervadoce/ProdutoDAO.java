/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.controller.boutiqueervadoce;


import br.inatel.model.boutiqueervadoceprojeto.Cliente;
import br.inatel.model.boutiqueervadoceprojeto.Produto;
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
public class ProdutoDAO {
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
    public boolean inserirProduto(Produto novoProduto) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO produto(codigo,tipo,marca,quant,moda,precoCusto,precoVenda) values (?,?,?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setInt(1, novoProduto.getCodigo()); //1- refere-se à primeira interrogação
            pst.setString(2, novoProduto.getTipo());  //2- refere-se à segunda interrogação
            pst.setString(3, novoProduto.getMarca());
            pst.setInt(4, novoProduto.getQuant());
            pst.setString(5, novoProduto.getModa());//e assim por diante....
            pst.setInt(6, novoProduto.getPrecoCusto());//e assim por diante....
            pst.setInt(7, novoProduto.getPrecoVenda());//e assim por diante....
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
    public ArrayList<Produto> buscarUsuariosSemFiltro() 
    {
        ArrayList<Produto> listaDeProduto = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM produto";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Produto: ");
            while(rs.next())
            {
                
                Produto p = new Produto();
                // Atribuindo valores aos atributos deste funcionario
                p.setCodigo(rs.getInt(1));           // Primeira coluna: CPF
                p.setTipo(rs.getString(2));                    // Segunda coluna: Nome             
                p.setMarca(rs.getNString(3));                    // Terceira coluna: Sexo
                p.setQuant(rs.getInt(4));                   // Quarta coluna: Email
                p.setModa(rs.getNString(5));                  // Quinta coluna: Cidade
                p.setPrecoCusto(rs.getInt(6));                // Sexta coluna: Rua
                p.setPrecoVenda(rs.getInt(7));                         // Sétima coluna: Numero
                
                listaDeProduto.add(p);
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
        return listaDeProduto;
    }
    
    /************************ ATUALIZAR DADOS *********************************/
    public boolean atualizarNomeUsuario(int id, String novoNome) 
    {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE usuario SET nome=? WHERE id=?";
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
    public boolean deletarProduto(int id) 
    {
        connectToDb();
        //Comando em SQL:
        String sql = "DELETE FROM produto WHERE id=?";
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
