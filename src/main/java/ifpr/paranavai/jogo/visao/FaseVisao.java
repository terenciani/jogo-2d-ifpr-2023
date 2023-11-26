package ifpr.paranavai.jogo.visao;

import ifpr.paranavai.jogo.controle.FaseControle;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import ifpr.paranavai.jogo.modelo.Fase;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public abstract class FaseVisao extends JPanel implements ActionListener, KeyListener {

    protected Image fundo;
    protected Timer timer;
    protected boolean emJogo = true;
    protected Fase fase;
    protected FaseControle controle;

    public FaseVisao() {
        this.fase = new Fase();
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this);
    }
    
    public abstract void paint(Graphics g);
}
