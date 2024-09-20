package com.titus_systems.idscan.ollama;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.OllamaAsyncResultStreamer;
import io.github.ollama4j.utils.OptionsBuilder;

public class OllamaEngine extends OllamaAPI{
    
    private String model;
    private OptionsBuilder options;

    public OllamaEngine(String modelName, float temperature){
        super("http://localhost:11434/");
        this.setRequestTimeoutSeconds(180);
        this.model = modelName;
        
        this.options = new OptionsBuilder();
        options.setTemperature(temperature);
        
        System.out.println("ollama is running: " + this.ping());
    }

    public void setModel(String modelName){
        this.model = modelName;
    }

    public String getModel(){
        return this.model;
    }

    public String generateAsyncAnswerGemma2 (String prompt) throws Exception {
        OllamaAsyncResultStreamer streamer = this.generateAsync(CustomModelType.PHI3MINI, prompt, false);
        int pollIntervalMilliseconds = 1000;

        while (true) {
            String tokens = streamer.getStream().poll();
            System.out.print(tokens);
            if (!streamer.isAlive()) {
                break;
            }
            Thread.sleep(pollIntervalMilliseconds);
        }

        String completeResponse = streamer.getCompleteResponse();

        System.out.println("\n------------------------");
        System.out.println("Complete Response:");
        System.out.println("------------------------");

        System.out.println(completeResponse);

        return completeResponse;
    }

}
