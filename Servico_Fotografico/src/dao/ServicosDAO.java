package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Clientes;
import model.Servicos;

public class ServicosDAO {

    public void salvar(Servicos servicos) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();

        sql.append("insert into tb_servicos ");
        sql.append("(ser_descricao, ser_valor, ser_data, ser_cli_codigo, ser_numero) ");
        sql.append("values (?, ?, ?, ?, ?)");

        try {
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, servicos.getDescricao());
            stmt.setDouble(2, servicos.getValor());
            stmt.setDate(3, new java.sql.Date(servicos.getData().getTime()));
            stmt.setInt(4, servicos.getClientes().getCodigo());
            stmt.setInt(5, servicos.getNumero());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ArrayList<Servicos> listAll(String nome) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from tb_servicos as s inner join tb_clientes as c "
                + "on s.ser_cli_codigo = c.cli_codigo where c.cli_nome like ? "
                + "order by s.ser_numero desc");

        ArrayList<Servicos> lista = new ArrayList<>();

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

                Servicos s = new Servicos();

                s.setCodigo(rs.getInt("ser_codigo"));
                s.setDescricao(rs.getString("ser_descricao"));
                s.setData(rs.getDate("ser_data"));
                s.setValor(rs.getDouble("ser_valor"));
                s.setNumero(rs.getInt("ser_numero"));
                s.setClientes(c);

                lista.add(s);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Servicos> listLimit(String nome) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from tb_servicos as s inner join tb_clientes as c "
                + "on s.ser_cli_codigo = c.cli_codigo where c.cli_nome like ? "
                + "order by s.ser_numero desc limit 1");

        ArrayList<Servicos> lista = new ArrayList<>();

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

                Servicos s = new Servicos();

                s.setCodigo(rs.getInt("ser_codigo"));
                s.setDescricao(rs.getString("ser_descricao"));
                s.setData(rs.getDate("ser_data"));
                s.setValor(rs.getDouble("ser_valor"));
                s.setNumero(rs.getInt("ser_numero"));
                s.setClientes(c);

                lista.add(s);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Servicos bucarPorCodigo(Servicos servicos) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from tb_servicos where ser_codigo = ?");

        Servicos s = null;

        try {
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, servicos.getCodigo());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                s = new Servicos();

                s.setCodigo(rs.getInt("ser_codigo"));
                s.setDescricao(rs.getString("ser_descricao"));
                s.setData(rs.getDate("ser_data"));
                s.setValor(rs.getDouble("ser_valor"));
                s.setNumero(rs.getInt("ser_numero"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return s;
    }

    public void excluir(Servicos servicos) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();

        sql.append("delete from tb_servicos where ser_codigo = ?");

        try {
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, servicos.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void editar(Servicos servicos) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("update tb_servicos set ");
        sql.append("ser_descricao = ?, ser_valor = ?, ser_data = ?, ser_cli_codigo = ?, ser_numero = ? ");
        sql.append("where ser_codigo = ?");

        try {

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, servicos.getDescricao());
            stmt.setDouble(2, servicos.getValor());
            stmt.setDate(3, new java.sql.Date(servicos.getData().getTime()));
            stmt.setInt(4, servicos.getClientes().getCodigo());
            stmt.setInt(5, servicos.getNumero());
            stmt.setInt(6, servicos.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public ArrayList<Servicos> consultaEntreDatas(Date data_inicial, Date data_final) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();

        sql.append("select * from tb_servicos as s inner join tb_clientes as c "
                + "on s.ser_cli_codigo = c.cli_codigo where s.ser_data between ? and ? order by s.ser_numero desc");
        ArrayList<Servicos> lista = new ArrayList<>();

        try {

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setDate(1, new java.sql.Date(data_inicial.getTime()));
            stmt.setDate(2, new java.sql.Date(data_final.getTime()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes c = new Clientes();
                c.setCodigo(rs.getInt("cli_codigo"));
                c.setNome(rs.getString("cli_nome"));
                c.setCpf(rs.getString("cli_cpf"));
                c.setTelefone(rs.getString("cli_telefone"));
                c.setCelular(rs.getString("cli_celular"));
                c.setEndereco(rs.getString("cli_endereco"));

                Servicos s = new Servicos();

                s.setCodigo(rs.getInt("ser_codigo"));
                s.setDescricao(rs.getString("ser_descricao"));
                s.setData(rs.getDate("ser_data"));
                s.setValor(rs.getDouble("ser_valor"));
                s.setNumero(rs.getInt("ser_numero"));
                s.setClientes(c);

                lista.add(s);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Servicos> buscarNumero(Integer numero) {
        Connection con = ConnectionFactory.conectar();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from tb_servicos as s inner join tb_clientes as c ");
        sql.append("on s.ser_cli_codigo = c.cli_codigo where s.ser_numero = ?");

        ArrayList<Servicos> servicos = new ArrayList<>();

        try {

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, numero);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes c = new Clientes();
                c.setCodigo(rs.getInt("cli_codigo"));
                c.setNome(rs.getString("cli_nome"));
                c.setCpf(rs.getString("cli_cpf"));
                c.setTelefone(rs.getString("cli_telefone"));
                c.setCelular(rs.getString("cli_celular"));
                c.setEndereco(rs.getString("cli_endereco"));

                Servicos s = new Servicos();

                s.setCodigo(rs.getInt("ser_codigo"));
                s.setDescricao(rs.getString("ser_descricao"));
                s.setData(rs.getDate("ser_data"));
                s.setValor(rs.getDouble("ser_valor"));
                s.setNumero(rs.getInt("ser_numero"));
                s.setClientes(c);

                servicos.add(s);
            }
            return servicos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
