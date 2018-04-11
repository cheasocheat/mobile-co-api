package com.khmersolution.moduler.util;

import java.io.*;

/**
 * Created by DANG DIM
 * Date     : 4/11/2018, 10:57 AM
 * Email    : d.dim@gl-f.com
 */

public class UploadUtil {

    public static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (uploadedInputStream != null)
                    try {
                        uploadedInputStream.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            try {
                if (uploadedInputStream != null){
                    uploadedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
