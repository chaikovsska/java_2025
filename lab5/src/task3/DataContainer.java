package task3;

import java.io.*;

class DataContainer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    public DataContainer(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Декодовані дані: " + message;
    }
}