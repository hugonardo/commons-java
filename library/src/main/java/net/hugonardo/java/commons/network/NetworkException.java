package net.hugonardo.java.commons.network;

import java.io.IOException;

public class NetworkException extends IOException {

    public NetworkException() {
        super("Problemas com a conexao de rede");
    }
}
