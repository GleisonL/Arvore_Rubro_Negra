package ArvoreRN;


 


public class No<T extends Comparable> {

    private No pai;
    private No direito;
    private No esquerdo;
    private T info;
    private boolean cor;

    
    public No() {

        this.info = null;
        this.pai = null;
        this.direito = null;
        this.esquerdo = null;
        this.cor = false;
    }

    
    public No(T info, boolean cor) {
        this.pai = Arvore.sentinela;
        this.direito = Arvore.sentinela;
        this.esquerdo = Arvore.sentinela;
        this.info = info;
        this.cor = cor;
    }

    
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

    
    public No minimo() {
        if (!this.esquerdo.equals(Arvore.sentinela)) {
            return getEsquerdo().minimo();
        } else {
            return this;
        }
    }

   
    public No sucessor() {
        if (!getEsquerdo().equals(Arvore.sentinela)) {
            return getDireito().minimo();
        } else {
            return this;
        }
    }

    
    public No<T> getPai() {
        return pai;
    }

    
    public void setPai(No pai) {
        this.pai = pai;
    }

    
    public No<T> getDireito() {
        return direito;
    }

    
    public void setDireito(No direito) {
        this.direito = direito;
    }

    
    public No<T> getEsquerdo() {
        return esquerdo;
    }

    
    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    
    public T getInfo() {
        return info;
    }

    
    public void setInfo(T info) {
        this.info = info;
    }

    
    public boolean getCor() {
        return this.cor;
    }

    
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
