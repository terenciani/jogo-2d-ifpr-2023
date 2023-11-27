package ifpr.paranavai.jogo.controle;

import ifpr.paranavai.jogo.dao.FaseDaoImpl;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.servico.AsteroideServico;
import ifpr.paranavai.jogo.servico.FaseServico;
import ifpr.paranavai.jogo.servico.InimigoServico;
import ifpr.paranavai.jogo.servico.PersonagemServico;
import ifpr.paranavai.jogo.servico.TiroServico;
import ifpr.paranavai.jogo.visao.FaseUmVisao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FaseUmControle implements ActionListener, KeyListener {

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
        PersonagemServico personagemServico = new PersonagemServico();
        InimigoServico inimigoServico = new InimigoServico();
        AsteroideServico asteroideServico = new AsteroideServico();
        TiroServico tiroServico = new TiroServico();

        asteroideServico.atualizarPosicao(fase.getAsteroides());
        personagemServico.atualizarPosicao(fase.getPersonagem());
        tiroServico.atualizarPosicao(fase.getPersonagem().getTiros());
        inimigoServico.atualizarPosicao(fase.getInimigos());
    }

    private void carregarElementosGraficosAdicionais() {
        AsteroideServico asteroideServico = new AsteroideServico();
        asteroideServico.carregarAsteroides(this.fase.getAsteroides());
    }

    private void carregarInimigos() {
        InimigoServico inimigoServico = new InimigoServico();
        inimigoServico.carregarInimigos(this.fase.getInimigos());
    }

    private void carregarPersonagem() {
        PersonagemServico personagemServico = new PersonagemServico();
        TiroServico tiroServico = new TiroServico();
        personagemServico.carregarImagem(this.fase.getPersonagem());
        tiroServico.carregarTiros(this.fase.getPersonagem().getTiros());
    }
    
    private void inicializarElementosGraficosAdicionais() {
        AsteroideServico asteroideServico = new AsteroideServico();
        asteroideServico.inicializaAsteroides(this.fase.getAsteroides());
    }

    private void inicializarInimigos() {
        InimigoServico inimigoServico = new InimigoServico();
        inimigoServico.inicializaInimigos(this.fase.getInimigos());
    }

    private void inicializarPersonagem() {
        PersonagemServico personagemServico = new PersonagemServico();
        Personagem personagem = new Personagem(POSICAO_INICIAL_PERSONAGEM_EM_X, POSICAO_INICIAL_PERSONAGEM_EM_Y);
        personagemServico.carregarImagem(personagem);
        this.fase.setPersonagem(personagem);
    }

    private void verificarColisoes() {
        InimigoServico inimigoServico = new InimigoServico();
        if (inimigoServico.verificarColisoes(this.fase)) {
            this.faseUmVisao.setEmJogo(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.atualizarPosicoesElementosGraficos();
        this.verificarColisoes();
        faseUmVisao.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PersonagemServico personagemServico = new PersonagemServico();
        int codigo = e.getKeyCode();
        if (codigo == KeyEvent.VK_SPACE) {
            personagemServico.atirar(this.fase.getPersonagem());
        } else if (codigo == KeyEvent.VK_ESCAPE) {
            FaseServico faseServico = new FaseServico(new FaseDaoImpl());
            faseServico.criarPontoSalvamento(this.fase);
        } else {
            personagemServico.mover(this.fase.getPersonagem(), codigo);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PersonagemServico personagemServico = new PersonagemServico();
        personagemServico.parar(this.fase.getPersonagem(), e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
