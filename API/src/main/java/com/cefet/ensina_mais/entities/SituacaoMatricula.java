package com.cefet.ensina_mais.entities;

public enum SituacaoMatricula {
    REPROVADO(0, "Reprovado"),
    APROVADO(1, "Aprovado"), 
    EM_ANDAMENTO(2, "Em Andamento");
    
    private final int codigo;
    private final String descricao;
    
    SituacaoMatricula(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public static SituacaoMatricula fromCodigo(int codigo) {
        for (SituacaoMatricula situacao : values()) {
            if (situacao.codigo == codigo) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Código de situação inválido: " + codigo);
    }
}