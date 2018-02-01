package cn.cd.caoyeung.webapp.utils.lang;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;

public class ObjZipBufInputStream  extends ObjectInputStream{
    public ObjZipBufInputStream(byte[] buf) throws IOException {
        super(new GZIPInputStream(new ByteArrayInputStream(buf)));
    }
}
