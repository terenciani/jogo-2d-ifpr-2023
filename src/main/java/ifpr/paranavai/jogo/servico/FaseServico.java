package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.dao.FaseDao;
import ifpr.paranavai.jogo.modelo.Fase;

public class FaseServico {
    
    private FaseDao faseDao;
    
    public FaseServico(FaseDao faseDao) {
        this.faseDao = faseDao;
    }
    
    public Fase buscarUltimoJogoSalvo() {
        return faseDao.buscarUltimaFaseSalva();
    }
    
    public void criarPontoSalvamento(Fase fase) {
        //faseDao.excluirTodosOsPontosSalvamento();
        if (fase.getIdFase() != null && fase.getIdFase() > 0) {
            faseDao.atualizar(fase);
        } else {
            faseDao.inserir(fase);
        }
    }
    
}
