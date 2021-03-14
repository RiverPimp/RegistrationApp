
package registrationapp;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Server extends UnicastRemoteObject implements ConnectInterface{
    
    public Server() throws RemoteException{
        super();
    }
    
    public static void main(String[] args)throws RemoteException{
        Registry reg = LocateRegistry.createRegistry(4444);
        Server s = new Server();
        reg.rebind("db", s);
        System.out.println("Server is ready");
    }

    @Override
    public String Insert(int ID, String name, String surname, int age, int cellNo, String degree) throws RemoteException {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmiTest","root","root");
            Statement st = con.createStatement();

            String sql = "insert into students values('"+ID+"','"+name+"','"+surname+"','"+age+"','"+cellNo+"','"+degree+"')";
            st.executeUpdate(sql);
            return "Record Inserted Succesfully";
            
        }catch(Exception e){
            return (e.toString());
        }
        
    }
    
}
