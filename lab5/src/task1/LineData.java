package task1;

import java.io.*;

class LineData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;

    public LineData(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getWordCount() {
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        return content.trim().split("\\s+").length;
    }

    @Override
    public String toString() {
        return content;
    }
}