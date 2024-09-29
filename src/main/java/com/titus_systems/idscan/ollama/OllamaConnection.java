package com.titus_systems.idscan.ollama;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.Model;
import io.github.ollama4j.models.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OllamaConnection{

    public static void main (String[] args) throws Exception{
        // SLF4JBridgeHandler.uninstall();

        // caminho da imagem que será processada
        String imagePath = "/home/pedro/Imagens/2col.png";
        String tesseractAnswer = getTesseractAnswer(imagePath);

        String prompt = "this is the text extracted ";

        String answer = getOllamaAnswer(prompt + "\n" + tesseractAnswer, imagePath);
        System.out.println(answer);

        Gson gson = new Gson();
        String json = gson.toJson(answer);
        try (FileWriter file = new FileWriter("respostaPrompt.json")) {
            file.write(json);
            System.out.println("Arquivo JSON salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOllamaAnswer(String prompt, String filePath) throws Exception{
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(180);
        System.out.println("ollama is running: " + ollamaAPI.ping());

        List<Model> models = ollamaAPI.listModels();
        System.out.println("models: ");
        models.forEach(model -> System.out.println(model.getName()));
        
        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(0.8f);

        OllamaResult result = ollamaAPI.generate("gemma2:2b", prompt, false, options.build());
        // List<File> fileList =  List.of(new File(filePath));
        // OllamaResult result = ollamaAPI.generateWithImageFiles("moondream", prompt, fileList, options.build());

        return result.getResponse();
    }


    public static String getTesseractAnswer(String imagePath) throws Exception{
        Tesseract tess = new Tesseract();

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")){
            // datapath para windows
            tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            tess.setLanguage("por");
        } else if (osName.contains("nux") || osName.contains("nix")){
            // datapath para linux
            tess.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
            tess.setLanguage("por");
        } else {
            throw new UnsupportedOperationException("Sistema operacional não suportado.");
        }
        
        File imgFile = new File(imagePath);

        try{
            String result = tess.doOCR(imgFile);
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "pobrema aqui";
        }
    }

}
