
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.*;
import java.sql.DriverManager;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EditCategory extends javax.swing.JInternalFrame
{

    String cname;
    String cdesc;
    String cphoto;
    File f;

    public EditCategory(String cname, String cdesc, String cphoto)
    {
        initComponents();
        tf.setText(cname);
        tf.setEnabled(false);
        ta.setText(cdesc);
        label.setText(cphoto);
        f=new File(label.getText());
        try
        {
            Image img = ImageIO.read(f).getScaledInstance(label.getWidth(), label.getHeight(),
                        Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                label.setIcon(icon);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        tf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta = new javax.swing.JTextArea();
        label = new javax.swing.JLabel();
        btupdate = new javax.swing.JButton();
        cn = new javax.swing.JLabel();
        cd = new javax.swing.JLabel();
        cp = new javax.swing.JLabel();
        btbrowse = new javax.swing.JButton();

        getContentPane().setLayout(null);

        jInternalFrame1.getContentPane().setLayout(null);
        getContentPane().add(jInternalFrame1);
        jInternalFrame1.setBounds(235, 313, 0, 0);
        getContentPane().add(tf);
        tf.setBounds(141, 11, 318, 41);

        ta.setColumns(20);
        ta.setRows(5);
        jScrollPane1.setViewportView(ta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(141, 70, 318, 96);
        getContentPane().add(label);
        label.setBounds(141, 184, 280, 140);

        btupdate.setText("Update");
        btupdate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btupdateActionPerformed(evt);
            }
        });
        getContentPane().add(btupdate);
        btupdate.setBounds(141, 343, 230, 34);

        cn.setText("Category Name");
        getContentPane().add(cn);
        cn.setBounds(22, 11, 101, 41);

        cd.setText("New  Description");
        getContentPane().add(cd);
        cd.setBounds(20, 70, 101, 80);

        cp.setText("Category Photo");
        getContentPane().add(cp);
        cp.setBounds(22, 184, 101, 30);

        btbrowse.setText("Browse...");
        btbrowse.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btbrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btbrowse);
        btbrowse.setBounds(22, 220, 88, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btbrowseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btbrowseActionPerformed
    {//GEN-HEADEREND:event_btbrowseActionPerformed
        try
        {
            JFileChooser jfilechooser = new JFileChooser();
            int ans = jfilechooser.showOpenDialog(EditCategory.this);
            if (ans == 0)
            {
                f = jfilechooser.getSelectedFile();
                label.setText(f.getPath());
                Image img = ImageIO.read(f).getScaledInstance(label.getWidth(), label.getHeight(),
                        Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                label.setIcon(icon);
            }
        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }//GEN-LAST:event_btbrowseActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btupdateActionPerformed
    {//GEN-HEADEREND:event_btupdateActionPerformed
        cname = tf.getText();
      
        cdesc = ta.getText();
        cphoto = label.getText();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from category where cname='" + cname + "'");
            if (rs.next())
            {
                
                rs.updateString("cdesc", cdesc);
                if (f != null)
                {
                    rs.updateString("cphoto", cphoto);
                }
                rs.updateRow();
                JOptionPane.showMessageDialog(this, cname + " category Updated Successfully!!");
                dispose();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btupdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbrowse;
    private javax.swing.JButton btupdate;
    private javax.swing.JLabel cd;
    private javax.swing.JLabel cn;
    private javax.swing.JLabel cp;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTextArea ta;
    private javax.swing.JTextField tf;
    // End of variables declaration//GEN-END:variables

    class Category
    {

        int s;
        String cname;
        String cdesc;
        String cphoto;

        Category(int s, String cname, String cdesc, String cphoto)
        {
            this.s = s;
            this.cname = cname;
            this.cdesc = cdesc;
            this.cphoto = cphoto;
        }
    }
}
