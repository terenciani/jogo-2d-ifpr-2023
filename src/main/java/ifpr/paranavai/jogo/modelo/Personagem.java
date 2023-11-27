package ifpr.paranavai.jogo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_personagem")
public class Personagem extends ElementoGrafico {

    @Column(name = "deslocamento_em_x")
    private int deslocamentoEmX;

    @Column(name = "deslocamento_em_y")
    private int deslocamentoEmY;

    @Column(name = "pontuacao")
    private int pontuacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_personagem")
    private List<Tiro> tiros;

    public Personagem() {
        this.tiros = new ArrayList<>();
    }

    public Personagem(int posicaoEmX, int posicaoEmY) {
        this();
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
    }

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public List<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}