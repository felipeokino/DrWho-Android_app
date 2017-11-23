package v1.localhost.drwho.login;

import v1.localhost.drwho.models.Client;

/**
 * Created by felipe on 17/11/17.
 */

public class SingletonUser {

        private static SingletonUser instance = null;
        private static Client client = null;

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
}
