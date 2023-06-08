package com.loja_do_fulano.setor_vendas;

public class PessoaJuridica {

    private String razaoSocial;
    private int CNPJ;
    private int inscricaoEstadual;
    private String nomefantasia;
    
    public void pessoajuridica (int CNPJ, int inscricaoestadual, String nomefantasia, String razaosocial){

        this.CNPJ = CNPJ;
        this.inscricaoEstadual = inscricaoestadual;
        this.nomefantasia = nomefantasia;
        this.razaoSocial = razaosocial;
    }

    public String getRazaosocial(){
        return razaoSocial;
    }
    public int getInscricaoestadual(){
        return inscricaoEstadual;
    }
    public int getCNPJ(){
        return CNPJ;
    }
    public String getNomefantasia(){
        return nomefantasia;
    }
}
