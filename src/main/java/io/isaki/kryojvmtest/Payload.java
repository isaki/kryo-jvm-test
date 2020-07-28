package io.isaki.kryojvmtest;


public final class Payload {

    //private final String id;
    private final Long id;
    //private final long id;

    @SuppressWarnings("unused")
    private Payload() {
        super();
        this.id = null;
        //this.id = 0L;
    }
    
    //public Payload(final String id) {
    public Payload(final Long id) {
    //public Payload(final long id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString()
            + " ("
            //+ this.id
            + this.id.toString()
            //+ Long.toString(this.id)
            + ")";
    }
}
