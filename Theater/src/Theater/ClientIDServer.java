package Theater;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * ClientIDServer class serves as a way of getting a new, unique ID for client creation.
 * Will be saved along with the Theater class when the program is shutdown.
 * @author David Jaqua
 */
public class ClientIDServer implements Serializable{

    private static final long serialVersionUID = 1L;
    /*
     * Singleton instance of the ClientIDServer
     */
    private static ClientIDServer serverInstance;
    private int idCounter;

    
    // Creates singleton instance of ClientIDServer
    private ClientIDServer() {
            idCounter = 1;
    }

    /**
     * Retrieves the singleton instance of ClientIDServer
     * @return ClientIDServer instance
     */
    public static ClientIDServer instance(){
            if (serverInstance == null){
                    return (serverInstance = new ClientIDServer());
            } else{
                    return serverInstance;
            }
    }
    
    /**
    * Retrieves the server object
    * @param input input stream for deserialization.
    */
    public static void retrieve(ObjectInputStream input) {
        try {
            serverInstance = (ClientIDServer) input.readObject();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Gets a new, unique ID for a client
     * @return Unique client ID
     */
    public int getID(){
            return idCounter++; // returns current unique id, then increments it for the next call
    }
}
