package ifpr.paranavai.jogo.servico;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Inimigo;

public abstract class FaseVisao extends JPanel implements ActionListener, KeyListener {
    protected Image fundo;
    protected Timer timer;
    protected boolean emJogo = true;
    protected Fase fase;
    protected List<Inimigo> inimigos;

    public FaseVisao() {
        this.fase = new Fase();
        this.inimigos = fase.getInimigos();
        setFocusable(true); // + define o foco inicial do jogo
        setDoubleBuffered(true); // + Otimização computacional
        addKeyListener(this); // + Definindo que a própria classe irá controlar os eventos do teclado
    }

    // public abstract void inicializaElementosGraficosAdicionais();

    public abstract void inicializaInimigos();

    public abstract void verificarColisoes();

}
