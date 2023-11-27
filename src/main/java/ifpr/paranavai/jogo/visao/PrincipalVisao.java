package ifpr.paranavai.jogo.visao;

import javax.swing.JFrame;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.dao.FaseDaoImpl;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.servico.FaseServico;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class PrincipalVisao extends JFrame {

    public static final int LARGURA_DA_JANELA = 1024;
    public static final int ALTURA_DA_JANELA = 728;
    private static final int INICIAR_NOVO_JOGO = 0;
    private static final int CARREGAR_ULTIMO_JOGO = 1;
    private static final int CANCELAR = 2;

    public PrincipalVisao() {
    }

    private void inicializar(FaseVisao faseVisao) {
        this.add(faseVisao);
        this.setTitle("Jogo do IFPR - Campus ParanavaÃ­");
        this.setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        exibirCarregando();
        PrincipalVisao principalVisao = new PrincipalVisao();
        principalVisao.iniciarJogo();
    }

    public final void iniciarJogo() throws HeadlessException {
        int opcao = exibirOpcoes();
        switch (opcao) {
            case INICIAR_NOVO_JOGO:
                this.inicializar(new FaseUmVisao());
                break;
            case CARREGAR_ULTIMO_JOGO:
                FaseServico faseServico = new FaseServico(new FaseDaoImpl());
                Fase fase = faseServico.buscarUltimoJogoSalvo();
                if (fase != null) {
                    this.inicializar(new FaseUmVisao(fase));
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum jogo foi encontrado! Um novo jogo será iniciado!");
                    this.inicializar(new FaseUmVisao());
                }
                break;
            case CANCELAR:
                this.encerrarJogo();
                break;
            default:
                break;
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

    public void encerrarJogo() {
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
        this.dispose();
    }
}
