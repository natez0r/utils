import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.util.Arrays;

/**
 * Small helper class to create a named file with the size passed.
 * Usage : FileCreator file_size in bytes name of file
 */
public class FileCreator {

  private static final int CHUNK_SIZE = 1024;
    
    public static void main(String[] args) {
        
        if (args == null || args.length != 2) {
            System.out.println("Usage: FileCreator <file_size_in_KB> <file_name>");
            System.exit(1);
            return;
        }
        
        final int fileSize = Integer.parseInt(args[0]);
        final File file = new File(args[1]);
        
        if (file.isDirectory()) {
            System.out.println("Nice try, not gonna delete that directory.");
            System.exit(1);
            return;
        }

        if (file.exists()) {
            file.delete();
        }

        final byte[] bytes = new byte[CHUNK_SIZE];
        Arrays.fill(bytes, (byte) 1);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            for (int i = 0; i < fileSize; i++) {
              bos.write(bytes);
            }

            bos.flush();
            bos.close();
        } catch (IOException e) {}
    }
}
