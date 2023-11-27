package ifpr.paranavai.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_asteroide")
public class Asteroide extends ElementoGrafico {

    public Asteroide() {
    }

    public Asteroide(int xAleatorio, int yAleatorio) {
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }

}
