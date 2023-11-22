package ifpr.paranavai.jogo.controle;

import java.awt.Graphics2D;

import ifpr.paranavai.jogo.modelo.Asteroide;
import ifpr.paranavai.jogo.modelo.Fase;
import ifpr.paranavai.jogo.principal.PrincipalVisao;

public class FaseUmControle {
    private static final int QTDE_DE_ASTEROIDES = 50;

    public void inicializaElementosGraficosAdicionais(Fase fase) {

        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * PrincipalVisao.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * PrincipalVisao.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            fase.getAsteroides().add(asteroide);
        }
    }

    public void desenhaPontuacao(Graphics2D graficos, Fase fase) {
        String textoPontuacao = "PONTOS: " + fase.getPersonagem().getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }
}
