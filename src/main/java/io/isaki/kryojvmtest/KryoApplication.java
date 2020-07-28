package io.isaki.kryojvmtest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;

public final class KryoApplication {

    private static final int BUFFER_SIZE = 102400;

    private final File file;

    public static void main(final String[] argv) throws Exception {
        if (argv.length != 2) {
            throw new IllegalStateException("Invalid program arguments");
        }

        final String lc = argv[0].toLowerCase(Locale.US);

        final KryoApplication app = new KryoApplication(new File(argv[1]));
        if ("serialize".equals(lc)) {
            final Payload payload = PayloadFactory.create();
            System.out.println(payload.toString());
            app.serialize(payload);
        } else if ("deserialize".equals(lc)) {
            final Object payload = app.deserialize();
            System.out.println(payload.toString());
        } else {
            throw new IllegalArgumentException(lc + " is not a supported action");
        }
    }

    private KryoApplication(final File file) {
        super();
        this.file = file;
    }

    void serialize(final Payload payload) throws FileNotFoundException, IOException {
        final Kryo kryo = createKryo();
        try (final FileOutputStream fos = new FileOutputStream(this.file);
            final BufferedOutputStream bos = new BufferedOutputStream(fos);
            final Output ostream = new Output(BUFFER_SIZE)) {
            
            ostream.setOutputStream(bos);
            
            kryo.writeClassAndObject(ostream, payload);
        }
    }

    Object deserialize() throws FileNotFoundException, IOException {
        final Kryo kryo = createKryo();
        
        final Object ret;
        try (final FileInputStream fis = new FileInputStream(this.file);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            final Input istream = new Input(BUFFER_SIZE)) {
            
            istream.setInputStream(bis);
            
            ret = kryo.readClassAndObject(istream);
        }
        
        return ret;
    }

    private static Kryo createKryo() {
        final Kryo kryo = new Kryo();
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        return kryo;
    }
}
