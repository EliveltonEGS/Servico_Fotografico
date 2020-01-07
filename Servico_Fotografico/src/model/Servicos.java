package model;

import java.util.Date;

public class Servicos {

    private Integer codigo;
    private Integer numero;
    private String descricao;
    private double valor;
    private Date data;
    private Clientes clientes;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Servicos{" + "codigo=" + codigo + ", numero=" + numero + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + ", clientes=" + clientes + '}';
    }

    public void setData(String dataSalva) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
