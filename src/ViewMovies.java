
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;

public class ViewMovies extends javax.swing.JInternalFrame
{
    ArrayList<Genre> al;
    MyProjectModel tm;
    public ViewMovies()
    {
        initComponents();
        cbSelectGenre();   
    }

       @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        cbselectgenre = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtviewmovies = new javax.swing.JTable();
        btedit = new javax.swing.JButton();
        btdelete = new javax.swing.JButton();
        btrefresh = new javax.swing.JButton();

        cbselectgenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        cbselectgenre.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cbselectgenreItemStateChanged(evt);
            }
        });
        cbselectgenre.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbselectgenreActionPerformed(evt);
            }
        });

        jLabel1.setText(" Select Genre");

        jtviewmovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtviewmovies);

        btedit.setText("Edit");
        btedit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bteditActionPerformed(evt);
            }
        });

        btdelete.setText("Delete");
        btdelete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btdeleteActionPerformed(evt);
            }
        });

        btrefresh.setText("Refresh");
        btrefresh.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btrefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbselectgenre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btedit, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbselectgenre, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btrefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btedit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btdelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbselectgenreItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cbselectgenreItemStateChanged
    {//GEN-HEADEREND:event_cbselectgenreItemStateChanged
       String selectgenre=cbselectgenre.getSelectedItem()+"";
       al=new ArrayList<>();
       int s=1;
        try
        {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod","root","system");
         Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         ResultSet rs=stmt.executeQuery("select * from video where genre='"+selectgenre+"'AND cname='"+"Movies"+"'");
         while(rs.next())
         {
             al.add(new Genre(s,rs.getString("cname"),rs.getString("genre"),rs.getString("producer"),rs.getString("director"),rs.getString("storyline"),rs.getString("releasedate"),rs.getString("id"),rs.getString("title"),
                     rs.getString("duration"),rs.getString("rating"),rs.getString("language"),rs.getString("coverphoto"),rs.getString("cast"),rs.getString("squarephoto"),rs.getString("videopath"),rs.getString("trailerpath")));
             s++;
         }
         
         tm=new MyProjectModel();
         jtviewmovies.setModel(tm);
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_cbselectgenreItemStateChanged

    private void bteditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bteditActionPerformed
    {//GEN-HEADEREND:event_bteditActionPerformed
       int index=jtviewmovies.getSelectedRow();
       if(index==-1)
       {
           JOptionPane.showMessageDialog(this, "Please select the Video to edit");
       }
       else if(index!=-1)
       {
           EditMovies obj=new EditMovies(al.get(index).cname,al.get(index).genre,al.get(index).producer,al.get(index).director,al.get(index).storyline,al.get(index).releasedate,al.get(index).id,al.get(index).movietitle,al.get(index).duration
                                               ,al.get(index).rating,al.get(index).language,al.get(index).coverphoto,al.get(index).cast,al.get(index).squarephoto,al.get(index).videopath,al.get(index).trailerpath);
           obj.setIconifiable(true);
           obj.setMaximizable(true);
           obj.setClosable(true);
           obj.setSize(926,674);
           this.getDesktopPane().add(obj);
           obj.moveToFront();
           obj.setVisible(true);
       }
    }//GEN-LAST:event_bteditActionPerformed

    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btdeleteActionPerformed
    {//GEN-HEADEREND:event_btdeleteActionPerformed
        int index=jtviewmovies.getSelectedRow();
        if(index==-1)
        {
            JOptionPane.showMessageDialog(this,"Please select 'Movie' first to delete");
        }
        else
        {
          String title=al.get(index).movietitle;
          
          if(JOptionPane.showConfirmDialog
                           (this,"Are you sure you want to delete the Movie '"+title+"'","Delete",JOptionPane.YES_NO_OPTION)==0)
          {
              try
              {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod","root","system");
                 Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs=stmt.executeQuery("select * from video where title='"+title+"'AND cname='"+"Movies"+"'");
                 if(rs.next())
                 {
                     rs.deleteRow();
                     al.remove(jtviewmovies.getSelectedRow());
                     tm.fireTableDataChanged();
                     JOptionPane.showMessageDialog(this, "Record Deleted");
                 }
              }
              catch (Exception e)
              {
                  e.printStackTrace();
              }
          }
        }
    }//GEN-LAST:event_btdeleteActionPerformed

    private void btrefreshActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btrefreshActionPerformed
    {//GEN-HEADEREND:event_btrefreshActionPerformed
        String selectgenre=cbselectgenre.getSelectedItem()+"";
        al.clear();
        int s=1;
        try
        {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod","root","system");
         Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         ResultSet rs=stmt.executeQuery("select * from video where genre='"+selectgenre+"'AND cname='"+"Movies"+"'");
         while(rs.next())
         {
             al.add(new Genre(s,rs.getString("cname"),rs.getString("genre"),rs.getString("producer"),rs.getString("director"),rs.getString("storyline"),rs.getString("releasedate"),rs.getString("id"),rs.getString("title"),
                     rs.getString("duration"),rs.getString("rating"),rs.getString("language"),rs.getString("coverphoto"),rs.getString("cast"),rs.getString("squarephoto"),rs.getString("videopath"),rs.getString("trailerpath")));
             s++;
         }
         
         tm=new MyProjectModel();
         jtviewmovies.setModel(tm);
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_btrefreshActionPerformed

    private void cbselectgenreActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbselectgenreActionPerformed
    {//GEN-HEADEREND:event_cbselectgenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbselectgenreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btedit;
    private javax.swing.JButton btrefresh;
    private javax.swing.JComboBox<String> cbselectgenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtviewmovies;
    // End of variables declaration//GEN-END:variables

   void cbSelectGenre()
   {
     try
        {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod","root","system");
        Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("select * from genre");
        while(rs.next())
        {
            String selectgenre=rs.getString("Genre Name");
            cbselectgenre.addItem(selectgenre);
            
        }
        }
        catch(Exception ex)
        {
            
        }   
   }
   
   
   class Genre
   {
       int s;
       
       String cname;
       String genre;
       String producer;
       String director;
       String releasedate;
       String id;
       String storyline;
       String rating;
       String coverphoto;
       String cast;
       String squarephoto;
       String movietitle;
       String videopath;
       String trailerpath;
       String duration;
       String language;
       
       Genre(int s,String cname,String genre,String producer,String director,String storyline,String releasedate,String id,String movietitle,String duration,String rating,String language,String coverphoto,String cast,String squarephoto,String videopath,String trailerpath)
       {
           this.s=s;
           this.cname=cname;
           this.genre=genre;
           this.director=director;
           this.producer=producer;
           this.releasedate=releasedate;
           this.id=id;
           this.storyline=storyline;
           this.rating=rating;
           this.squarephoto=squarephoto;
           this.cast=cast;
           this.coverphoto=coverphoto;
           this.movietitle=movietitle;
           this.videopath=videopath;
           this.trailerpath=trailerpath;
           this.duration=duration;
           this.language=language;
       }
           
    }
   
   class MyProjectModel extends AbstractTableModel
   {
       String name[]={"S.No.","Movie Title","Video Path","Duration","Language"};
       public int getRowCount()
       {
         return al.size();  
       }
       public int getColumnCount()
       {
         return 5;
       }
       public Object getValueAt(int row,int col)
       {  
          Genre g=al.get(row);
          if(col==0)
          {
             return g.s;
          }
          else if(col==1)
          {
              return g.movietitle;
          }
          else if(col==2)
          {
              return g.videopath;
          }
          else if(col==3)
          {
              return g.duration;
          }
          else
          {
              return g.language;   
          }
       }
       public String getColumnName(int col)
       {
          return name[col]; 
       }
   }
}
