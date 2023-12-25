
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class ChangePassword extends javax.swing.JInternalFrame
{

    /**
     * Creates new form JInternalFrame
     */
    String email;
    public ChangePassword(String email)
    {
        initComponents();
        this.email=email;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        op = new javax.swing.JLabel();
        np = new javax.swing.JLabel();
        cp = new javax.swing.JLabel();
        btchangepassword = new javax.swing.JButton();
        pfop = new javax.swing.JPasswordField();
        pfnp = new javax.swing.JPasswordField();
        pfcp = new javax.swing.JPasswordField();

        getContentPane().setLayout(null);

        op.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        op.setText("Old Password");
        getContentPane().add(op);
        op.setBounds(50, 30, 95, 47);

        np.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        np.setText("New Password");
        getContentPane().add(np);
        np.setBounds(50, 100, 95, 47);

        cp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cp.setText("Confirm Password");
        getContentPane().add(cp);
        cp.setBounds(50, 160, 95, 48);

        btchangepassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btchangepassword.setText("Change Password");
        btchangepassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btchangepasswordActionPerformed(evt);
            }
        });
        getContentPane().add(btchangepassword);
        btchangepassword.setBounds(150, 230, 150, 31);
        getContentPane().add(pfop);
        pfop.setBounds(150, 30, 426, 47);
        getContentPane().add(pfnp);
        pfnp.setBounds(150, 100, 426, 47);
        getContentPane().add(pfcp);
        pfcp.setBounds(150, 160, 426, 48);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btchangepasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btchangepasswordActionPerformed
    {//GEN-HEADEREND:event_btchangepasswordActionPerformed
      String op=pfop.getText();
      String np=pfnp.getText();
      String cp=pfcp.getText();
      if(op.isEmpty()||np.isEmpty()||cp.isEmpty())
      {
           JOptionPane.showMessageDialog(ChangePassword.this, "Please enter values properly.");
      }
      else if(!np.equals(cp))
      {
           JOptionPane.showMessageDialog(ChangePassword.this, "New password and Confirm Password need to be same.");
      }
      else
      {
          try
          {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from admin where email='"+email+"'and password='"+op+"'");
            if(rs.next())
            {
             rs.updateString("password",np);
             rs.updateRow();
             JOptionPane.showMessageDialog(ChangePassword.this, "Password Changed Successfully.");
             this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(ChangePassword.this, "Invalid Old Password.");
            }
          }
          catch(Exception ex)
          {
              
          }
      }
                   
    }//GEN-LAST:event_btchangepasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btchangepassword;
    private javax.swing.JLabel cp;
    private javax.swing.JLabel np;
    private javax.swing.JLabel op;
    private javax.swing.JPasswordField pfcp;
    private javax.swing.JPasswordField pfnp;
    private javax.swing.JPasswordField pfop;
    // End of variables declaration//GEN-END:variables
}
