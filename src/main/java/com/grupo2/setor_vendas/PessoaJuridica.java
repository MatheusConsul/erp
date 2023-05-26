public class PessoaJuridica {
    private String razaosocial;
    private int CNPJ;
    private int inscricaoestadual;
    private String nomefantasia;
    public pessoajuridica(int CNPJ, int inscricaoestadual, String nomefantasia, String razaosocial){
        this.CNPJ = CNPJ;
        this.inscricaoestadual = inscricaoestadual;
        this.nomefantasia = nomefantasia;
        this.razaosocial = razaosocial;
    }
    public String getRazaosocial(){
        return razaosocial;
    }
    public int getInscricaoestadual(){
        return inscricaoestadual;
    }
    public int getCNPJ(){
        return CNPJ;
    }
    public String getNomefantasia(){
        return nomefantasia;
    }
}
