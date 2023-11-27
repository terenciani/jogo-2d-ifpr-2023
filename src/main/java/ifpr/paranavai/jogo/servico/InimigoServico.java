package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Inimigo;
import java.util.List;
import javax.swing.ImageIcon;

public class InimigoServico {

    private static final int VELOCIDADE = 2;

    public void carregarImagem(Inimigo inimigo) {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/inimigo.png"));
        inimigo.setImagem(carregando.getImage());
        inimigo.setLarguraImagem(carregando.getImage().getWidth(null));
        inimigo.setAlturaImagem(carregando.getImage().getHeight(null));
    }

    public void atualizarPosicao(Inimigo inimigo) {
        inimigo.setPosicaoEmX(inimigo.getPosicaoEmX() - VELOCIDADE);
    }

    public void atualizarPosicao(List<Inimigo> inimigos) {
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel()) {
                inimigos.remove(inimigo);
            } else {
                this.atualizarPosicao(inimigo);
            }
        }
    }
}
