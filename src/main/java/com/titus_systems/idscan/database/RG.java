package com.titus_systems.idscan.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RG {
    private String nome = null;
    private String cpf = null;
    private String pai = null;
    private String mae = null;
    private String naturalidade = null;
    private String nacionalidade = null;
    private String dNasc = null;
    private String cnh = null;
    private String rg = null;
    private String fatorRh = null;
    private String oExp = null;
    private String observacao = null;
    private String estado = null;
    private String nisPisPasep = null;
    private String ctps = null;
    private String tEleitor = null;
    private String dExp = null;
    private String certMiliar = null;
    private String dni = null;
    private String via = null;
    private String idProf = null;
    private String cns = null;
    private String serie = null;
    private String uf = null;
    private String regCivil = null;
    private String regGeral = null;

    public RG(String nome, String cpf, String pai, String mae, String naturalidade,
        String nacionalidade, String dNasc, String cnh, String rg, String fatorRh, String oExp, String observacao,
        String estado, String nisPisPasep, String ctps, String tEleitor,
        String dExp, String certMiliar, String dni, String via, String idProf,
        String serie, String uf, String regCivil) {
        this.nome = nome;
        this.cpf = cpf;
        this.pai = pai;
        this.mae = mae;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.dNasc = dNasc;
        this.cnh = cnh;
        this.rg = rg;
        this.fatorRh = fatorRh;
        this.oExp = oExp;
        this.observacao = observacao;
        this.estado = estado;
        this.nisPisPasep = nisPisPasep;
        this.ctps = ctps;
        this.tEleitor = tEleitor;
        this.dExp = dExp;
        this.certMiliar = certMiliar;
        this.dni = dni;
        this.via = via;
        this.idProf = idProf;
        this.cns = cns;
        this.serie = serie;
        this.uf = uf;
        this.regCivil = regCivil;
        this.regGeral = regGeral;
    }

    public RG () {

    }

    public RG (HashMap<String,String> extractedInfo){
        for (Map.Entry<String,String> entry : extractedInfo.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            this.setAttribute(key, value);
        }
    }

    public void saveToDatabase(Connection connection) throws SQLException{
        HashMap<String,String> attributes = this.getAllAttributes();
        StringBuilder buildStatement = new StringBuilder();
        buildStatement.append("INSERT INTO RG(");
        String sql = "INSERT INTO RG(?) VALUES(?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (Map.Entry<String,String> entry: attributes.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
        }
            
        try { 
           // String id_aux=Integer.toString(cliente.getId());
            stmt.setString(1, this.getNome());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
    }


    private void setAttribute(String key, String value) {
        key = key.toLowerCase().trim();
        if (value == null || value.equals("null")){
            value = null;
        }else{
            value = value.toLowerCase().trim();
        }

        Map<String, List<String>> attributeMap = new HashMap<>();
        attributeMap.put("nome", Arrays.asList("nome", "name"));
        attributeMap.put("cpf", Arrays.asList("cpf", "cor", "cof", "opf", "11 digits number", "11 digit number"));
        attributeMap.put("nomePai", Arrays.asList("nome pai", "nomepai", "filiacao", "filiação"));
        attributeMap.put("nomeMae", Arrays.asList("nome mae", "nomemae", "filiacao", "filiação"));
        attributeMap.put("naturalidade", Arrays.asList("naturalidade", "nasc", "natural", "cidade"));
        attributeMap.put("nacionalidade", Arrays.asList("nacionalidade", "pais"));
        attributeMap.put("dataNascimento", Arrays.asList("data nascimento","datanascimento", "data de nascimento", "nascimento", "birth", "date of birth"));
        attributeMap.put("cnh", Arrays.asList("cnh", "10 digits number", "10 digit number"));
        attributeMap.put("registroGeral", Arrays.asList("registro geral", "registrogeral", "rg", "reg geral", "9 digits number", "9 digit number"));
        attributeMap.put("fatorRh", Arrays.asList("fator rh", "rh", "fatorrh"));
        attributeMap.put("orgaoExpedidor", Arrays.asList("orgao expedidor","orgaoexpedidor", "oexp", "orgao exp"));
        attributeMap.put("observacao", Arrays.asList("observacao"));
        attributeMap.put("estado", Arrays.asList("estado","stado", "state"));
        attributeMap.put("nisPisPasep", Arrays.asList("nis", "pis", "pasep", "nis/pis/pasep"));
        attributeMap.put("ctps", Arrays.asList("ctps", "carteira trabalho"));
        attributeMap.put("tEleitor", Arrays.asList("titulo eleitor", "teleitor", "t.eleitor", "t eleitor", "título eleitor"));
        attributeMap.put("dataExpedicao", Arrays.asList("data expedicao", "data de expedicao","data expedição", "data de expedição", "datadeexpedicao", "datadeexpedição"));
        attributeMap.put("certMiliar", Arrays.asList("cert militar","certmilitar", "military certificate"));
        attributeMap.put("dni", Arrays.asList("dni"));
        attributeMap.put("via", Arrays.asList("via", "2via"));
        attributeMap.put("identidadeProfissional", Arrays.asList("identidade profissional", "identidadeprofissional"));
        attributeMap.put("cns", Arrays.asList("cns"));
        attributeMap.put("serie", Arrays.asList("serie", "série", "series"));
        attributeMap.put("uf", Arrays.asList("uf", "federal unit"));
        attributeMap.put("registroCivil", Arrays.asList("registro civil", "registrocivil", "civil registry"));
        attributeMap.put("registroGeral", Arrays.asList("registro geral", "registrogeral", "geral registry"));
    
        for (Map.Entry<String, List<String>> entry : attributeMap.entrySet()) {
            if (entry.getValue().contains(key)) {
                switch (entry.getKey()) {
                    case "nome":
                        this.nome = value;
                        break;
                    case "cpf":
                        this.cpf = value;
                        break;
                    case "nomePai":
                        this.pai = value;
                        break;
                    case "nomeMae":
                        this.mae = value;
                        break;
                    case "naturalidade":
                        this.naturalidade = value;
                        break;
                    case "nacionalidade":
                        this.nacionalidade = value;
                        break;
                    case "dataNascimento":
                        this.dNasc = value;
                        break;
                    case "cnh":
                        this.cnh = value;
                        break;
                    case "registroGeral":
                        this.rg = value;
                        break;
                    case "fatorRh":
                        this.fatorRh = value;
                        break;
                    case "orgaoExpedidor":
                        this.oExp = value;
                        break; 
                    case "observacao":
                        this.observacao = value;
                        break;
                    case "estado":
                        this.estado = value;
                        break;
                    case "nisPisPasep":
                        this.nisPisPasep = value;
                        break;
                    case "ctps":
                        this.ctps = value;
                        break;
                    case "tEleitor":
                        this.tEleitor = value;
                        break;
                    case "dataExpedicao":
                        this.dExp = value;
                        break;
                    case "certMiliar":
                        this.certMiliar = value;
                        break;
                    case "dni":
                        this.dni = value;
                        break;
                    case "via":
                        this.via = value;
                        break;
                    case "identidadeProfissional":
                        this.idProf = value;
                        break;
                    case "cns":
                        this.cns = value;
                        break;
                    case "serie":
                        this.serie = value;
                        break;
                    case "uf":
                        this.uf = value;
                        break;
                    case "registroCivil":
                        this.regCivil = value;
                        break;
                }
                break;
            }
        }
    }

    public HashMap<String,String> getAllAttributes(){
        // as chaves são os nomes das colunas no banco de dados
        HashMap<String,String> allAttributes = new HashMap<>();
        allAttributes.put("nome", this.nome);
        allAttributes.put("cpf", this.cpf);
        allAttributes.put("nomePai", this.pai);
        allAttributes.put("nomeMae", this.mae);
        allAttributes.put("naturalidade", this.naturalidade);
        allAttributes.put("nacionalidade", this.nacionalidade);
        allAttributes.put("dataNascimento", this.dNasc);
        allAttributes.put("cnh", this.cnh);
        allAttributes.put("registroGeral", this.rg);
        allAttributes.put("fatorRh", this.fatorRh);
        allAttributes.put("orgaoExpedidor", this.oExp);
        allAttributes.put("observacao", this.observacao);
        allAttributes.put("estado", this.estado);
        allAttributes.put("nisPisPasep", this.nisPisPasep);
        allAttributes.put("ctps", this.ctps);
        allAttributes.put("tEleitor", this.tEleitor);
        allAttributes.put("dataExpedicao", this.dExp);
        allAttributes.put("certMilitar", this.certMiliar);
        allAttributes.put("dni", this.dni);
        allAttributes.put("via", this.via);
        allAttributes.put("identidadeProfissional", this.idProf);
        allAttributes.put("cns", this.cns);
        allAttributes.put("serie", this.serie);
        allAttributes.put("uf", this.uf);
        allAttributes.put("registroCivil", this.regCivil);
        allAttributes.put("registroGeral", this.regGeral);
        return allAttributes;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPai() {
        return this.pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return this.mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getNaturalidade() {
        return this.naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return this.nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getdNasc() {
        return this.dNasc;
    }

    public void setdNasc(String dNasc) {
        this.dNasc = dNasc;
    }

    public String getCnh() {
        return this.cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getRg() {
        return this.rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getFatorRh() {
        return this.fatorRh;
    }

    public void setFatorRh(String fatorRh) {
        this.fatorRh = fatorRh;
    }

    public String getoExp() {
        return this.oExp;
    }


    public void setoExp(String oExp) {
        this.oExp = oExp;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNisPisPasep() {
        return this.nisPisPasep;
    }

    public void setNisPisPasep(String nisPisPasep) {
        this.nisPisPasep = nisPisPasep;
    }

    public String getCtps() {
        return this.ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String gettEleitor() {
        return this.tEleitor;
    }

    public void settEleitor(String tEleitor) {
        this.tEleitor = tEleitor;
    }

    public String getdExp() {
        return this.dExp;
    }

    public void setdExp(String dExp) {
        this.dExp = dExp;
    }

    public String getCertMiliar() {
        return this.certMiliar;
    }

    public void setCertMiliar(String certMiliar) {
        this.certMiliar = certMiliar;
    }

    public String getDni() {
        return this.dni;
    }

    public void setPadding(String dni) {
        this.dni = dni;
    }

    public String getVia() {
        return this.via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getIdProf() {
        return this.idProf;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }

    public String getCns() {
        return this.cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getSerie() {
        return this.serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRegCivil() {
        return this.regCivil;
    }

    public void setRegCivil(String regCivil) {
        this.regCivil = regCivil;
    }

    public String getRegGeral() {
        return this.regGeral;
    }

    public void setRegGeral(String regGeral) {
        this.regGeral = regGeral;
    }
}