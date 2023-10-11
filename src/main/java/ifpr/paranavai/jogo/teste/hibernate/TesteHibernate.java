package ifpr.paranavai.jogo.teste.hibernate;

import ifpr.paranavai.jogo.conexao.HibernateUtil;

public class TesteHibernate {

    public static void main(String[] args) {
        HibernateUtil.getSession();
        // sessao.beginTransaction();
        // Jogador local = new Jogador("Joãozinho");

        // sessao.save(local);

        // sessao.getTransaction().commit();
        HibernateUtil.encerraSession();
    }
}
