package task2ANDtask3;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class EncryptionFilterOutputStream extends FilterOutputStream {
    private final int key;

    public EncryptionFilterOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b + key);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(b[off + i]);
        }
    }
}