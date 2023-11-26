package ifpr.paranavai.jogo.visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.controle.FaseUmControle;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.visao.FaseVisao;

public class FaseUmVisao extends FaseVisao {

    private static final int DELAY = 5;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_X = 100;
    private static final int POSICAO_INICIAL_PERSONAGEM_EM_Y = 100;

    public FaseUmVisao() {
        super();
        this.controle = new FaseUmControle();
        this.emJogo = true;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/fundo.jpg"));
        this.fundo = carregando.getImage();
        this.fase.setPersonagem(new Personagem(POSICAO_INICIAL_PERSONAGEM_EM_X, POSICAO_INICIAL_PERSONAGEM_EM_Y));
        controle.inicializaElementosGraficosAdicionais(super.fase);
        controle.inicializaInimigos(super.fase);

        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            controle.redesenhaElementosGraficos(graficos, super.fase, this);
            controle.desenhaPontuacao(graficos, super.fase);
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
            controle.atirar(this.fase.getPersonagem());
        } else {
            controle.movimentarPersonagem(this.fase.getPersonagem(), codigo);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controle.pararPersonagem(this.fase.getPersonagem(), e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controle.atualizarPosicoesElementosGraficos(this.fase);
        emJogo = controle.verificarColisoes(this.fase);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
