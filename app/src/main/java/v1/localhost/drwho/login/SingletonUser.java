package v1.localhost.drwho.login;

import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.models.Doctor;

/**
 * Created by felipe on 17/11/17.
 */

public class SingletonUser {

        private static SingletonUser instance = null;
        private static Client client = null;
        private static boolean isClient = false;

        public static SingletonUser getInstance() {
            if (instance == null) {
                client = new Client();
                return instance = new SingletonUser();
            } else {
                return instance;
            }
        }

        public void setUsuario(Client _client) {
            SingletonUser.client = _client;
        }

        public Client getUsuario() {
            return SingletonUser.client;
        }

    public static boolean isClient() {
        return isClient;
    }

    public static void setIsClient(boolean isClient) {
        SingletonUser.isClient = isClient;
    }
}
