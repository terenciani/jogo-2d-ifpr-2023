package ifpr.paranavai.jogo.controle;

import java.awt.Graphics2D;

import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.visao.FaseVisao;

public interface FaseControle {

    public void inicializaElementosGraficosAdicionais(Fase fase);

    public void inicializaInimigos(Fase fase);

    public void desenhaPontuacao(Graphics2D graficos, Fase fase);

    public void redesenhaElementosGraficos(Graphics2D graficos, Fase fase, FaseVisao faseVisao);

    public void atirar(Personagem personagem);

    public void movimentarPersonagem(Personagem personagem, int codigoDaTecla);

    public void pararPersonagem(Personagem personagem, int codigoDaTecla);

    public void atualizarPosicoesElementosGraficos(Fase fase);

    public boolean verificarColisoes(Fase fase);
}
