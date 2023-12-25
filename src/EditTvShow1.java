
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EditTvShow1 extends javax.swing.JInternalFrame {

    JFileChooser ch;
    File f;
    Video v;

    EditTvShow1(Video video) {

        initComponents();
        v = video;
        ch = new JFileChooser();
        new Thread(new Runnable() {
            @Override
            public void run() {
                fillCB();
                cbgenre.setSelectedItem(video.genre);
                tfvideotitle.setText(video.Movietitle);
                String[] split = video.duration.split(":");
                tfhour.setText(split[0]);
                tfminute.setText(split[1]);
                tfproducer.setText(video.producer);
                tfdirector.setText(video.director);
                tastoryline.setText(video.story_line);
                cbgenre.setSelectedItem(video.language);
                cbrating.setSelectedItem(video.rating);
                try {
                    Image obj = ImageIO.read(new File(video.sphoto)).getScaledInstance(lbsquarephoto.getWidth(),
                            lbsquarephoto.getHeight(), Image.SCALE_SMOOTH);
                    lbsquarephoto.setIcon(new ImageIcon(obj));
                    Image obj1 = ImageIO.read(new File(video.cphoto)).getScaledInstance(lbcoverphoto.getWidth(),
                            lbcoverphoto.getHeight(), Image.SCALE_SMOOTH);
                    lbcoverphoto.setIcon(new ImageIcon(obj1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void fillCB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "system");
            Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            cbgenre.addItem("-Select Genre-");
            ResultSet rs1 = stmt.executeQuery("select * from genre");
            while (rs1.next()) {
                cbgenre.addItem(rs1.getString("genrename"));
                System.out.println("k");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        lbgenre = new javax.swing.JLabel();
        lbproducer = new javax.swing.JLabel();
        lbdirector = new javax.swing.JLabel();
        tfproducer = new javax.swing.JTextField();
        lbstoryline = new javax.swing.JLabel();
        tfdirector = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tastoryline = new javax.swing.JTextArea();
        tfvideotitle = new javax.swing.JTextField();
        lbvideotitle = new javax.swing.JLabel();
        cbgenre = new javax.swing.JComboBox<>();
        lbduration = new javax.swing.JLabel();
        lbhour = new javax.swing.JLabel();
        tfhour = new javax.swing.JTextField();
        lbminute = new javax.swing.JLabel();
        tfminute = new javax.swing.JTextField();
        lbrating = new javax.swing.JLabel();
        cbrating = new javax.swing.JComboBox<>();
        lblanguage = new javax.swing.JLabel();
        cblanguage = new javax.swing.JComboBox<>();
        lbcoverphoto = new javax.swing.JLabel();
        btbrowsecover = new javax.swing.JButton();
        lbcover = new javax.swing.JLabel();
        lbsquarephoto = new javax.swing.JLabel();
        btbrowsesquare = new javax.swing.JButton();
        lbsquare = new javax.swing.JLabel();
        btadd = new javax.swing.JButton();

        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        lbgenre.setText("Genre");
        jPanel1.add(lbgenre);
        lbgenre.setBounds(20, 64, 80, 20);

        lbproducer.setText("Producer");
        jPanel1.add(lbproducer);
        lbproducer.setBounds(20, 210, 70, 20);

        lbdirector.setText("Director");
        jPanel1.add(lbdirector);
        lbdirector.setBounds(20, 270, 50, 20);
        jPanel1.add(tfproducer);
        tfproducer.setBounds(160, 200, 140, 33);

        lbstoryline.setText("Story  Line");
        jPanel1.add(lbstoryline);
        lbstoryline.setBounds(20, 340, 70, 30);
        jPanel1.add(tfdirector);
        tfdirector.setBounds(160, 270, 140, 30);

        tastoryline.setColumns(20);
        tastoryline.setRows(5);
        jScrollPane1.setViewportView(tastoryline);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(160, 330, 140, 96);
        jPanel1.add(tfvideotitle);
        tfvideotitle.setBounds(160, 90, 110, 20);

        lbvideotitle.setText("Video  Title");
        jPanel1.add(lbvideotitle);
        lbvideotitle.setBounds(20, 90, 52, 14);
        jPanel1.add(cbgenre);
        cbgenre.setBounds(160, 60, 130, 20);

        lbduration.setText("Duration");
        jPanel1.add(lbduration);
        lbduration.setBounds(20, 120, 60, 20);

        lbhour.setText("HH");
        jPanel1.add(lbhour);
        lbhour.setBounds(120, 130, 30, 20);
        jPanel1.add(tfhour);
        tfhour.setBounds(170, 130, 40, 20);

        lbminute.setText(": MM");
        jPanel1.add(lbminute);
        lbminute.setBounds(220, 130, 30, 20);
        jPanel1.add(tfminute);
        tfminute.setBounds(260, 130, 40, 20);

        lbrating.setText("Rating");
        jPanel1.add(lbrating);
        lbrating.setBounds(400, 70, 70, 20);

        cbrating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5 (Excellent)", "4(V.Good)",
            "3(good)", "2(average)","1(poor)" }));
jPanel1.add(cbrating);
cbrating.setBounds(520, 80, 130, 20);

lblanguage.setText("Language");
jPanel1.add(lblanguage);
lblanguage.setBounds(400, 30, 70, 30);

cblanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Hindi", "Punjabi" }));
jPanel1.add(cblanguage);
cblanguage.setBounds(510, 30, 150, 20);

lbcoverphoto.setText("Cover Photo");
jPanel1.add(lbcoverphoto);
lbcoverphoto.setBounds(390, 110, 70, 20);

btbrowsecover.setText("Browse..");
btbrowsecover.addActionListener(new java.awt.event.ActionListener()
{
    public void actionPerformed(java.awt.event.ActionEvent evt)
    {
        btbrowsecoverActionPerformed(evt);
    }
    });
    jPanel1.add(btbrowsecover);
    btbrowsecover.setBounds(390, 140, 90, 23);

    lbcover.setText("Cover photo label");
    jPanel1.add(lbcover);
    lbcover.setBounds(500, 110, 120, 60);

    lbsquarephoto.setText("Square Photo");
    jPanel1.add(lbsquarephoto);
    lbsquarephoto.setBounds(390, 180, 80, 30);

    btbrowsesquare.setText("Browse...");
    btbrowsesquare.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            btbrowsesquareActionPerformed(evt);
        }
    });
    jPanel1.add(btbrowsesquare);
    btbrowsesquare.setBounds(390, 220, 90, 23);

    lbsquare.setText("Square Photo Label");
    jPanel1.add(lbsquare);
    lbsquare.setBounds(510, 180, 110, 80);

    btadd.setText("Update Tv Show");
    btadd.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            btaddActionPerformed(evt);
        }
    });
    jPanel1.add(btadd);
    btadd.setBounds(380, 460, 330, 40);

    getContentPane().add(jPanel1);
    jPanel1.setBounds(30, 10, 730, 520);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btbrowsecoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowsecoverActionPerformed
        int r = ch.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == r) {
            f = ch.getSelectedFile();
            coverphoto = f.getPath();
            try {
                Image obj = ImageIO.read(f).getScaledInstance(lbcoverphoto.getWidth(),
                        lbcoverphoto.getHeight(), Image.SCALE_SMOOTH);
                lbcoverphoto.setIcon(new ImageIcon(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btbrowsecoverActionPerformed
    String videopath = "";
    String trailerpath = "";
    String squarephoto = "";
    String coverphoto = "";
    private void btbrowsesquareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowsesquareActionPerformed
        // TODO add your handling code here:
        int r = ch.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == r) {
            f = ch.getSelectedFile();
            squarephoto = f.getPath();
            try {
                Image obj = ImageIO.read(f).getScaledInstance(lbsquarephoto.getWidth(),
                        lbsquarephoto.getHeight(), Image.SCALE_SMOOTH);
                lbsquarephoto.setIcon(new ImageIcon(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btbrowsesquareActionPerformed

    private void btaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddActionPerformed
        int indexofGenre = cbgenre.getSelectedIndex();
        if (indexofGenre == 0) {
            JOptionPane.showMessageDialog(this, "Please select a Genre First!!");
            return;
        }
        String vtitle = tfvideotitle.getText();
        if (vtitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a video title!!");
            return;
        }

        String producer = tfproducer.getText();
        if (producer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a Movie Producer!!");
            return;
        }
        String director = tfdirector.getText();
        if (director.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a Movie Director!!");
            return;
        }

        String storyline = tastoryline.getText();
        if (storyline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a Movie Story Line!!");
            return;
        }

        String language = cblanguage.getSelectedItem().toString();
        String rating = cbrating.getSelectedItem().toString();
        if (coverphoto.isEmpty()) {
            coverphoto = v.cphoto;
        }
        if (squarephoto.isEmpty()) {
            squarephoto = v.sphoto;
        }
        String genre = cbgenre.getSelectedItem().toString();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "system");
            Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from videos where id='" + v.id + "'");
            rs.next();
            rs.updateString("cover_photo", coverphoto);
            rs.updateString("square_photo", squarephoto);
            rs.updateString("producer", producer);
            rs.updateString("director", director);
            rs.updateString("story_line", storyline);
            rs.updateString("genre", genre);
            rs.updateString("video_path", videopath);
            rs.updateString("trailer_path", trailerpath);
            rs.updateString("title", vtitle);
            rs.updateString("rating", rating);
            rs.updateString("language", language);
            rs.updateRow();
            JOptionPane.showMessageDialog(rootPane, "Updated successfully");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btaddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btadd;
    private javax.swing.JButton btbrowsecover;
    private javax.swing.JButton btbrowsesquare;
    private javax.swing.JComboBox<String> cbgenre;
    private javax.swing.JComboBox<String> cblanguage;
    private javax.swing.JComboBox<String> cbrating;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbcover;
    private javax.swing.JLabel lbcoverphoto;
    private javax.swing.JLabel lbdirector;
    private javax.swing.JLabel lbduration;
    private javax.swing.JLabel lbgenre;
    private javax.swing.JLabel lbhour;
    private javax.swing.JLabel lblanguage;
    private javax.swing.JLabel lbminute;
    private javax.swing.JLabel lbproducer;
    private javax.swing.JLabel lbrating;
    private javax.swing.JLabel lbsquare;
    private javax.swing.JLabel lbsquarephoto;
    private javax.swing.JLabel lbstoryline;
    private javax.swing.JLabel lbvideotitle;
    private javax.swing.JTextArea tastoryline;
    private javax.swing.JTextField tfdirector;
    private javax.swing.JTextField tfhour;
    private javax.swing.JTextField tfminute;
    private javax.swing.JTextField tfproducer;
    private javax.swing.JTextField tfvideotitle;
    // End of variables declaration//GEN-END:variables
}
