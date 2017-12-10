package Model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.Arvore;

/**
 * @param <T> aceito qualquer abjeto que seja comparable
 * @author Asaf Santana
 */
public class No<T extends Comparable> {

    private No pai;
    private No direito;
    private No esquerdo;
    private T info;
    private boolean cor;

    /**
     * Usado para o sentinela
     */
    public No() {

        this.info = null;
        this.pai = null; // sentinela nao tem pai
        this.direito = null;
        this.esquerdo = null;
        this.cor = false;
    }

    /**
     * Pede um info para começar Começa com a cor vermelha
     *
     * @param info
     */
    public No(T info, boolean cor) {
        this.pai = Arvore.sentinela;
        this.direito = Arvore.sentinela;
        this.esquerdo = Arvore.sentinela;
        this.info = info;
        this.cor = cor;
    }

    /**
     * Encontra o Nodo que contém o valor que foi enviado como parâmetro, ou o mais próximo disso
     * Bem útil para encontrar o local em que será colocado um novo Nodo com determinado valor
     * @param num
     * @return
     */
     public No busca(T num) {
        if (num.compareTo(getInfo()) > 0 && this.getDireito().getInfo() != null) {
            return getDireito().busca(num);
        } else if (num.compareTo(getInfo()) < 0 && this.getEsquerdo().getInfo() != null) {
            return getEsquerdo().busca(num);
        } else if (num.compareTo(getInfo()) == 0) {
            throw new IllegalArgumentException("No ja existente");
        }
        return this;
    }

    /**
     * Vai mostrar o valor mais baixo presente na árvore a partir do Nodo que
     * está rodando no momento
     *
     * @return
     */
    public No minimo() {
        if (!this.esquerdo.equals(Arvore.sentinela)) {
            return getEsquerdo().minimo();
        } else {
            return this;
        }
    }

   /**
    * // Vai mostrar o valor mais alto presente na árvore 
    * a partir do Nodo que está rodando no momento
    * @return 
    */
    public No sucessor() {
        if (!getEsquerdo().equals(Arvore.sentinela)) {
            return getDireito().minimo();
        } else {
            return this;
        }
    }

    /**
     * @param
     */
    public No<T> getPai() {
        return pai;
    }

    /**
     * @param
     */
    public void setPai(No pai) {
        this.pai = pai;
    }

    /**
     * @return pai
     */
    public No<T> getDireito() {
        return direito;
    }

    /**
     * @param direito
     */
    public void setDireito(No direito) {
        this.direito = direito;
    }

    /**
     * @return
     */
    public No<T> getEsquerdo() {
        return esquerdo;
    }

    /**
     * @param esquerdo
     */
    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    /**
     * @return
     */
    public T getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * @return
     */
    public boolean getCor() {
        return this.cor;
    }

    /**
     * Se for falso é igual a cor preto
     * <p>
     * Se for verdadeiro e igual a cor vermelha
     *
     * @param
     */
    public void setCor(boolean cor) {
        this.cor = cor;
    }

    public boolean equals(Object obj) {
        if (obj instanceof No) {
            No x = (No) obj;
            if (x.getInfo() != null && getInfo() != null) {
                return (getClass() == x.getClass() && getInfo().compareTo(x.getInfo()) == 0);
            }
        }
        return super.equals(obj);
    }

}
