package com.titus_systems.idscan.ollama;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.titus_systems.idscan.tesseract.TesseractEngine;

import io.github.ollama4j.models.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;
import javafx.concurrent.Task;


public class ImageProcessor {
    private final OllamaEngine ollamaEngine;
    private final TesseractEngine tessEngine;

    public ImageProcessor(String ollamaModelName) {
        this.ollamaEngine = new OllamaEngine(ollamaModelName, 0.8f);
        this.tessEngine = new TesseractEngine();
    }

    public String processWithTesseract(String imagePath, PromptBuilder prompt) throws Exception {
        String tesseractResult = this.tessEngine.extractTextFromimage(imagePath);
        prompt.addLine(tesseractResult);
        String ollamaResult = this.ollamaEngine.generateAsyncAnswerFromPrompt(prompt.build());
        return ollamaResult;
    }

    public String processWithVLM (String imagePath, PromptBuilder prompt) throws Exception{
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

        System.out.println("chamando generateWithImageFiles...");
        OllamaResult ollamaResult = this.ollamaEngine.generateWithImageFiles(this.ollamaEngine.getModel(), 
                                                                            prompt.build(), 
                                                                            imageFiles, 
                                                                            options.build());

        String ollamaResponse = ollamaResult.getResponse();
        System.out.println("Resposta obida do Ollama: " + ollamaResponse);
        return ollamaResponse;
    }

    public void asyncProcessWithTesseract(String imagePath, PromptBuilder prompt, Consumer<String> callback){
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

    public void asyncProcessWithVLM(String imagePath, PromptBuilder prompt, Consumer<Void> callback) {
        System.out.println("Iniciando processamento assíncrono...");
        Thread processImageThread = new Thread (() -> {
            try {
                System.out.println("Processando a imagem...");
                String asyncResult = processWithVLM(imagePath, prompt);
                System.out.println(asyncResult);
            } catch (Exception e) {
                System.out.println("Erro no método call: " + e.getMessage());
            }
        });
        processImageThread.start();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    updateMessage("Processando a imagem...");
                    String asyncResult = processWithVLM(imagePath, prompt);
                    System.out.println(asyncResult);
                } catch (Exception e) {
                    System.out.println("Erro no método call: " + e.getMessage());
                    throw e; // Rethrow para que a task seja marcada como falha
                }
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            System.out.println("Processamento concluído");
            callback.accept(null); // Use null se não houver um resultado específico
        });
    
        task.setOnFailed(event -> {
            Throwable e = task.getException();
            System.out.println("Falha no processamento: " + e.getMessage());
            // Atualize a interface do usuário com uma mensagem de erro
        });
    
        //execução da task em uma nova thread --> isso que gera a assincronicidade
        System.out.println("estado da task antes de iniciar: " + task.getState());
        Thread taskThread = new Thread(task);
        taskThread.start();
        System.out.println("estado da task depois de iniciar: " + task.getState());
        System.out.println("estado da trhread: " + taskThread.getState());
    }
}
