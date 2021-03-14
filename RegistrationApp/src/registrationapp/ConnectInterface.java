
package registrationapp;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ConnectInterface extends Remote{
    
    public String Insert (int ID, String name, String surname, int age, int cellNo, String degree) throws RemoteException;
    
}
