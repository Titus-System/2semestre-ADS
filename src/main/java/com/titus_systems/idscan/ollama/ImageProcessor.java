package com.titus_systems.idscan.ollama;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.titus_systems.idscan.tesseract.TesseractEngine;

import io.github.ollama4j.models.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;


public class ImageProcessor {
    private final OllamaEngine ollamaEngine;
    private final TesseractEngine tessEngine;

    public ImageProcessor(String ollamaModelName) {
        this.ollamaEngine = new OllamaEngine(ollamaModelName, 0.8f);
        this.tessEngine = new TesseractEngine();
    }

    public String processWithTesseract(String imagePath, IdPrompt prompt) throws Exception {
        String tesseractResult = this.tessEngine.extractTextFromimage(imagePath);
        prompt.buildWithTesseract(tesseractResult);
        String ollamaResult = this.ollamaEngine.generateAsyncAnswerFromPrompt(prompt.getPrompt().build());
        return ollamaResult;
    }

    public String processWithVLM (String imagePath, IdPrompt prompt) throws Exception{
        System.out.println("Iniciando processamento com VLM: " + this.ollamaEngine.getModel());
        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(this.ollamaEngine.getTemperature());

        System.out.println("Verificando caminho de imagem: " + imagePath);
        File imgFile = new File(imagePath);
        System.out.println("Arquivo de imagem criado: " + imgFile.getAbsolutePath());
        if (!imgFile.exists()){
            throw new Exception("Arquivo de imagem não encontrado!");
        }
        List<File> imageFiles = new LinkedList<>();
        imageFiles.add(imgFile);

        prompt.build();

        System.out.println("chamando generateWithImageFiles...");
        OllamaResult ollamaResult = this.ollamaEngine.generateWithImageFiles(this.ollamaEngine.getModel(), 
                                                                            prompt.getPrompt().build(), 
                                                                            imageFiles, 
                                                                            options.build());

        String ollamaResponse = ollamaResult.getResponse();
        System.out.println("prompt: \n" + prompt.getPrompt().build());
        System.out.println("Resposta obida do Ollama: \n" + ollamaResponse);
        return ollamaResponse;
    }

    public String processWithTesseractAndVLM(String imagePath, IdPrompt prompt) throws Exception{
        System.out.println("Iniciando processamento com Tesseract e VLM: " + this.ollamaEngine.getModel());
        System.out.println("Iniciando processamento com tesseract...");

        String tessAnswer = this.tessEngine.extractTextFromimage(imagePath);
        prompt.addLine("the following image is an id card from brazil. The following text was extracted from this same image");
        prompt.askWithTesseract(tessAnswer);
        prompt.askForMainInfo();
        prompt.askFormat();
        
        System.out.println("Iniciando processamento com Ollama...");
        String ollamaAnswer = this.processWithVLM(imagePath, prompt);
        return ollamaAnswer;
    }

    public void asyncProcessWithTesseractAndVLM(String imagePath, IdPrompt prompt, Consumer<String> callback){
        System.out.println("Iniciando processamento assíncrono...");
        Thread processImageThread = new Thread (() -> {
            try {
                System.out.println("Processando a imagem...");
                String asyncResult = processWithTesseractAndVLM(imagePath, prompt);
                System.out.println(asyncResult);

                // Passa o resultado para o callback
                callback.accept(asyncResult);
            } catch (Exception e) {
                System.out.println("Erro no método call: " + e.getMessage());
            }
        });
        processImageThread.start();
    }

    public void asyncProcessWithTesseract(String imagePath, IdPrompt prompt, Consumer<String> callback){
        System.out.println("Iniciando processamento assíncrono...");
        Thread processImageThread = new Thread (() -> {
            try {
                System.out.println("Processando a imagem...");
                String asyncResult = processWithTesseract(imagePath, prompt);
                System.out.println(asyncResult);

                // Passa o resultado para o callback
                callback.accept(asyncResult);
            } catch (Exception e) {
                System.out.println("Erro no método call: " + e.getMessage());
            }
        });
        processImageThread.start();
    }

    public void asyncProcessWithVLM(String imagePath, IdPrompt prompt, Consumer<String> callback) {
        System.out.println("Iniciando processamento assíncrono...");
        Thread processImageThread = new Thread (() -> {
            try {
                System.out.println("Processando a imagem...");
                String asyncResult = processWithVLM(imagePath, prompt);
                System.out.println(asyncResult);

                callback.accept(asyncResult);
            } catch (Exception e) {
                System.out.println("Erro no método call: " + e.getMessage());
            }
        });
        processImageThread.start();
    }
}
