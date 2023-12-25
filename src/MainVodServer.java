
import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainVodServer implements Runnable
{

    MainVodServer()
    {
        new Thread(this).start();
    }
    ServerSocket sersock;

    public void run()
    {
        try
        {
            sersock = new ServerSocket(4200);
            System.out.println("Server Started");
            while (true)
            {
                Socket socket = sersock.accept();
                ClientHandler ch = new ClientHandler(socket);
                Thread t = new Thread(ch);
                t.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new MainVodServer();
    }

    class ClientHandler implements Runnable
    {

        Socket socket;
        DataOutputStream dos;
        DataInputStream dis;

        public ClientHandler(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            try
            {
                while(true)
                {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                String msg = dis.readLine();
             
                {
                if (msg.equals("Signup"))
                {
                    signUp();
                } else if (msg.equals("Login"))
                {
                    logIn();
                } else if (msg.equals("sendmovies"))
                {
                    sendMovies();
                } else if(msg.equals("sendgenre"))
                {
                    sendGenre();
                }
                }
                }  
            } catch (Exception e)
            {
            }
        }

        private void signUp()
        {
            try
            {
                String em = dis.readLine();
                String password = dis.readLine();
                String sec_ques = dis.readLine();
                String sec_ans = dis.readLine();
                String contact = dis.readLine();

                Class.forName("com.mysql.jdbc.Driver");
                Connection myconnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod",
                        "root", "system");
                Statement stmt = myconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select*from users where email='" + em + "' ");

                if (rs.next())
                {
                    dos.writeBytes("fail\r\n");
                } else
                {
                    rs.moveToInsertRow();
                    rs.updateString("email", em);
                    rs.updateString("password", password);
                    rs.updateString("sec_ques", sec_ques);
                    rs.updateString("sec_ans", sec_ans);
                    rs.updateString("contact", contact);
                    rs.insertRow();
                    dos.writeBytes("success\r\n");
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        void logIn()
        {
            try
            {
                String email = dis.readLine();
                String password = dis.readLine();
                Class.forName("com.mysql.jdbc.Driver");
                Connection myconnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod",
                        "root", "system");
                Statement stmt = myconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select*from users where email='" + email + "'AND password='" + password + "'");
                if (rs.next())
                {
                    dos.writeBytes("success\r\n");
                } else
                {
                    dos.writeBytes("fail\r\n");
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        void sendMovies()
        {

            try
            {

                Class.forName("com.mysql.jdbc.Driver");
                Connection myconnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod",
                        "root", "system");
                Statement stmt = myconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select*from video where cname='Movies' OR cname='TV Shows'");

                while (rs.next())
                {
                    dos.writeBytes("receive\r\n");
                    dos.writeBytes(rs.getString("cname") + "\r\n");
                    dos.writeBytes(rs.getString("genre") + "\r\n");
                    dos.writeBytes(rs.getString("producer") + "\r\n");
                    dos.writeBytes(rs.getString("director") + "\r\n");
                    dos.writeBytes(rs.getString("storyline") + "\r\n");
                    dos.writeBytes(rs.getString("releasedate") + "\r\n");
                    dos.writeBytes(rs.getString("id") + "\r\n");
                    dos.writeBytes(rs.getString("title") + "\r\n");
                    dos.writeBytes(rs.getString("duration") + "\r\n");
                    dos.writeBytes(rs.getString("rating") + "\r\n");
                    dos.writeBytes(rs.getString("language") + "\r\n");
                    dos.writeBytes(rs.getString("coverphoto") + "\r\n");
                    dos.writeBytes(rs.getString("cast") + "\r\n");
                    dos.writeBytes(rs.getString("squarephoto") + "\r\n");
                    dos.writeBytes(rs.getString("videopath") + "\r\n");
                    dos.writeBytes(rs.getString("trailerpath") + "\r\n");
                }
                dos.writeBytes("done\r\n");

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        void sendGenre()
        {
            try
            {
                dos.writeBytes("receive\r\n");
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection myconnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod",
                        "root", "system");
                Statement stmt = myconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select*from genre");
                
                while(rs.next())
                {
                    dos.writeBytes(rs.getString("Genre Name")+"\r\n");
                    
                    dos.flush();
                }
                dos.writeBytes("done\r\n");
                
            } catch (Exception e)
            {
            }
        }
    }

}
