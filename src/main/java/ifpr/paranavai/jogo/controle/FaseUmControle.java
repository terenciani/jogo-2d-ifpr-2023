package ifpr.paranavai.jogo.controle;

import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.servico.AsteroideServico;
import ifpr.paranavai.jogo.servico.InimigoServico;
import ifpr.paranavai.jogo.servico.PersonagemServico;
import ifpr.paranavai.jogo.servico.TiroServico;
import ifpr.paranavai.jogo.visao.FaseUmVisao;
import ifpr.paranavai.jogo.visao.PrincipalVisao;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FaseUmControle implements ActionListener, KeyListener {

    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_X = 100;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_Y = 100;
    private static final int QTDE_DE_ASTEROIDES = 50;
    private static final int QTDE_DE_INIMIGOS = 40;
    private Fase fase;
    private FaseUmVisao faseUmVisao;

    public FaseUmControle(Fase fase, FaseUmVisao faseUmVisao) {
        this.fase = fase;
        this.faseUmVisao = faseUmVisao;
        this.inicializaPersonagem();
        this.inicializaInimigos();
        this.inicializaElementosGraficosAdicionais();
    }

    private void inicializaElementosGraficosAdicionais() {
        AsteroideServico asteroideServico = new AsteroideServico();
        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            asteroideServico.carregarImagem(asteroide);
            this.fase.getAsteroides().add(asteroide);
        }
    }

    private void inicializaInimigos() {
        InimigoServico inimigoServico = new InimigoServico();
        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            inimigoServico.carregarImagem(inimigo);
            this.fase.getInimigos().add(inimigo);
        }
    }

    public void atualizarPosicoesElementosGraficos() {
        PersonagemServico personagemServico = new PersonagemServico();
        InimigoServico inimigoServico = new InimigoServico();
        AsteroideServico asteroideServico = new AsteroideServico();
        TiroServico tiroServico = new TiroServico();

        personagemServico.atualizarPosicao(fase.getPersonagem());

        asteroideServico.atualizarPosicao(fase.getAsteroides());

        tiroServico.atualizarPosicao(fase.getPersonagem().getTiros());
        inimigoServico.atualizarPosicao(fase.getInimigos());
    }

    private void verificarColisoes() {
        Rectangle formaPersonagem = this.fase.getPersonagem().getRectangle();

        for (int i = 0; i < this.fase.getInimigos().size(); i++) {
            Inimigo inimigo = this.fase.getInimigos().get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                this.fase.getPersonagem().setEhVisivel(false);
                inimigo.setEhVisivel(false);
                this.faseUmVisao.setEmJogo(false);
                System.out.println("Colidiu");
            }
            List<Tiro> tiros = this.fase.getPersonagem().getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    int pontuacaoAtual = this.fase.getPersonagem().getPontuacao();
                    this.fase.getPersonagem().setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PersonagemServico personagemServico = new PersonagemServico();
        int codigo = e.getKeyCode();
        if (codigo == KeyEvent.VK_SPACE) {
            personagemServico.atirar(this.fase.getPersonagem());
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
    public void actionPerformed(ActionEvent e) {
        this.atualizarPosicoesElementosGraficos();
        this.verificarColisoes();
        faseUmVisao.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void inicializaPersonagem() {

        PersonagemServico personagemServico = new PersonagemServico();
        Personagem personagem = new Personagem(POSICAO_INICIAL_PERSONAGEM_EM_X, POSICAO_INICIAL_PERSONAGEM_EM_Y);
        personagemServico.carregarImagem(personagem);
        this.fase.setPersonagem(personagem);

    }

}
