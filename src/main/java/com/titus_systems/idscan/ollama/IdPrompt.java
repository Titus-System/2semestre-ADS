package com.titus_systems.idscan.ollama;

import io.github.ollama4j.utils.PromptBuilder;

public class IdPrompt {

    private final PromptBuilder prompt;
    private boolean full;

    public IdPrompt (boolean complete){
        this.prompt = new PromptBuilder();
        this.full = complete;
    }

    public PromptBuilder getPrompt(){
        return this.prompt;
    }

    public void addLine(String line){
        this.prompt.addLine(line);
    }

    public void build(){        
        if (this.full){
            this.askForAll();
        }else{
            this.askForMainInfo();
        }
        this.askFormat();
    }

    public void buildWithTesseract(String tessResponse){
        this.askWithTesseract(tessResponse);
        if (this.full){
            this.askForAll();
        }else{
            this.askForMainInfo();
        }
        this.askFormat();
    }

    public void askForMainInfo(){
        this.askForEstado();
        this.askForNome();
        this.askForDataNascimento();
        this.askForNaturalidade();
        this.askForFiliacao();
        this.askForCPF();
        this.askForRG();
        this.askForDataExpedicao();
        this.askForCNH();
        this.askForTituloEleitor();
    }

    public void askForAll(){
        this.askForMainInfo();
        this.askForIdProfissional();
        this.askForNisPisPasep();
        this.askForRegistroCivil();
        this.askForVia();
        this.askForCertMilitar();
        this.askForCTPS();
        this.askForRH();
    }

    public void askWithTesseract(String tessResponse){
        prompt.addLine("The following text was extracted from a brazilian id card with the OCR tool Tesseract. Get the information from the document as asked.");
        prompt.addSeparator();
        prompt.add(tessResponse);
        prompt.addSeparator();
        prompt.addLine("the text above was extracted with an ocr tool, so some of the sentences and words may be misspelled or broken in parts. Take that into consideration and avoid null answers.");
    }

    public void askFormat(){
        prompt.addLine("the information asked should be returned in json format, composed by the attribute and its value found in the document.");
        prompt.addLine("The keys in the json are: ESTADO, NOME, DATA NASCIMENTO, NATURALIDADE, CPF, REGISTRO GERAL, NOME PAI, NOME MAE, VIA, DATA DE EXPEDICAO, REGISTRO CIVIL, TELEITOR, NIS/PIS/PASEP, IDENTIDADE PROFISSIONAL, CNH, FILIACAO, DATA NASCIMENTO, ORGAO EXPEDIDOR, DATA DE EXPEDICAO, CERT MILITAR, CTPS, RH.");
        prompt.addLine("the answer must contain all the keys as described. In case the information requested is not found, the value should be stated as 'null'.");
        prompt.addLine("return only the json answer. Do not make comments of any kind or give any explanations or descriptions.");
    }
    
    public void askForNome(){
        prompt.addLine("NOME is the name of the id holder. It is placed after the label 'NOME'.");
        prompt.addLine("get the NOME attribute of the document.");
    }
    
    public void askForRG(){
        prompt.addLine("REGISTRO GERAL is a 9 digits number in the format 'nn.nnn.nnn-n'.");
        prompt.addLine("the number for REGISTRO GERAL is located in the back of the document, placed on the top left side, right after the label REGISTRO GERAL.");
        prompt.addLine("get the REGISTRO GERAL of the document.");
    }

    public void askForVia(){
        prompt.addLine("VIA is an information without label. It is is characterized by a number in front of the data VIA, such as 2via.");
        prompt.addLine("get the VIA attribute of the document.");
    }

    public void askForCPF(){
        prompt.addLine("CPF is a 11 digit number in the format 'nnnnnnnnn/nn' placed after the label CPF.");
        prompt.addLine("get the CPF of the document");
    }

    public void askForDataExpedicao(){
        prompt.addLine("DATA DE EXPEDICAO is a date in the format 'nn/nn/nnnn'.");
        prompt.addLine("the date for DATA DE EXPEDIÇÂO is placed right after the label DATA DE EXPEDIÇÂO.");
        prompt.addLine("get the DATA DE EXPEDICAO of the document.");
    }

    public void askForRegistroCivil(){
        prompt.addLine("REGISTRO CIVIL is a long information placed in the line bellow the label REGISTRO CIVIL.");
        prompt.addLine("REGISTRO CIVIL starts with the name of a city such as 'são josé dos campos' and goes all the way to the end of the line.");
        prompt.addLine("get the REGISTRO CIVIL of the document.");
    }

    public void askForTituloEleitor(){
        prompt.addLine("TELEITOR is a 15 digit number in the format nnnnnnnnnnnnnnn.");
        prompt.addLine("the number for TELEITOR is in the line bellow the labels T.ELEITOR SERIE UF.");
        prompt.addLine("get the TELEITOR attribute of the document.");
    }

    public void askForNisPisPasep(){
        prompt.addLine("the NIS/PIS/PASEP is a number under the line with the label NIS/PIS/PASEP");
        prompt.addLine("get the NIS/PIS/PASEP number of the document");
    }

    public void askForIdProfissional(){
        prompt.addLine("IDENTIDADE PROFISSIONAL is a title in the line bellow the label IDENTIDADE PROFISSIONAL");
        prompt.addLine("get the IDENTIDADE PROFISSIONAL of the document");
    }

    public void askForCNH(){
        prompt.addLine("CNH is a 10 digit number placed in the line bellow the label CNH.");
        prompt.addLine("get the CNH number of the document");
    }

    public void askForNaturalidade(){
        prompt.addLine("NATURALIDADE is composed by the name of a city and the abbreviation for the city's state. It is placed in the line bellow the label NATURALIDADE.");
        prompt.addLine("get the NATURALIDADE of the document.");
    }

    public void askForFiliacao(){
        prompt.addLine("FILIACAO is composed by two names under the label FILIACAO.");
        prompt.addLine("this is the fathers name and the mothers name.");
        prompt.addLine("get the fathers name as NOME PAI and the mothers name as NOME MAE of the document.");
    }

    public void askForDataNascimento(){
        prompt.addLine("DATA NASCIMENTO is a date in the format 'nn/nn/nnnn' placed in the line bellow the label DATA NASCIMENTO.");
        prompt.addLine("get the DATA NASCIMENTO of the document.");
    }

    public void askForOrgaoExp(){
        prompt.addLine("ORGAO EXPEDIDOR is a 5 letter title placed in the line bellow the label 'ORGÃO EXPEDIDOR'.");
        prompt.addLine("get the ORGAO EXPEDIDOR of the document");
    }

    public void askForCertMilitar(){
        prompt.addLine("CERT MILITAR is a 12 digit number placed in the line bellow the label CERT. MILITAR.");
        prompt.addLine("get the CERT MILITAR of the document.");
    }

    public void askForRH(){
        prompt.addLine("RH is an indication os positive or negative. It is placed in the line bellow FATOR RH, right after the value for ORGAO EXPEDIDOR.");
        prompt.addLine("get the RH attribute of the document.");
    }

    public void askForCTPS(){
        prompt.addLine("CTPS is a number placed under the line with the label CTPS, right after the TITULO ELEITOR value.");
        prompt.addLine("get the CTPS of the document.");
    }

    public void askForEstado(){
        prompt.addLine("ESTADO is the name of the state the document was created. The state name is plkaced after the label ESTADO DE.");
        prompt.addLine("get the ESTADO attribute of the document.");
    }

    public void setFull(boolean bool){
        this.full = bool;
    }

    public boolean getFull(){
        return this.full;
    }

}
