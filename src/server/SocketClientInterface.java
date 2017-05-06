package server;

/**
 * Created by RJ Rivera on 3/10/2017.
 */
public interface SocketClientInterface
{
    boolean openConnection();
    void handleSession();
    void closeSession();
}
