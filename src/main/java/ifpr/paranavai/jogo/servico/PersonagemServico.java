package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiro;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PersonagemServico {

    private static final int DESLOCAMENTO = 3;

    public void atirar(Personagem personagem) {
        TiroServico tiroServico = new TiroServico();
        int frenteDaNave = personagem.getPosicaoEmX() + personagem.getLarguraImagem();
        int meioDaNave = personagem.getPosicaoEmY() + (personagem.getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        tiroServico.carregarImagem(tiro);
        personagem.getTiros().add(tiro);
    }

    public void atualizarPosicao(Personagem personagem) {
        personagem.setPosicaoEmX(personagem.getPosicaoEmX() + personagem.getDeslocamentoEmX());
        personagem.setPosicaoEmY(personagem.getPosicaoEmY() + personagem.getDeslocamentoEmY());
    }

    public void carregarImagem(Personagem personagem) {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/espaconave.png"));
        personagem.setImagem(carregando.getImage());
        personagem.setLarguraImagem(carregando.getImage().getWidth(null));
        personagem.setAlturaImagem(carregando.getImage().getHeight(null));
    }

    public void mover(Personagem personagem, int codigoDaTecla) {
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

    public void parar(Personagem personagem, int codigoDaTecla) {
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

}
