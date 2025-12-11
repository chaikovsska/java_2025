package task2ANDtask3;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class DecryptionFilterInputStream extends FilterInputStream {
    private final int key;

    public DecryptionFilterInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b == -1) {
            return -1;
        }
        return b - key;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytesRead = super.read(b, off, len);
        if (bytesRead == -1) return -1;

        for (int i = off; i < off + bytesRead; i++) {
            b[i] = (byte) (b[i] - key);
        }
        return bytesRead;
    }
}
