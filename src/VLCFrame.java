
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

public class VLCFrame extends javax.swing.JFrame {

    EmbeddedMediaPlayer mediaPlayer;
    String media;

    public VLCFrame(String media) {
        initComponents();
        setSize(620, 500);
        this.setTitle("MOVIE PLAYER");
        getContentPane().setBackground(Color.PINK);

        this.media = media;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayer.stop();
                flag = false;
            }
        });

        try {
            NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            LibXUtil.initialise();
            canvas1.setBackground(Color.black);
//
            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
            mediaPlayer
                    = mediaPlayerFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
            mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas1));
            mediaPlayer.setEnableMouseInputHandling(false);
            mediaPlayer.setEnableKeyInputHandling(true);

            mediaPlayer.prepareMedia(media);
            mediaPlayer.play();

            mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
                @Override
                public void finished(MediaPlayer mediaPlayer) {
                    jToggleButton2.setText("Play");
                    flag = false;
                    mediaPlayer.stop();
                }

                @Override
                public void error(MediaPlayer mediaPlayer) {
                    JOptionPane.showMessageDialog(rootPane, "Unable to play this video,\n"
                            + VLCFrame.this.media);
                    dispose();
                }

            });
            mediaPlayer.setVolume(2);
            new Thread(new Progress()).start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro Loading File " + e.getMessage());
//            e.printStackTrace();0
        }
        requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();
        jslider = new javax.swing.JSlider();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jToggleButton2 = new javax.swing.JToggleButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setLayout(null);

        canvas1.setBackground(new java.awt.Color(51, 0, 51));
        jPanel1.add(canvas1);
        canvas1.setBounds(0, 0, 620, 250);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 620, 250);

        jslider.setToolTipText("Inrease/Decrease Volume");
        jslider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsliderStateChanged(evt);
            }
        });
        getContentPane().add(jslider);
        jslider.setBounds(300, 300, 320, 20);

        jToggleButton1.setText("Mute");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1);
        jToggleButton1.setBounds(10, 290, 90, 30);

        jLabel1.setText("Volume");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(220, 300, 70, 20);

        jProgressBar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jProgressBar1MouseDragged(evt);
            }
        });
        jProgressBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar1MouseClicked(evt);
            }
        });
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(0, 260, 620, 20);

        jToggleButton2.setText("Pause");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton2);
        jToggleButton2.setBounds(100, 290, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jsliderStateChanged
    {//GEN-HEADEREND:event_jsliderStateChanged
        int n = jslider.getValue();
        mediaPlayer.setVolume(n);
//        jLabel4.setText(jSlider1.getValue() + "%");
    }//GEN-LAST:event_jsliderStateChanged

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jToggleButton1ActionPerformed
    {//GEN-HEADEREND:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            mediaPlayer.mute(true);
            jToggleButton1.setText("Unmute");
        } else {
            mediaPlayer.mute(false);
            jToggleButton1.setText("Mute");
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jProgressBar1MouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jProgressBar1MouseDragged
    {//GEN-HEADEREND:event_jProgressBar1MouseDragged
        // TODO add your handling code here:
        move(evt);
    }//GEN-LAST:event_jProgressBar1MouseDragged

    private void jProgressBar1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jProgressBar1MouseClicked
    {//GEN-HEADEREND:event_jProgressBar1MouseClicked
        move(evt);
    }//GEN-LAST:event_jProgressBar1MouseClicked

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jToggleButton2ActionPerformed
    {//GEN-HEADEREND:event_jToggleButton2ActionPerformed
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            jToggleButton2.setText("Play");
        } else {
            if (flag == false) {
                mediaPlayer.start();
                flag = true;
                new Thread(new Progress()).start();
            } else {
                mediaPlayer.play();
            }
            jToggleButton2.setText("Pause");
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    public void move(java.awt.event.MouseEvent event) {
        int size = jProgressBar1.getWidth();
        int x = event.getX();
        int per = (int) ((x * 100) / size);
        jProgressBar1.setValue(per);
        float p = per / 100.0f;
        mediaPlayer.setPosition(p);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VLCFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VLCFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VLCFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VLCFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VLCFrame("F:\\Movies\\Iron Man (2008)  Worldfree4u.trade  1080p BRRip  Dual Audio   English-Hindi.mkv").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JSlider jslider;
    // End of variables declaration//GEN-END:variables

    boolean flag = true;

    class Progress implements Runnable {

        Thread t;

        Progress() {
            t = new Thread(this);
            t.start();
        }

        public void run() {
            try {
                while (flag) {
                    jProgressBar1.setValue((int) (mediaPlayer.getPosition() * 100) + 1);
                    jLabel1.setText((mediaPlayer.getTime() / 1000) / 60 + ":" + (mediaPlayer.getTime() / 1000) % 60
                            + "/" + (mediaPlayer.getLength() / 1000) / 60 + ":" + (mediaPlayer.getLength() / 1000) % 60);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
