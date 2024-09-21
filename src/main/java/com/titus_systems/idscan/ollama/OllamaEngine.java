package com.titus_systems.idscan.ollama;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.OllamaAsyncResultStreamer;
import io.github.ollama4j.models.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;

public class OllamaEngine extends OllamaAPI{
    
    private String model;
    private float temperature;

    public OllamaEngine(String modelName, float temperature){
        super("http://localhost:11434/");
        this.setRequestTimeoutSeconds(180);
        this.model = modelName;
        this.temperature = temperature;
        
        System.out.println("ollama is running: " + this.ping());
    }

    public void setModel(String modelName){
        this.model = modelName;
    }

    public String getModel(){
        return this.model;
    }

    public void setTemperature(float temperature){
        this.temperature = temperature;
    }

    public float getTemperature(){
        return this.temperature;
    }

    public String generateAsyncAnswerFromPrompt (String prompt) throws Exception {
        OllamaAsyncResultStreamer streamer = this.generateAsync(this.model, prompt, false);
        // if (this.model.equals("phi3:mini")){
        //     streamer = this.generateAsync(CustomModelType.PHI3MINI, prompt, false);
        // } else if (this.model.equals("minicpm-v")){
        //     streamer = this.generateAsync(CustomModelType.MINICPMV, prompt, false);
        // }

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

    public String generateSyncAnswerFromPrompt(String prompt) throws Exception{
        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(this.temperature);
        OllamaResult result = this.generate(this.model, prompt, false, options.build());
        return result.getResponse();
    }

}
