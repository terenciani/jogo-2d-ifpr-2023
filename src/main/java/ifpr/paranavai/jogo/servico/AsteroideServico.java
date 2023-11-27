package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.visao.PrincipalVisao;
import java.util.List;
import javax.swing.ImageIcon;

public class AsteroideServico {

    private static final int QTDE_DE_ASTEROIDES = 50;
    private static final int VELOCIDADE = 1;

    public void atualizarPosicao(Asteroide asteroide) {
        if (asteroide.getPosicaoEmX() < 0) {
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            asteroide.setPosicaoEmX(PrincipalVisao.LARGURA_DA_JANELA);
            asteroide.setPosicaoEmY(y);
        } else {
            asteroide.setPosicaoEmX(asteroide.getPosicaoEmX() - VELOCIDADE);
        }
    }

    public void atualizarPosicao(List<Asteroide> asteroides) {
        for (Asteroide asteroide : asteroides) {
            this.atualizarPosicao(asteroide);
        }
    }

    public void carregarAsteroides(List<Asteroide> asteroides) {
        for (Asteroide asteroide : asteroides) {
            this.carregarImagem(asteroide);
        }
    }

    public void carregarImagem(Asteroide asteroide) {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/asteroide.png"));
        asteroide.setImagem(carregando.getImage());
        asteroide.setLarguraImagem(carregando.getImage().getWidth(null));
        asteroide.setAlturaImagem(carregando.getImage().getHeight(null));
    }

    public void inicializaAsteroides(List<Asteroide> asteroides) {
        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            this.carregarImagem(asteroide);
            asteroides.add(asteroide);
        }
    }

}
