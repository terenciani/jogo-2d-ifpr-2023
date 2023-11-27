package ifpr.paranavai.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_inimigo")
public class Inimigo extends ElementoGrafico {

    public Inimigo() {
    }

    public Inimigo(int xAleatorio, int yAleatorio) {
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }
}
