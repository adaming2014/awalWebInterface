/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.adaming.awal.webinterface.util;

import fr.adaming.awal.webinterface.bean.manager.FirmManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author INTI0221
 */
public class FileUtil {

    public static void saveFile(final int id, final String dirPath, final UploadedFile logo, final String fimeName) {
        File dir = new File(dirPath + "/" + id);
        if (!dir.exists()) {
            try {
                Files.createDirectory(dir.toPath());
            } catch (IOException ex) {
                Logger.getLogger(FirmManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        File file = new File(dir, fimeName);
        System.out.println(file.getPath());

        try (InputStream input = logo.getInputstream()) {
            Files.copy(input, file.toPath(), REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(FirmManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
