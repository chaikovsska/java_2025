package task2ANDtask3;

import java.io.*;

class FileManager {

    public void saveEncryptedObject(String filePath, Object data, int keyChar) throws IOException {
        Main.logger.fine("Відкриття потоку запису для: " + filePath);

        try (FileOutputStream fos = new FileOutputStream(filePath);
             EncryptionFilterOutputStream efos = new EncryptionFilterOutputStream(fos, keyChar);
             ObjectOutputStream oos = new ObjectOutputStream(efos)) {

            oos.writeObject(data);

            Main.logger.info("Об'єкт успішно зашифровано та збережено: " + filePath);
            System.out.println(Main.messages.getString("msg.success.save"));
        }
    }

    public Object loadEncryptedObject(String filePath, int keyChar) throws IOException, ClassNotFoundException {
        Main.logger.fine("Відкриття потоку читання для: " + filePath);

        try (FileInputStream fis = new FileInputStream(filePath);
             DecryptionFilterInputStream dfis = new DecryptionFilterInputStream(fis, keyChar);
             ObjectInputStream ois = new ObjectInputStream(dfis)) {

            Object obj = ois.readObject();
            Main.logger.info("Об'єкт успішно зчитано та дешифровано з файлу: " + filePath);
            return obj;
        }
    }
}