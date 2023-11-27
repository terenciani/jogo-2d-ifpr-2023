package ifpr.paranavai.jogo.visao;

import javax.swing.JFrame;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.dao.FaseDaoImpl;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.servico.FaseServico;
import javax.swing.JOptionPane;

public class PrincipalVisao extends JFrame {

    public static final int LARGURA_DA_JANELA = 1024;
    public static final int ALTURA_DA_JANELA = 728;
    private static final int INICIAR_NOVO_JOGO = 0;
    private static final int CARREGAR_ULTIMO_JOGO = 1;

    private PrincipalVisao() {
        FaseVisao faseVisao = new FaseUmVisao();
        inicializar(faseVisao);
    }

    private PrincipalVisao(Fase fase) {
        FaseVisao faseVisao = new FaseUmVisao(fase);
        inicializar(faseVisao);
    }

    private void inicializar(FaseVisao faseVisao) {
        super.add(faseVisao);
        super.setTitle("Jogo do IFPR - Campus ParanavaÃ­");
        super.setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        exibirCarregando();
        
        int opcao = exibirOpcoes();

        if (opcao == INICIAR_NOVO_JOGO) {
            new PrincipalVisao();
        }
        if (opcao == CARREGAR_ULTIMO_JOGO) {
            FaseServico faseServico = new FaseServico(new FaseDaoImpl());
            Fase fase = faseServico.buscarUltimoJogoSalvo();
            if (fase != null) {
                new PrincipalVisao(fase);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum jogo foi encontrado! Um novo jogo será iniciado!");
                new PrincipalVisao();
            }
        }
    }

    public static int exibirOpcoes() {
        Object[] opcoes = {"Iniciar Novo Jogo", "Recuperar Último Jogo Salvo", "Cancelar"};
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção:",
                "Opções",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        return escolha;
    }

    private static void exibirCarregando() {
        Carregando carregando = new Carregando();
        try {
            carregando.setVisible(true);
            HibernateUtil.getSession();
        } finally {
            carregando.setVisible(false);
            carregando.dispose();
        }
    }
}
