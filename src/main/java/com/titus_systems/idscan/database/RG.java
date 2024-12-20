package com.titus_systems.idscan.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String dNasc = null;
    private String cnh = null;
    private String rg = null;
    private String fatorRh = null;
    private String oExp = null;
    private String estado = null;
    private String nisPisPasep = null;
    private String ctps = null;
    private String tEleitor = null;
    private String dExp = null;
    private String certMiliar = null;
    private String via = null;
    private String idProf = null;
    private String uf = null;
    private String regCivil = null;

    public RG(String nome, String cpf, String pai, String mae, String naturalidade,
            String dNasc, String cnh, String rg, String fatorRh, String oExp,
            String estado, String nisPisPasep, String ctps, String tEleitor,
            String dExp, String certMiliar, String via, String idProf, String uf, String regCivil) {
        this.nome = nome;
        this.cpf = cpf;
        this.pai = pai;
        this.mae = mae;
        this.naturalidade = naturalidade;
        this.dNasc = dNasc;
        this.cnh = cnh;
        this.rg = rg;
        this.fatorRh = fatorRh;
        this.oExp = oExp;
        this.estado = estado;
        this.nisPisPasep = nisPisPasep;
        this.ctps = ctps;
        this.tEleitor = tEleitor;
        this.dExp = dExp;
        this.certMiliar = certMiliar;
        this.via = via;
        this.idProf = idProf;
        this.uf = uf;
        this.regCivil = regCivil;
    }

    public RG() {

    }

    public RG(HashMap<String, String> extractedInfo) {
        for (Map.Entry<String, String> entry : extractedInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            this.setAttribute(key, value);
        }
    }

    public void checkDuplicatesInDatabase(Connection connection) throws SQLException {
        // Verifica se a entrada já existe no banco de dados
        String checkQuery = "SELECT COUNT(*) FROM RG WHERE registroGeral = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, this.rg);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Entrada existe, realizar atualização
                String updateQuery = "UPDATE RG SET " +
                    "nome = ?, dataNascimento = ?, naturalidade = ?, cpf = ?, nomePai = ?, nomeMae = ?, " +
                    "orgaoExpedidor = ?, estado = ?, dataExpedicao = ?, via = ?, uf = ?, cnh = ?, " +
                    "fatorRh = ?, nisPisPasep = ?, ctps = ?, tEleitor = ?, certMilitar = ?, " +
                    "identidadeProfissional = ?, registroCivil = ? WHERE registroGeral = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, this.nome);
                    updateStmt.setString(2, this.dNasc);
                    updateStmt.setString(3, this.naturalidade);
                    updateStmt.setString(4, this.cpf);
                    updateStmt.setString(5, this.pai);
                    updateStmt.setString(6, this.mae);
                    updateStmt.setString(7, this.oExp);
                    updateStmt.setString(8, this.estado);
                    updateStmt.setString(9, this.dExp);
                    updateStmt.setString(10, this.via);
                    updateStmt.setString(11, this.uf);
                    updateStmt.setString(12, this.cnh);
                    updateStmt.setString(13, this.fatorRh);
                    updateStmt.setString(14, this.nisPisPasep);
                    updateStmt.setString(15, this.ctps);
                    updateStmt.setString(16, this.tEleitor);
                    updateStmt.setString(17, this.certMiliar);
                    updateStmt.setString(18, this.idProf);
                    updateStmt.setString(19, this.regCivil);
                    updateStmt.setString(20, this.rg); 
                    updateStmt.executeUpdate();
                }
            } else {
                // Entrada não existe, realizar inserção
                String insertQuery = "INSERT INTO RG (registroGeral, nome, dataNascimento, naturalidade, cpf, nomePai, nomeMae, " +
                    "orgaoExpedidor, estado, dataExpedicao, via, uf, cnh, fatorRh, nisPisPasep, ctps, tEleitor, certMilitar, " +
                    "identidadeProfissional, registroCivil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, this.rg);
                    insertStmt.setString(2, this.nome);
                    insertStmt.setString(3, this.dNasc);
                    insertStmt.setString(4, this.naturalidade);
                    insertStmt.setString(5, this.cpf);
                    insertStmt.setString(6, this.pai);
                    insertStmt.setString(7, this.mae);
                    insertStmt.setString(8, this.oExp);
                    insertStmt.setString(9, this.estado);
                    insertStmt.setString(10, this.dExp);
                    insertStmt.setString(11, this.via);
                    insertStmt.setString(12, this.uf);
                    insertStmt.setString(13, this.cnh);
                    insertStmt.setString(14, this.fatorRh);
                    insertStmt.setString(15, this.nisPisPasep);
                    insertStmt.setString(16, this.ctps);
                    insertStmt.setString(17, this.tEleitor);
                    insertStmt.setString(18, this.certMiliar);
                    insertStmt.setString(19, this.idProf);
                    insertStmt.setString(20, this.regCivil);
                    insertStmt.executeUpdate();
                }
            }
        }
    }
    


    public void checkForDuplicates(Connection con) throws SQLException {
        if (this.cpf != null && !this.cpf.equals("null")) {
            try (PreparedStatement query = con.prepareStatement("SELECT cpf FROM RG WHERE cpf=?")) {
                query.setString(1, this.cpf);

                try (ResultSet result = query.executeQuery()) {
                    if (result.next() && result.getString("cpf").equals(this.cpf))
                        updateRecord(con, "cpf", this.cpf);

                }
            }
        } else if (this.rg != null && !this.rg.equals("null")) {
            try (PreparedStatement query = con.prepareStatement("SELECT registroGeral FROM RG WHERE registrGeral=?")) {
                query.setString(1, this.rg);

                try (ResultSet result = query.executeQuery()) {
                    if (result.next() && result.getString("registroGeral").equals(this.rg))
                        updateRecord(con, "registroGeral", this.rg);
                }
            }
        }
    }

    public void updateRecord(Connection con, String columnName, String columnValue) throws SQLException {
        HashMap<String, String> attributes = this.getAllAttributes();
        StringBuilder sql = new StringBuilder("UPDATE RG SET ");

        int count = 0;
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (entry.getValue() != null) {
                if (count > 0)
                    sql.append(", ");
                sql.append(entry.getKey()).append("=?");
                count++;
            }
        }

        sql.append(" WHERE ").append(columnName).append("=?");

        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            int index = 1;
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                if (entry.getValue() != null) {
                    stmt.setString(index++, entry.getValue());
                }
            }
            stmt.setString(index, columnValue);

            stmt.executeUpdate();
        }
    }
    
    public void saveToDatabase(Connection con) throws SQLException {
        HashMap<String, String> attributes = this.getAllAttributes();
        StringBuilder sql = new StringBuilder("INSERT INTO RG (");
        StringBuilder placeholders = new StringBuilder("VALUES (");

        int count = 0;

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (count > 0) {
                sql.append(", ");
                placeholders.append(", ");
            }

            sql.append(entry.getKey());
            placeholders.append("?");

            count++;
        }

        sql.append(") ");
        placeholders.append(")");
        sql.append(placeholders);

        PreparedStatement stmt = con.prepareStatement(sql.toString());

        int index = 1;
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            stmt.setString(index, entry.getValue());
            index++;
        }

        System.out.println(stmt);

        try {
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<RG> pullFromDataBase(Connection con, HashMap<String, String> attributes) throws SQLException {
        List<RG> usuarios = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM RG");

        int count = 0;

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String column = entry.getKey();
            String value = entry.getValue();

            if (value != null && !value.isEmpty()) {
                if (count == 0) {
                    sql.append(" WHERE ");
                } else {
                    sql.append(" AND ");
                }

                sql.append(column).append(" LIKE ?");
                count++;
            }
        }

        PreparedStatement stmt = con.prepareStatement(sql.toString());

        if (count > 0) {
            count = 1;
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                String value = entry.getValue();
                if (value != null && !value.isEmpty()) {
                    stmt.setString(count, "%" + value + "%");
                    count++;
                }
            }
        }

        System.out.println("statement: " + stmt);

        try (ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                RG usuario = new RG(result.getString("nome"), result.getString("cpf"), result.getString("nomePai"),
                        result.getString("nomeMae"), result.getString("naturalidade"),
                        result.getString("dataNascimento"), result.getString("cnh"), result.getString("registroGeral"),
                        result.getString("fatorRh"), result.getString("orgaoExpedidor"), result.getString("estado"),
                        result.getString("nisPisPasep"), result.getString("ctps"), result.getString("tEleitor"),
                        result.getString("dataExpedicao"), result.getString("certMilitar"), result.getString("via"),
                        result.getString("identidadeProfissional"), result.getString("uf"),
                        result.getString("registroCivil"));
                usuarios.add(usuario);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            stmt.close();
        }

        return usuarios;
    }

    public void removeFromDatabase(Connection con, RG rg) throws SQLException{
        if (rg.getCpf() == null && rg.getRg() == null) {
            throw new IllegalArgumentException("Pelo menos um dos atributos (CPF ou RG) deve ser fornecido.");
        }
        
        StringBuilder sql = new StringBuilder("DELETE FROM RG");
        String[] attributes = {rg.getCpf(), rg.getRg()};
        
        if (attributes[0] != null){
            sql.append(" WHERE ").append("cpf").append(" = ?");
        }
        if (attributes[1] != null){
            if (attributes[0] != null){
                sql.append(" AND ").append("registroGeral").append(" = ?"); 
            }else{
                sql.append(" WHERE ").append("registroGeral").append(" = ?");
            }
        }

        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            int count = 1;
            for (int i = 0; i < 2; i++) {
                if (attributes[i] != null){
                    stmt.setString(count, attributes[i]);
                    count++;
                }
            }

        System.out.println(stmt);
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Erro ao remover do banco de dados", exception);
        }
    }

    private void setAttribute(String key, String value) {
        key = key.toLowerCase().trim();
        if (value == null || value.equals("null")) {
            value = null;
        } else {
            value = value.toLowerCase().trim();
        }

        Map<String, List<String>> attributeMap = new HashMap<>();
        attributeMap.put("nome", Arrays.asList("nome", "name"));
        attributeMap.put("cpf", Arrays.asList("cpf", "cor", "cof", "opf", "11 digits number", "11 digit number"));
        attributeMap.put("nomePai", Arrays.asList("nome pai", "nomepai", "filiacao", "filiação"));
        attributeMap.put("nomeMae", Arrays.asList("nome mae", "nomemae", "filiacao", "filiação"));
        attributeMap.put("naturalidade", Arrays.asList("naturalidade", "nasc", "natural", "cidade"));
        attributeMap.put("dataNascimento", Arrays.asList("data nascimento", "datanascimento", "data de nascimento",
                "nascimento", "birth", "date of birth"));
        attributeMap.put("cnh", Arrays.asList("cnh", "10 digits number", "10 digit number"));
        attributeMap.put("registroGeral", Arrays.asList("registro geral", "registrogeral", "rg", "reg geral",
                "9 digits number", "9 digit number"));
        attributeMap.put("fatorRh", Arrays.asList("fator rh", "rh", "fatorrh"));
        attributeMap.put("orgaoExpedidor", Arrays.asList("orgao expedidor", "orgaoexpedidor", "oexp", "orgao exp"));
        attributeMap.put("estado", Arrays.asList("estado", "stado", "state"));
        attributeMap.put("nisPisPasep", Arrays.asList("nis", "pis", "pasep", "nis/pis/pasep"));
        attributeMap.put("ctps", Arrays.asList("ctps", "carteira trabalho"));
        attributeMap.put("tEleitor",
                Arrays.asList("titulo eleitor", "teleitor", "t.eleitor", "t eleitor", "título eleitor"));
        attributeMap.put("dataExpedicao", Arrays.asList("data expedicao", "data de expedicao", "data expedição",
                "data de expedição", "datadeexpedicao", "datadeexpedição"));
        attributeMap.put("certMiliar", Arrays.asList("cert militar", "certmilitar", "military certificate"));
        attributeMap.put("via", Arrays.asList("via", "2via"));
        attributeMap.put("identidadeProfissional", Arrays.asList("identidade profissional", "identidadeprofissional"));
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
                    case "via":
                        this.via = value;
                        break;
                    case "identidadeProfissional":
                        this.idProf = value;
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

    public HashMap<String, String> getAllAttributes() {
        // as chaves são os nomes das colunas no banco de dados
        HashMap<String, String> allAttributes = new HashMap<>();
        allAttributes.put("nome", this.nome);
        allAttributes.put("cpf", this.cpf);
        allAttributes.put("nomePai", this.pai);
        allAttributes.put("nomeMae", this.mae);
        allAttributes.put("naturalidade", this.naturalidade);
        allAttributes.put("dataNascimento", this.dNasc);
        allAttributes.put("cnh", this.cnh);
        allAttributes.put("registroGeral", this.getRg());
        allAttributes.put("fatorRh", this.fatorRh);
        allAttributes.put("orgaoExpedidor", this.oExp);
        allAttributes.put("estado", this.estado);
        allAttributes.put("nisPisPasep", this.nisPisPasep);
        allAttributes.put("ctps", this.ctps);
        allAttributes.put("tEleitor", this.tEleitor);
        allAttributes.put("dataExpedicao", this.dExp);
        allAttributes.put("certMilitar", this.certMiliar);
        allAttributes.put("via", this.via);
        allAttributes.put("identidadeProfissional", this.idProf);
        allAttributes.put("uf", this.uf);
        allAttributes.put("registroCivil", this.regCivil);
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
}