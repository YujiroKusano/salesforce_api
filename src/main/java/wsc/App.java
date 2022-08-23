package wsc;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

// import com.
public class App {
    static final String USERNAME = "yujiro.kusano.1106@developer.salesforce";
    static final String PASSWORD = "sigusigu14";

    public static void main(String[] args) {
        loginCheck();
    }

    public static void loginCheck() {
        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        Connector connector = new Connector();
        try {
            PartnerConnection pc = connector.newConnection(config);
            String fullName = pc.getUserInfo().getUserFullName();
            System.out.println(fullName);
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
    
}
