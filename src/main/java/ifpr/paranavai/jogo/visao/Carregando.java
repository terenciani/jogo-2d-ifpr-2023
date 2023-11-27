package ifpr.paranavai.jogo.visao;

/**
 *
 * @author Ifpr
 */
public class Carregando extends javax.swing.JFrame {

    /**
     * Creates new form Carregando
     *
     * @param parent
     */
    public Carregando(java.awt.Component parent) {
        this.setLocationRelativeTo(parent);
        initComponents();
    }

    public Carregando() {
        this.setLocationRelativeTo(null);
        initComponents();
        setBackground(new java.awt.Color(0.0f, 0.0f, 0.0f, 0.0f));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gifCarregando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(100, 100));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(100, 100));
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        gifCarregando.setBackground(new java.awt.Color(0.0f, 0.0f, 0.0f, 0.0f));
        gifCarregando.setForeground(new java.awt.Color(255, 255, 255));
        gifCarregando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gifCarregando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loading100px.gif"))); // NOI18N
        gifCarregando.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gifCarregando.setOpaque(true);
        getContentPane().add(gifCarregando);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gifCarregando;
    // End of variables declaration//GEN-END:variables
}
