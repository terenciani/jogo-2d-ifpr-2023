package ifpr.paranavai.jogo.visao;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.controle.FaseUmControle;
import ifpr.paranavai.jogo.modelo.ElementoGrafico;
import ifpr.paranavai.jogo.modelo.Fase;
import java.util.List;

public class FaseUmVisao extends FaseVisao {

    private static final int DELAY = 5;

    private FaseUmControle controle;

    public FaseUmVisao() {
        super();
        this.controle = new FaseUmControle(this);
        this.carregarImagemFundo();
        this.addKeyListener(this.controle);
        this.emJogo = true;
        this.timer = new Timer(DELAY, controle);
        this.timer.start();
    }
    
    public FaseUmVisao(Fase fase) {
        super(fase);
        this.controle = new FaseUmControle(super.fase, this);
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
            desenharInstrucoes(graficos);
        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/fimdejogo.jpg"));
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
        }
        g.dispose();
    }

    public void redesenharElementosGraficos(Graphics2D graficos) {
        redesenhaElementoGrafico(graficos, super.fase.getAsteroides());
        redesenhaElementoGrafico(graficos, super.fase.getPersonagem());
        redesenhaElementoGrafico(graficos, super.fase.getPersonagem().getTiros());
        redesenhaElementoGrafico(graficos, super.fase.getInimigos());
    }
    
    private void desenharInstrucoes(Graphics2D graficos) {
        String textoPontuacao = "Pressione ESC para salvar!";
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 20));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, PrincipalVisao.LARGURA_DA_JANELA - 275, PrincipalVisao.ALTURA_DA_JANELA - 50);
    }

    private void desenharPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + fase.getPersonagem().getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }
    

    private void redesenhaElementoGrafico(Graphics2D graficos, ElementoGrafico elementoGrafico) {
        graficos.drawImage(elementoGrafico.getImagem(), elementoGrafico.getPosicaoEmX(), elementoGrafico.getPosicaoEmY(), this);
    }

    private <T> void redesenhaElementoGrafico(Graphics2D graficos, List<T> elementosGraficos) {
        for (T e : elementosGraficos) {
            if (e instanceof ElementoGrafico) {
                redesenhaElementoGrafico(graficos, (ElementoGrafico) e);
            }
        }
    }
}
