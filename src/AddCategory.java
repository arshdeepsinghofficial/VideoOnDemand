
import java.awt.Image;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AddCategory extends javax.swing.JInternalFrame
{

    File f;
    String cname;
    String cdesc;
    String cphoto;

    public AddCategory()
    {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        cn = new javax.swing.JLabel();
        tf = new javax.swing.JTextField();
        cd = new javax.swing.JLabel();
        cp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta = new javax.swing.JTextArea();
        label = new javax.swing.JLabel();
        btbrowse = new javax.swing.JButton();
        btaddcategory = new javax.swing.JButton();

        getContentPane().setLayout(null);

        cn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cn.setText("Category Name");
        getContentPane().add(cn);
        cn.setBounds(20, 10, 110, 36);
        getContentPane().add(tf);
        tf.setBounds(154, 11, 320, 40);

        cd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cd.setText("Category Discription");
        getContentPane().add(cd);
        cd.setBounds(20, 70, 130, 40);

        cp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp.setText("Category Photo");
        getContentPane().add(cp);
        cp.setBounds(20, 180, 100, 32);

        ta.setColumns(20);
        ta.setRows(5);
        jScrollPane1.setViewportView(ta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(154, 66, 319, 96);
        getContentPane().add(label);
        label.setBounds(154, 180, 270, 150);

        btbrowse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btbrowse.setText("Browse...");
        btbrowse.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btbrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btbrowse);
        btbrowse.setBounds(20, 220, 90, 23);

        btaddcategory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btaddcategory.setText("Add Category");
        btaddcategory.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btaddcategoryActionPerformed(evt);
            }
        });
        getContentPane().add(btaddcategory);
        btaddcategory.setBounds(150, 350, 224, 31);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btaddcategoryActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btaddcategoryActionPerformed
    {//GEN-HEADEREND:event_btaddcategoryActionPerformed

        cname = tf.getText();
        cdesc = ta.getText();
        if(!label.getText().isEmpty())
        cphoto = f.getPath();
        if(cname.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill Category name");
        }else if(cdesc.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please fill Category description");
        }else if(label.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please add Category photo"); 
        }else
        {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from category where cname='" + cname + "'");
            if (rs.next())
            {
                JOptionPane.showMessageDialog(AddCategory.this, "Category already added");
                this.dispose();
            } else
            {
                rs.moveToInsertRow();
                rs.updateString("cname", cname);
                rs.updateString("cdesc", cdesc);
                rs.updateString("cphoto", cphoto);
                rs.insertRow();
                JOptionPane.showMessageDialog(AddCategory.this, "Category added successfully");
                tf.setText("");
                ta.setText("");
               
            }
        } catch (Exception e)
        {
        }
   
        }
    }//GEN-LAST:event_btaddcategoryActionPerformed

    private void btbrowseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btbrowseActionPerformed
    {//GEN-HEADEREND:event_btbrowseActionPerformed
        try
        {
            JFileChooser jfilechooser = new JFileChooser();
            int ans = jfilechooser.showOpenDialog(AddCategory.this);
            if (ans == 0)
            {
                f = jfilechooser.getSelectedFile();
                label.setText(f.getPath());
                Image img = ImageIO.read(f).getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                label.setIcon(icon);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btbrowseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddcategory;
    private javax.swing.JButton btbrowse;
    private javax.swing.JLabel cd;
    private javax.swing.JLabel cn;
    private javax.swing.JLabel cp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTextArea ta;
    private javax.swing.JTextField tf;
    // End of variables declaration//GEN-END:variables
}
