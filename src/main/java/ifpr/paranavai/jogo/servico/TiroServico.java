package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.visao.PrincipalVisao;
import java.util.List;
import javax.swing.ImageIcon;

public class TiroServico {

    private static final int VELOCIDADE = 2;

    public void atualizarPosicao(Tiro tiro) {
        tiro.setPosicaoEmX(tiro.getPosicaoEmX() + VELOCIDADE);
    }

    public void atualizarPosicao(List<Tiro> tiros) {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            if (tiro.getPosicaoEmX() > PrincipalVisao.LARGURA_DA_JANELA || !tiro.getEhVisivel()) {
                tiros.remove(tiro);
            } else {
                this.atualizarPosicao(tiro);
            }
        }
    }

    public void carregarImagem(Tiro tiro) {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/tiro.png"));
        tiro.setImagem(carregando.getImage());
        tiro.setLarguraImagem(carregando.getImage().getWidth(null));
        tiro.setAlturaImagem(carregando.getImage().getHeight(null));
    }
}
