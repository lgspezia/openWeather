/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.java.back.jdbc.dao;

import io.java.back.data.Chamado;
import io.java.back.enumer.chamado.Status;
import io.java.back.infra.ConexaoJDBC;
import io.java.back.infra.ConexaoPostgresJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author spezia
 */
public class ChamadoDAO {

    private final ConexaoJDBC conexao;

    public ChamadoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoPostgresJDBC();
    }

    public Long inserir(Chamado chamado) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sqlQuery = "INSERT INTO chamado (assunto, status, mensagem) VALUES (?, ?, ?) RETURNING id";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, chamado.getAssunto());
            stmt.setString(2, chamado.getStatus().toString());
            stmt.setString(3, chamado.getMensagem());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getLong("id");
            }
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return id;
    }

    public int alterar(Chamado chamado) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE chamado SET assunto = ?, status = ?, mensagem = ? WHERE id = ?";
        int linhasAfetadas = 0;

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, chamado.getAssunto());
            stmt.setString(2, chamado.getStatus().toString());
            stmt.setString(3, chamado.getMensagem());
            stmt.setLong(4, chamado.getId());

            linhasAfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return linhasAfetadas;
    }

    public int excluir(long id) throws SQLException, ClassNotFoundException {
        int linhasAlfetadas = 0;
        String sqlQuery = "DELETE FROM chamado WHERE id = ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id);
            linhasAlfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return linhasAlfetadas;
    }

    public Chamado selecionar(long id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM chamado WHERE id = ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return parser(rs);
            }
        } catch (SQLException e) {
            throw e;
        }

        return null;
    }

    public List<Chamado> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM chamado ORDER BY id";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            List<Chamado> chamados = new ArrayList<>();

            while (rs.next()) {
                chamados.add(parser(rs));
            }

            return chamados;
        } catch (SQLException e) {
            throw e;
        }
    }

    private Chamado parser(ResultSet resultSet) throws SQLException {
        Chamado chmd = new Chamado();

        chmd.setId(resultSet.getLong("id"));
        chmd.setAssunto(resultSet.getString("assunto"));
        chmd.setMensagem(resultSet.getString("mensagem"));
        chmd.setStatus(Status.valueOf(resultSet.getString("status")));

        return chmd;
    }
}
