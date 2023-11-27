package ifpr.paranavai.jogo.visao;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.controle.FaseUmControle;
import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiro;
import java.util.List;

public class FaseUmVisao extends FaseVisao {
    
    private static final int DELAY = 5;

    private FaseUmControle controle;
    
    public FaseUmVisao() {
        super();
        this.controle = new FaseUmControle(fase, this);
        this.carregarImagemFundo();
        this.addKeyListener(this.controle);
        this.emJogo = true;
        this.timer = new Timer(DELAY, controle);
        this.timer.start();
    }

    private void carregarImagemFundo() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/fundo.jpg"));
        this.fundo = carregando.getImage();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            redesenharElementosGraficos(graficos);
            desenharPontuacao(graficos);
        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/fimdejogo.jpg"));
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
        }
        g.dispose();
    }
    
    public void redesenharElementosGraficos(Graphics2D graficos) {
        Personagem personagem = super.fase.getPersonagem();
        for (Asteroide asteroide : super.fase.getAsteroides()) {
            graficos.drawImage(asteroide.getImagem(), asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), this);
        }
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
        List<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }
        for (Inimigo inimigo : super.fase.getInimigos()) {
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
        }
    }
    
    public void desenharPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + fase.getPersonagem().getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }
}
