package com.titus_systems.idscan.ollama;

import io.github.ollama4j.OllamaAPI;

public class OllamaConnection{

    private OllamaAPI ollamaAPI;

    public OllamaConnection(){
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
    }

    public void chageHost(String host){
        this.ollamaAPI = new OllamaAPI(host);
    }

}
