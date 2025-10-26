package br.edu.ufersa.cc.lpoo.scale_flow.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyInBandException extends RuntimeException {

    public UserAlreadyInBandException(final String message) {
        super(message);
    }

    public UserAlreadyInBandException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
