package savogineros.esbeu2w2d3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    /*public NotFoundException(String message) {
        super(message);                             // ma che è sta roba?
    }*/

    public NotFoundException(UUID id) {
        super(id + " non trovato!");
    }
}