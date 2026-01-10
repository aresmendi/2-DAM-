import java.util.Observable;

public class EjercicioHilos3 {
    public static class RelojDigital extends Observable implements Runnable {

        private int horas, minutos, segundos;

        public RelojDigital(int horas, int minutos, int segundos) {
            this.horas = horas;
            this.minutos = minutos;
            this.segundos = segundos;
        }

        @Override
        public void run() {

            String tiempo;
            while (true) {
                tiempo = "";
                if (horas < 10) {
                    tiempo = "0" + horas;
                } else {
                    tiempo += horas;
                }
                tiempo += ":";
                if (minutos < 10) {
                    tiempo = "0" + minutos;
                } else {
                    tiempo += minutos;
                }
                tiempo += ":";
                if (segundos < 10) {
                    tiempo = "0" + segundos;
                } else {
                    tiempo += segundos;
                }
                this.setChanged();
                this.notifyObservers(tiempo);
                this.clearChanged();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                segundos++;
                if (segundos == 60) {
                    minutos++;
                    segundos = 0;
                }
                if (minutos == 60) {
                    minutos = 0;
                    horas++;
                }
                if (horas == 24) {
                    horas = 0;
                }
            }

        }
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmContador().setVisible(true);
            }
        });
    }

}

