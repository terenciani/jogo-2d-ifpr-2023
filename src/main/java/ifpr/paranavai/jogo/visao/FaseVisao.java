package ifpr.paranavai.jogo.visao;

import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.Timer;

import ifpr.paranavai.jogo.modelo.Fase;
import java.awt.Graphics;

public abstract class FaseVisao extends JPanel {

    protected Image fundo;
    protected Timer timer;
    protected boolean emJogo = true;
    protected Fase fase;

    public FaseVisao() {
        inicializar();
    }

    public FaseVisao(Fase fase) {
        this.fase = fase;
        inicializar();
    }

    private void inicializar() {
        setFocusable(true);
        setDoubleBuffered(true);
    }

    public boolean isEmJogo() {
        return emJogo;
    }

    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public abstract void paint(Graphics g);
}
