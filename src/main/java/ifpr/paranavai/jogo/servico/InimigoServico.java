package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.visao.PrincipalVisao;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.ImageIcon;

public class InimigoServico {

    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int QTDE_DE_INIMIGOS = 40;
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

    public void inicializaInimigos(Fase fase) {
        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            this.carregarImagem(inimigo);
            fase.getInimigos().add(inimigo);
        }
    }

    public boolean verificarColisoes(Fase fase) {

        Rectangle formaPersonagem = fase.getPersonagem().getRectangle();
        for (int i = 0; i < fase.getInimigos().size(); i++) {
            Inimigo inimigo = fase.getInimigos().get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                fase.getPersonagem().setEhVisivel(false);
                inimigo.setEhVisivel(false);
                return true;
            }
            List<Tiro> tiros = fase.getPersonagem().getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    int pontuacaoAtual = fase.getPersonagem().getPontuacao();
                    fase.getPersonagem().setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
        }
        return false;
    }
}
