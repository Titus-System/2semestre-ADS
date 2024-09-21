package com.titus_systems.idscan;

import com.titus_systems.idscan.gui.Main;
import com.titus_systems.idscan.ollama.OllamaEngine;
import com.titus_systems.idscan.tesseract.TesseractEngine;

import io.github.ollama4j.utils.PromptBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando a aplicação...");
        
        String modelName = "gemma2:2b";
        String imagePath = "/home/pedro/Imagens/2col.png";

        Thread processImageThread = new Thread (() -> {
            try {
                processImage(modelName, imagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // processImage(modelName, imagePath);
        
        processImageThread.start();
        // iniciar a interface gráfica
        Main.main(args);
    }

    public static TesseractEngine startTesseractEngine(){
        TesseractEngine tess = new TesseractEngine();
        return tess;
    }

    public static OllamaEngine startOllamaEngine(String modelName){
        OllamaEngine ollamaEngine = new OllamaEngine(modelName, 0.8f);
        return ollamaEngine;
    }

    public static void processImage(String modelName, String imagePath) throws Exception{
        OllamaEngine ollamaEng = startOllamaEngine(modelName);
        TesseractEngine tessEng = startTesseractEngine();
        
        String tesseractResponse = tessEng.extractTextFromimage(imagePath);
        
        PromptBuilder prompt = new PromptBuilder();
        prompt.addLine(tesseractResponse);
        ollamaEng.generateAsyncAnswerFromPrompt(prompt.build());
        // ollamaEng.generateSyncAnswerCustom(prompt.build());
    }
}
