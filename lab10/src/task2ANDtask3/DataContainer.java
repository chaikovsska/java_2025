package task2ANDtask3;

import java.io.Serializable;

class DataContainer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    public DataContainer(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}