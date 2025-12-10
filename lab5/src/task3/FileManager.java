package task3;

import java.io.*;

class FileManager {

    public void saveEncryptedObject(String filePath, Object data, int keyChar) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             EncryptionFilterOutputStream efos = new EncryptionFilterOutputStream(fos, keyChar);
             ObjectOutputStream oos = new ObjectOutputStream(efos)) {

            oos.writeObject(data);
            System.out.println("-> Об'єкт успішно зашифровано та збережено у файл.");
        }
    }

    public Object loadEncryptedObject(String filePath, int keyChar) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             DecryptionFilterInputStream dfis = new DecryptionFilterInputStream(fis, keyChar);
             ObjectInputStream ois = new ObjectInputStream(dfis)) {

            return ois.readObject();
        }
    }
}
