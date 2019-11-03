package org.zbinxp.rate;

/**
 * ProviderNotFoundException
 */
public class ProviderNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -8151771140561623769L;

    public ProviderNotFoundException() {
        super();
    }

    public ProviderNotFoundException(String msg) {
        super(msg);
    }
}