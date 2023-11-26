package ifpr.paranavai.jogo.controle;

import java.awt.Graphics2D;

import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.visao.PrincipalVisao;
import ifpr.paranavai.jogo.visao.FaseVisao;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

public class FaseUmControle implements FaseControle {

    private static final int DESLOCAMENTO = 3;
    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int QTDE_DE_ASTEROIDES = 50;
    private static final int QTDE_DE_INIMIGOS = 40;

    @Override
    public void inicializaElementosGraficosAdicionais(Fase fase) {
        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            fase.getAsteroides().add(asteroide);
        }
    }

    @Override
    public void inicializaInimigos(Fase fase) {
        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            fase.getInimigos().add(inimigo);
        }
    }

    @Override
    public void desenhaPontuacao(Graphics2D graficos, Fase fase) {
        String textoPontuacao = "PONTOS: " + fase.getPersonagem().getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }

    @Override
    public void redesenhaElementosGraficos(Graphics2D graficos, Fase fase, FaseVisao faseVisao) {
        Personagem personagem = fase.getPersonagem();
        for (Asteroide asteroide : fase.getAsteroides()) {
            graficos.drawImage(asteroide.getImagem(), asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), faseVisao);
        }
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), faseVisao);
        List<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), faseVisao);
        }
        for (Inimigo inimigo : fase.getInimigos()) {
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), faseVisao);
        }
    }

    public void atirar(Personagem personagem) {
        int frenteDaNave = personagem.getPosicaoEmX() + personagem.getLarguraImagem();
        int meioDaNave = personagem.getPosicaoEmY() + (personagem.getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        personagem.getTiros().add(tiro);
    }

    @Override
    public void movimentarPersonagem(Personagem personagem, int codigoDaTecla) {
        switch (codigoDaTecla) {
            case KeyEvent.VK_UP:
                personagem.setDeslocamentoEmY(-DESLOCAMENTO);
                break;
            case KeyEvent.VK_DOWN:
                personagem.setDeslocamentoEmY(DESLOCAMENTO);
                break;
            case KeyEvent.VK_LEFT:
                personagem.setDeslocamentoEmX(-DESLOCAMENTO);
                break;
            case KeyEvent.VK_RIGHT:
                personagem.setDeslocamentoEmX(DESLOCAMENTO);
                break;
            default:
                break;
        }
    }

    @Override
    public void pararPersonagem(Personagem personagem, int codigoDaTecla) {
        switch (codigoDaTecla) {
            case KeyEvent.VK_UP:
                personagem.setDeslocamentoEmY(0);
                break;
            case KeyEvent.VK_DOWN:
                personagem.setDeslocamentoEmY(0);
                break;
            case KeyEvent.VK_LEFT:
                personagem.setDeslocamentoEmX(0);
                break;
            case KeyEvent.VK_RIGHT:
                personagem.setDeslocamentoEmX(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void atualizarPosicoesElementosGraficos(Fase fase) {
        fase.getPersonagem().atualizar();
        for (Asteroide asteroide : fase.getAsteroides()) {
            asteroide.atualizar();
        }

        List<Tiro> tiros = fase.getPersonagem().getTiros();
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            if (tiro.getPosicaoEmX() > PrincipalVisao.LARGURA_DA_JANELA || !tiro.getEhVisivel()) {
                tiros.remove(tiro);
            } else {
                tiro.atualizar();
            }
        }

        List<Inimigo> inimigos = fase.getInimigos();
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel()) {
                inimigos.remove(inimigo);
            } else {
                inimigo.atualizar();
            }
        }
    }

    @Override
    public boolean verificarColisoes(Fase fase) {
        Rectangle formaPersonagem = fase.getPersonagem().getRectangle();

        for (int i = 0; i < fase.getInimigos().size(); i++) {
            Inimigo inimigo = fase.getInimigos().get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                fase.getPersonagem().setEhVisivel(false);
                inimigo.setEhVisivel(false);
                return false;
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
        return true;
    }
}
