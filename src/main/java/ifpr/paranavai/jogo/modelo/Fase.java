package ifpr.paranavai.jogo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fase")
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fase")
    private Integer idElementoGrafico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_personagem")
    private Personagem personagem;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fase")
    private List<Inimigo> inimigos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fase")
    private List<Asteroide> asteroides;

    public Fase() {
        this.personagem = new Personagem();
        this.inimigos = new ArrayList<Inimigo>();
        this.asteroides = new ArrayList<Asteroide>();
    }

    public Integer getIdElementoGrafico() {
        return this.idElementoGrafico;
    }

    public void setIdElementoGrafico(Integer idElementoGrafico) {
        this.idElementoGrafico = idElementoGrafico;
    }

    public Personagem getPersonagem() {
        return this.personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public List<Inimigo> getInimigos() {
        return this.inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public List<Asteroide> getAsteroides() {
        return this.asteroides;
    }

    public void setAsteroides(List<Asteroide> asteroides) {
        this.asteroides = asteroides;
    }

}
