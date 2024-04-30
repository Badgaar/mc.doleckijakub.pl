package pl.doleckijakub.mc.util;

import java.io.*;

public final class FileUtils {

    public static void copy(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }

            String[] files = src.list();
            if (files == null) return;

            for (String file : files) copy(new File(src, file), new File(dest, file));
        } else { // not a directory
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

    public static void delete(File f) {
        File[] allContents = f.listFiles();

        if (allContents != null) {
            for (File file : allContents) {
                delete(file);
            }
        }

        if (!f.delete()) throw new RuntimeException("Failed to delete " + f.getAbsolutePath());
    }

}
