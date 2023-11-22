package ifpr.paranavai.jogo.servico;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.principal.PrincipalVisao;

public class FaseUmVisao extends FaseVisao {
    private static final int DESLOCAMENTO = 3;
    private static final int DELAY = 5;
    private static final int QTDE_DE_INIMIGOS = 40;
    private static final int QTDE_DE_ASTEROIDES = 50;
    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_X = 100;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_Y = 100;

    public FaseUmVisao() {
        super();
        this.emJogo = true;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/fundo.jpg"));
        this.fundo = carregando.getImage();

        this.personagem = new Personagem(POSICAO_INICIAL_PERSONAGEM_EM_X, POSICAO_INICIAL_PERSONAGEM_EM_Y);

        this.inicializaElementosGraficosAdicionais();

        this.inicializaInimigos();

        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    @Override
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Asteroide asteroide : asteroides) {
                // Desenhar o asteroide na nossa tela.
                graficos.drawImage(asteroide.getImagem(), asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), this);
            }

            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
            // local chamada tiros.
            List<Tiro> tiros = personagem.getTiros();

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Tiro tiro : tiros) {
                // Desenhar o tiro na nossa tela.
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Inimigo inimigo : inimigos) {
                // Desenhar o inimigo na nossa tela.
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            super.desenhaPontuacao(graficos);
        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/fimdejogo.jpg"));
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
        }
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();
        if (codigo == KeyEvent.VK_SPACE) {
            int frenteDaNave = super.personagem.getPosicaoEmX() + super.personagem.getLarguraImagem();
            int meioDaNave = super.personagem.getPosicaoEmY() + (super.personagem.getAlturaImagem() / 2);
            Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
            super.personagem.getTiros().add(tiro);
        } else {
            switch (codigo) {
                case KeyEvent.VK_UP:
                    this.personagem.setDeslocamentoEmY(-DESLOCAMENTO);
                    break;
                case KeyEvent.VK_DOWN:
                    this.personagem.setDeslocamentoEmY(DESLOCAMENTO);
                    break;
                case KeyEvent.VK_LEFT:
                    this.personagem.setDeslocamentoEmX(-DESLOCAMENTO);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.personagem.setDeslocamentoEmX(DESLOCAMENTO);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.personagem.setDeslocamentoEmY(0);
                break;
            case KeyEvent.VK_DOWN:
                this.personagem.setDeslocamentoEmY(0);
                break;
            case KeyEvent.VK_LEFT:
                this.personagem.setDeslocamentoEmX(0);
                break;
            case KeyEvent.VK_RIGHT:
                this.personagem.setDeslocamentoEmX(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Asteroide asteroide : this.asteroides) {
            asteroide.atualizar();
        }

        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        List<Tiro> tiros = personagem.getTiros();

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < tiros.size(); i++) {
            // Obter o objeto tiro da posicao i do ArrayList
            Tiro tiro = tiros.get(i);
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (tiro.getPosicaoEmX() > PrincipalVisao.LARGURA_DA_JANELA || !tiro.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (LARGURA_DA_JANELA)
                tiros.remove(tiro);
            else
                // Atualizar a posição do tiro.
                tiro.atualizar();
        }

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < this.inimigos.size(); i++) {
            // Obter o objeto inimigo da posicao i do ArrayList
            Inimigo inimigo = this.inimigos.get(i);
            // Verificar se (if) a posição do x (inimigo.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (0)
                inimigos.remove(inimigo);
            else
                // Atualizar a posição do inimigo.
                inimigo.atualizar();
        }
        this.verificarColisoes();
        repaint();
    }

    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRectangle();

        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                this.personagem.setEhVisivel(false);
                inimigo.setEhVisivel(false);
                emJogo = false;
            }
            List<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    int pontuacaoAtual = this.personagem.getPontuacao();
                    this.personagem.setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
        }
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        super.asteroides = new ArrayList<Asteroide>();

        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            super.asteroides.add(asteroide);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
