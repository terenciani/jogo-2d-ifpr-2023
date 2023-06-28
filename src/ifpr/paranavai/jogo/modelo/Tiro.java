package ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;

public class Tiro extends ElementoGrafico {

    private static int VELOCIDADE = 2;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        super.setPosicaoEmX(posicaoPersonagemEmX);
        super.setPosicaoEmY(posicaoPersonagemEmY);
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\tiro.png");
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
    }
}