/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.java.back.business;

import io.java.back.data.Usuario;
import io.java.back.enumer.usuario.Tipo;
import io.java.back.infra.HibernateUtil;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author spezia
 */
public class UsuarioData {
    // TODO Remover método temporario

    public static Usuario selecionarUsuario() {
        Usuario usuario = (Usuario) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Usuario where login = :login")
                .setString("login", "user").uniqueResult();

        if (usuario == null) {
            usuario = new Usuario();
            usuario.setLogin("ADM-01");
            usuario.setSenha(DigestUtils.sha256Hex("123"));
            usuario.setNome("Administrator");
            usuario.setTipo(Tipo.SUPORTE);
            usuario.setDataRegistro(new Date());
            usuario.setAtivo(true);

            UsuarioData usuarioBus = new UsuarioData();
            usuarioBus.inserir(usuario);
        }
        return usuario;
    }

    public Long inserir(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(usuario);
        transaction.commit();
        return usuario.getId();
    }

    public Usuario selecionar(long id) {
        return (Usuario) HibernateUtil.getSessionFactory().openSession()
                .get(Usuario.class, id);
    }
}
