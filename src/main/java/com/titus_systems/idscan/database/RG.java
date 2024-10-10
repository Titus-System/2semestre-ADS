package com.titus_systems.idscan.database;

public class RG {
    private String nome;
    private String cpf;
    private String pai;
    private String mae;
    private String naturalidade;
    private String dNasc;
    private String cnh;
    private String rg;
    private String fatorRh;
    private String oExp;
    private String estado;
    private String nisPisPasep;
    private String ctps;
    private String tEleitor;
    private String dExp;
    private String certMiliar;
    private String via;
    private String idProf;
    private String serie;
    private String uf;
    private String regCivil;

    public RG(String nome, String cpf, String pai, String mae, String naturalidade,
        String dNasc, String cnh, String rg, String fatorRh, String oExp,
        String estado, String nisPisPasep, String ctps, String tEleitor,
        String dExp, String certMiliar, String via, String idProf,
        String serie, String uf, String regCivil) {
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
        this.serie = serie;
        this.uf = uf;
        this.regCivil = regCivil;
    }
}
