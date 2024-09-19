package com.titus_systems.idscan.tesseract;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractEngine extends Tesseract{

    public TesseractEngine (){
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")){
            // datapath para windows
            this.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            this.setLanguage("por");
            System.out.println("Conexão com Tesseract aberta com sucesso!");

        } else if (osName.contains("nux") || osName.contains("nix")){
            // datapath para linux
            this.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
            this.setLanguage("por");
            System.out.println("Conexão com Tesseract aberta com sucesso");
            
        } else {
            throw new UnsupportedOperationException("Sistema operacional não suportado.");
        }
    }


    public String extractTextFromimage(String imagePath) throws Exception{
        File imgFile = new File(imagePath);

        try{
            String result = this.doOCR(imgFile);
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "pobrema aqui";
        }
    }
    
}
