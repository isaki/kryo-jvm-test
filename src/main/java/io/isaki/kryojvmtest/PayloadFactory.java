package io.isaki.kryojvmtest;


public final class PayloadFactory {
    
    //private static final String ID = "stringid";
    private static final Long ID = Long.valueOf(19000L);
    //private static final long ID = 18000L;

    public static Payload create() {
        return new Payload(ID);
    }
    
    private PayloadFactory() {
        // STATIC CLASS
    }
}
