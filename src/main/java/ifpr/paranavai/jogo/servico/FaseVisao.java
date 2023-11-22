package ifpr.paranavai.jogo.servico;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Personagem;

public abstract class FaseVisao extends JPanel implements ActionListener, KeyListener {
    protected Image fundo;
    protected Timer timer;
    protected boolean emJogo = true;
    protected Personagem personagem;
    protected Fase fase;
    protected List<Inimigo> inimigos;
    protected List<Asteroide> asteroides;

    public FaseVisao() {
        this.fase = new Fase();
        this.personagem = fase.getPersonagem();
        this.inimigos = fase.getInimigos();
        this.asteroides = fase.getAsteroides();
        setFocusable(true); // + define o foco inicial do jogo
        setDoubleBuffered(true); // + Otimização computacional
        addKeyListener(this); // + Definindo que a própria classe irá controlar os eventos do teclado
    }

    public abstract void inicializaElementosGraficosAdicionais();

    public abstract void inicializaInimigos();

    public abstract void verificarColisoes();

    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }
}
