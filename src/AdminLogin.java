
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AdminLogin extends javax.swing.JFrame
{

    
    public AdminLogin()
    {
        initComponents();
        this.setSize(647,388);
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        tfemail = new javax.swing.JTextField();
        lbemail = new javax.swing.JLabel();
        lbpassword = new javax.swing.JLabel();
        btlogin = new javax.swing.JButton();
        tfpassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        tfemail.setText("admin@gmail.com");
        getContentPane().add(tfemail);
        tfemail.setBounds(170, 60, 430, 49);

        lbemail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbemail.setText("E-mail");
        getContentPane().add(lbemail);
        lbemail.setBounds(70, 60, 70, 49);

        lbpassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbpassword.setText("Password");
        getContentPane().add(lbpassword);
        lbpassword.setBounds(70, 130, 70, 47);

        btlogin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btlogin.setText("Login");
        btlogin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btloginActionPerformed(evt);
            }
        });
        getContentPane().add(btlogin);
        btlogin.setBounds(170, 200, 85, 23);

        tfpassword.setText("123");
        getContentPane().add(tfpassword);
        tfpassword.setBounds(170, 130, 430, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btloginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btloginActionPerformed
    {//GEN-HEADEREND:event_btloginActionPerformed
         String email=tfemail.getText();
         String password=tfpassword.getText();
        if(email.isEmpty()||password.isEmpty())
        {
            JOptionPane.showMessageDialog(AdminLogin.this, "Please enter Email and Password properly.");
        }
        else if(!(email.endsWith("mail.com")))
        {
             JOptionPane.showMessageDialog(AdminLogin.this, "Please enter Email and Password properly.");
        }
        else
        {
            
            try
            {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from admin where email='"+email+"'and password='"+password+"'");
            if(rs.next())
            {
                AdminHome obj=new AdminHome(email);
                obj.setVisible(true);
                 tfemail.setText("");
                tfpassword.setText("");
                this.dispose();
                //JOptionPane.showMessageDialog(AdminLogin.this, "Login Successfull");   
            }
            else
            {
                 JOptionPane.showMessageDialog(AdminLogin.this, "Please enter Email and Password properly.");
            }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btloginActionPerformed

    
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AdminLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlogin;
    private javax.swing.JLabel lbemail;
    private javax.swing.JLabel lbpassword;
    private javax.swing.JTextField tfemail;
    private javax.swing.JPasswordField tfpassword;
    // End of variables declaration//GEN-END:variables
}
