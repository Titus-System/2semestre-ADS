package com.titus_systems.idscan;

import com.titus_systems.idscan.gui.Main;
import com.titus_systems.idscan.ollama.OllamaEngine;
import com.titus_systems.idscan.tesseract.TesseractEngine;

import io.github.ollama4j.utils.PromptBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando a aplicação...");
        
        String imagePath = "/home/pedro/Imagens/2col.png";
        processImage(imagePath);
        
        // iniciar a interface gráfica
        Main.main(args);
    }

    public static TesseractEngine startTesseractEngine(){
        TesseractEngine tess = new TesseractEngine();
        return tess;
    }

    public static OllamaEngine startOllamaEngine(){
        OllamaEngine ollamaEngine = new OllamaEngine("gemma2", 0.8f);
        return ollamaEngine;
    }

    public static void processImage(String imagePath) throws Exception{
        OllamaEngine ollamaEng = startOllamaEngine();
        TesseractEngine tessEng = startTesseractEngine();
        
        String tesseractResponse = tessEng.extractTextFromimage(imagePath);
        
        PromptBuilder prompt = new PromptBuilder();
        prompt.addLine(tesseractResponse);
        ollamaEng.generateAsyncAnswerGemma2(prompt.build());
    }
}
