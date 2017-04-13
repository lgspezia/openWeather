/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.java.back.business;

import io.java.back.data.Chamado;
import io.java.back.data.Usuario;
import io.java.back.enumer.chamado.Status;
import io.java.back.enumer.chamado.Tipo;
import io.java.back.infra.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author spezia
 */
public class ChamadoData {

    public Long inserir(Chamado chamado) {
        chamado.setDataRegistro(new Date());
        chamado.setStatus(Status.NOVO);
        chamado.setTipo(Tipo.SOLICITACAO);
        chamado.setUsuario(UsuarioData.selecionarUsuario());
        chamado.setUsuarioStatus(UsuarioData.selecionarUsuario());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(chamado);
        transaction.commit();
        return chamado.getId();
    }

    public void alterar(Chamado chamado) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(chamado);
        t.commit();
    }

    public void excluir(long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Chamado c = selecionar(id);

        Transaction t = s.beginTransaction();
        s.delete(c);
        t.commit();
    }

    public Chamado selecionar(long id) {
        
    	Chamado chmd = new Chamado();
    	chmd = HibernateUtil.getSessionFactory().openSession().get(Chamado.class, id);
    	
    	if(chmd == null){
	    	if (id == 1) {
	        	return testaCriaModela(1);
			} else {
				return testaCriaModela(2);
			}
    	}
    	return chmd; 
    }

    public List<Chamado> listar() {    	
    	List<Chamado> list = new ArrayList<Chamado>();
    	
    	try{
    		// consulta JPQL
    		list = HibernateUtil.getSessionFactory().openSession().createQuery("from Chamado").list();
    		//testes
        	list.add(testaCriaModela(1));
        	list.add(testaCriaModela(2));
        	list.add(testaCriaModela(3));
    	} catch(Exception ex){
    		
    		ex.getStackTrace();
    	}
    		return list;
    }
    
    public Chamado testaCriaModela(int i){
    	
    	Usuario usuario = new Usuario();
    	usuario.setAtivo(true);
		//usuario.setId(1);
		usuario.setTipo(io.java.back.enumer.usuario.Tipo.ADMIN);
		usuario.setLogin("ADM-01");
		usuario.setNome("Administrator");
		usuario.setSenha(DigestUtils.sha256Hex("123"));
		usuario.setDataRegistro(new Date());
		
		Chamado chamado = new Chamado();
		if(i == 1){
	    	chamado.setId(1);
	    	chamado.setMensagem("vamos");
	    	chamado.setAssunto("tempo");
	    	chamado.setStatus(Status.PENDENTE); 
	    	chamado.setDataRegistro(new Date());
	    	chamado.setTipo(Tipo.SOLICITACAO);
	    	chamado.setUsuario(usuario);
	    	chamado.setUsuarioStatus(usuario);
		}
		if(i == 2){
	    	chamado.setId(2);
	    	chamado.setMensagem("vamos sempre");
	    	chamado.setAssunto("tempo parado");
	    	chamado.setStatus(Status.NOVO); 
	    	chamado.setDataRegistro(new Date());
	    	chamado.setTipo(Tipo.SOLICITACAO);
	    	chamado.setUsuario(usuario);
	    	chamado.setUsuarioStatus(usuario);
		}
		if(i == 3){
	    	chamado.setId(3);
	    	chamado.setMensagem("vamos que vamos");
	    	chamado.setAssunto("tempo ao tempo");
	    	chamado.setStatus(Status.FECHADO); 
	    	chamado.setDataRegistro(new Date());
	    	chamado.setTipo(Tipo.CORRECAO);
	    	chamado.setUsuario(usuario);
	    	chamado.setUsuarioStatus(usuario);
		}
    	return chamado;
    }
}
