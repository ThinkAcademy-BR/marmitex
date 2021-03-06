package com.les.marmitex.view.helper.impl;

import com.google.gson.Gson;
import com.les.marmitex.core.dominio.Cliente;
import com.les.marmitex.core.dominio.Credito;
import com.les.marmitex.core.dominio.EntidadeDominio;
import com.les.marmitex.core.dominio.Resultado;
import com.les.marmitex.core.dominio.Usuario;
import com.les.marmitex.view.helper.IViewHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * TODO descrição da classe
 *
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date 13/09/2016
 */
@Component("/marmitex/cliente")
public class ClienteHelper implements IViewHelper {

    Cliente c = null;
    Usuario u = null;
    Credito cr = null;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        String login = null;
        String senha = null;
        String telefone = null;
        String nome = null;
        String id = null;

        if (("CONSULTAR").equals(operacao)) {
            u = new Usuario();
            c = new Cliente();

            login = request.getParameter("login");
            senha = request.getParameter("senha");

            u.setLogin(login);
            u.setSenha(senha);
            c.setUsuario(u);
        } else if (("SALVAR").equals(operacao)) {
            u = new Usuario();
            c = new Cliente();
            cr = new Credito();

            login = request.getParameter("login");
            senha = request.getParameter("senha");
            telefone = request.getParameter("telefone");
            nome = request.getParameter("nome");

            u.setLogin(login);
            u.setSenha(senha);
            c.setUsuario(u);
            c.setNome(nome);
            c.setDtCriacao(new Date());
            c.setTelefone(telefone);
            c.setCredito(cr);
        } else if (("ALTERAR").equals(operacao)) {
            u = new Usuario();
            c = new Cliente();

            login = request.getParameter("email");
            senha = request.getParameter("senha");
            telefone = request.getParameter("telefone");
            nome = request.getParameter("nome");
            id = request.getParameter("id");

            u.setLogin(login);
            u.setSenha(senha);
            c.setUsuario(u);
            c.setNome(nome);
            c.setDtCriacao(new Date());
            c.setTelefone(telefone);
            c.setId(Integer.valueOf(id));
        } else if (("EXCLUIR").equals(operacao)) {
            c = new Cliente();

            id = request.getParameter("id");
            c.setId(Integer.valueOf(id));
        }

        return c;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");
        String retorno = null;
        Gson gson = new Gson();
        List<EntidadeDominio> l = new ArrayList();
        l.add(c);

        if (("SALVAR").equals(operacao)) {
            retorno = gson.toJson(resultado);
            try {
                response.getWriter().write(retorno);
            } catch (IOException ex) {
                System.out.println("ERRO!");
            }
        } else if (("CONSULTAR").equals(operacao)) {
            retorno = gson.toJson(resultado.getEntidades().get(0));
            try {
                response.getWriter().write(retorno);
            } catch (IOException ex) {
                System.out.println("ERRO!");
            }
        } else if (("EXCLUIR").equals(operacao)) {

        } else if (("ALTERAR").equals(operacao)) {
            retorno = gson.toJson(c);
            try {
                response.getWriter().write(retorno);
            } catch (IOException ex) {
                System.out.println("ERRO!");
            }
        }
    }

}
