package ifpr.paranavai.jogo.principal;

import javax.swing.JFrame;

import org.hibernate.Session;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.servico.FaseUmVisao;

public class PrincipalVisao extends JFrame {
    public static final int LARGURA_DA_JANELA = 1024;
    public static final int ALTURA_DA_JANELA = 728;

    public PrincipalVisao() {
        FaseUmVisao fase = new FaseUmVisao();
        super.add(fase);
        super.setTitle("Jogo do IFPR - Campus Paranavaí");
        super.setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        new PrincipalVisao();
    }
}