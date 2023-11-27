package ifpr.paranavai.jogo.controle;

import ifpr.paranavai.jogo.dao.FaseDaoImpl;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.servico.AsteroideServico;
import ifpr.paranavai.jogo.servico.FaseServico;
import ifpr.paranavai.jogo.servico.InimigoServico;
import ifpr.paranavai.jogo.servico.PersonagemServico;
import ifpr.paranavai.jogo.servico.TiroServico;
import ifpr.paranavai.jogo.visao.FaseUmVisao;
import ifpr.paranavai.jogo.visao.PrincipalVisao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FaseUmControle implements ActionListener, KeyListener {

    private final InimigoServico inimigoServico = new InimigoServico();
    private final PersonagemServico personagemServico = new PersonagemServico();
    private final AsteroideServico asteroideServico = new AsteroideServico();
    private final TiroServico tiroServico = new TiroServico();
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_X = 100;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_Y = 100;
    private Fase fase;
    private FaseUmVisao faseUmVisao;

    public FaseUmControle(FaseUmVisao faseUmVisao) {
        faseUmVisao.setFase(new Fase());
        this.fase = faseUmVisao.getFase();
        this.faseUmVisao = faseUmVisao;
        this.inicializarPersonagem();
        this.inicializarInimigos();
        this.inicializarElementosGraficosAdicionais();
    }

    public FaseUmControle(Fase fase, FaseUmVisao faseUmVisao) {
        this.fase = fase;
        this.faseUmVisao = faseUmVisao;
        this.carregarPersonagem();
        this.carregarInimigos();
        this.carregarElementosGraficosAdicionais();
    }

    private void atualizarPosicoesElementosGraficos() {
        if (this.faseUmVisao.isEmJogo()) {
            this.asteroideServico.atualizarPosicao(fase.getAsteroides());
            this.personagemServico.atualizarPosicao(fase.getPersonagem());
            this.tiroServico.atualizarPosicao(fase.getPersonagem().getTiros());
            this.inimigoServico.atualizarPosicao(fase.getInimigos());
        }
    }

    private void carregarElementosGraficosAdicionais() {
        this.asteroideServico.carregarAsteroides(this.fase.getAsteroides());
    }

    private void carregarInimigos() {
        this.inimigoServico.carregarInimigos(this.fase.getInimigos());
    }

    private void carregarPersonagem() {
        this.personagemServico.carregarImagem(this.fase.getPersonagem());
        this.tiroServico.carregarTiros(this.fase.getPersonagem().getTiros());
    }

    private void inicializarElementosGraficosAdicionais() {
        this.asteroideServico.inicializaAsteroides(this.fase.getAsteroides());
    }

    private void inicializarInimigos() {
        this.inimigoServico.inicializaInimigos(this.fase.getInimigos());
    }

    private void inicializarPersonagem() {
        Personagem personagem = new Personagem(POSICAO_INICIAL_PERSONAGEM_EM_X, POSICAO_INICIAL_PERSONAGEM_EM_Y);
        this.personagemServico.carregarImagem(personagem);
        this.fase.setPersonagem(personagem);
    }

    private void verificarColisoes() {
        if (this.faseUmVisao.isEmJogo()) {
            if (this.inimigoServico.verificarColisoes(this.fase)) {
                this.faseUmVisao.setEmJogo(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.atualizarPosicoesElementosGraficos();
        this.verificarColisoes();
        this.faseUmVisao.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();
        if (this.faseUmVisao.isEmJogo()) {
            if (codigo == KeyEvent.VK_SPACE) {
                this.personagemServico.atirar(this.fase.getPersonagem());
            } else if (codigo == KeyEvent.VK_ESCAPE) {
                FaseServico faseServico = new FaseServico(new FaseDaoImpl());
                faseServico.criarPontoSalvamento(this.fase);
            } else {
                this.personagemServico.mover(this.fase.getPersonagem(), codigo);
            }
        } else if (codigo == KeyEvent.VK_M) {
            novoJogo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.faseUmVisao.isEmJogo()) {
            this.personagemServico.parar(this.fase.getPersonagem(), e.getKeyCode());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void novoJogo() {
        JFrame jFrameOrigem = (JFrame) SwingUtilities.getWindowAncestor(this.faseUmVisao);
        jFrameOrigem.dispose();
        PrincipalVisao principalVisao = new PrincipalVisao();
        principalVisao.iniciarJogo();
    }
}
