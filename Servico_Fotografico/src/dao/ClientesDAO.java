package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Clientes;

public class ClientesDAO {

    public void salvar(Clientes clientes) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("insert into tb_clientes ");
        sql.append("(cli_nome, cli_cpf, cli_telefone, cli_celular, cli_endereco) ");
        sql.append("values (?, ?, ? ,?, ?)");

        try {

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getCpf());
            stmt.setString(3, clientes.getTelefone());
            stmt.setString(4, clientes.getCelular());
            stmt.setString(5, clientes.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    public Clientes buscarPorCodigo(Clientes clientes) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();

        sql.append("select * from tb_clientes where cli_codigo = ?");

        Clientes c = null;
        try {
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, clientes.getCodigo());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Clientes();

                c.setCodigo(rs.getInt("cli_codigo"));
                c.setNome(rs.getString("cli_nome"));
                c.setCpf(rs.getString("cli_cpf"));
                c.setTelefone(rs.getString("cli_telefone"));
                c.setCelular(rs.getString("cli_celular"));
                c.setEndereco(rs.getString("cli_endereco"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public void editar(Clientes clientes) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("update tb_clientes set ");
        sql.append("cli_nome = ?, cli_cpf = ?, cli_telefone = ?, cli_celular = ?, cli_endereco = ? ");
        sql.append("where cli_codigo = ?");

        try {

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getCpf());
            stmt.setString(3, clientes.getTelefone());
            stmt.setString(4, clientes.getCelular());
            stmt.setString(5, clientes.getEndereco());
            stmt.setInt(6, clientes.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public void excluir(Clientes clientes) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("delete from tb_clientes where cli_codigo = ?");

        try {
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, clientes.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Clientes> pesquisarPorNome(String nome) {
        Connection con = ConnectionFactory.conectar();

        ArrayList<Clientes> listaClientes = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        //sql.append("select * from tb_clientes where cli_nome = ?");
        sql.append("select * from tb_clientes where cli_nome like ? order by cli_nome asc");

        try {
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes c = new Clientes();

                c.setCodigo(rs.getInt("cli_codigo"));
                c.setNome(rs.getString("cli_nome"));
                c.setCpf(rs.getString("cli_cpf"));
                c.setTelefone(rs.getString("cli_telefone"));
                c.setCelular(rs.getString("cli_celular"));
                c.setEndereco(rs.getString("cli_endereco"));
                listaClientes.add(c);
            }

            return listaClientes;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
