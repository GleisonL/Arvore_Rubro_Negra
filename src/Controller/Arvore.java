package Controller;

import Model.No;

public class Arvore<T extends Comparable> {

    public No raiz;
    public static No sentinela = new No();

// ------------------------------------------------ MÉTODOS PUBLICOS ---------------------------------------------------
    public int alturaNegra() {
        No r = this.raiz;
        int cont = 0;
      
        do {
         
            if (!r.getCor() && !r.equals(sentinela)) {
                cont++;
            }
            r = r.getEsquerdo();
        } while (!r.equals(sentinela));
        return cont;
    }

    public Arvore() {

        raiz = sentinela;
    }

    public void adicionar(T k) throws Exception {
        if (raiz.equals(sentinela)) {
            // Se a árvore ainda estiver vazia, cria o no preto e o torna raiz
            this.raiz = new No(k, false);

        } else {

            No aux = raiz.busca(k);

            if (k.compareTo(aux.getInfo()) > 0) {

                No novo = new No(k, true);
                aux.setDireito(novo);

                aux.getDireito().setPai(aux);

                addFix(aux.getDireito());

            } else if (k.compareTo(aux.getInfo()) < 0) {

                No novo = new No(k, true);
                aux.setEsquerdo(novo);

                aux.getEsquerdo().setPai(aux);

                addFix(aux.getEsquerdo());
            }
        }
    }

    public No buscar(T info) throws Exception {
        No ptr = this.raiz;

        while (ptr != null) {
            if (info.compareTo(ptr.getInfo()) > 0) {
                ptr = ptr.getDireito();
            } else if (info.compareTo(ptr.getInfo()) < 0) {
                ptr = ptr.getEsquerdo();
            } else {
                return ptr;
            }
        }
        throw new IllegalArgumentException("No nao exixtente");
    }

    public void remover(T info) throws Exception {

        No z = buscar(info);
        No y = z, x;

        boolean yCorOriginal = y.getCor();

        if (z.getEsquerdo().equals(sentinela)) {

            x = z.getDireito();
            transplant(z, z.getDireito());

        } else if (z.getDireito().equals(sentinela)) {

            x = z.getEsquerdo();
            transplant(z, z.getEsquerdo());

        } else {

            y = z.sucessor();
            yCorOriginal = y.getCor();
            x = y.getDireito();
            if (y.getPai().equals(z)) {
                x.setPai(y);
            } else {
                transplant(y, y.getDireito());
                y.setDireito(z.getDireito());
                y.getDireito().setPai(y);
            }

            transplant(z, y);
            y.setEsquerdo(z.getEsquerdo());
            y.getEsquerdo().setPai(y);
            y.setCor(z.getCor());
        }
        if (yCorOriginal == false) {
            delFix(x);
        }
    }

// ------------------------------------------------ MÉTODOS PRIVADOS ---------------------------------------------------
    private void rotacaoEsquerda(No x) {
        No y = x.getDireito();
        x.setDireito(y.getEsquerdo());

        if (!y.getEsquerdo().equals(sentinela)) {
            y.getEsquerdo().setPai(x);
        }
        y.setPai(x.getPai());

        if (x.getPai().equals(sentinela)) {
            raiz = y;
        } else if (x == x.getPai().getEsquerdo()) {
            x.getPai().setEsquerdo(y);
        } else {
            x.getPai().setDireito(y);
        }
        y.setEsquerdo(x);
        x.setPai(y);
    }

    private void rotacaoDireita(No x) {
        No y = x.getEsquerdo();
        x.setEsquerdo(y.getDireito());

        if (!y.getDireito().equals(sentinela)) {
            y.getDireito().setPai(x);
        }
        y.setPai(x.getPai());

        if (x.getPai().equals(sentinela)) {
            raiz = y;
        } else if (x == x.getPai().getDireito()) {
            x.getPai().setDireito(y);
        } else {
            x.getPai().setEsquerdo(y);
        }
        y.setDireito(x);
        x.setPai(y);
    }

   /**
    * Método que auxilia na insercao
    * @param z 
    */
    private void addFix(No z) {
        No y;

        while (z.getPai().getCor()) { //Enquanto a Cor for do no for vermelha

            if (z.getPai().equals(z.getPai().getPai().getEsquerdo())) {

                y = z.getPai().getPai().getDireito();

                if (y.getCor()) { //caso 1 (tio é vermelho)
                    z.getPai().setCor(false);
                    y.setCor(false);
                    z.getPai().getPai().setCor(true);
                    z = z.getPai().getPai();

                } else {

                    if (z.equals(z.getPai().getDireito())) { // caso 2
                        z = z.getPai();
                        rotacaoEsquerda(z);
                    }

                    z.getPai().setCor(false);
                    z.getPai().getPai().setCor(true);

                    rotacaoDireita(z.getPai().getPai());
                }

            } else { //Mesma coisa, onde inverte Direito e Esquerdo
                y = z.getPai().getPai().getEsquerdo();

                if (y.getCor()) { 

                    z.getPai().setCor(false);
                    y.setCor(false);
                    z.getPai().getPai().setCor(true); 
                    z = z.getPai().getPai();

                } else {

                    if (z.equals(z.getPai().getEsquerdo())) {

                        z = z.getPai();
                        rotacaoDireita(z);
                    }

                    z.getPai().setCor(false);
                    z.getPai().getPai().setCor(true);

                    rotacaoEsquerda(z.getPai().getPai());
                }
            }
        }
        this.raiz.setCor(false);
    }

    /**
     * auxilia na remocao
     * @param n 
     */
    private void delFix(No n) {
        No x;
        while ( !n.equals(this.raiz) && !n.getCor()) {
            if (n.equals(n.getPai().getEsquerdo())) {
                x = n.getPai().getDireito();

                if (x.getCor()) {  // caso 1
                    x.setCor(false);
                    n.getPai().setCor(true);
                    rotacaoEsquerda(n.getPai());
                    x = n.getPai().getDireito();
                }

                if (!x.getEsquerdo().getCor() && !x.getDireito().getCor()) { // caso 2
                    x.setCor(true);
                    n = n.getPai();
                } else {

                    if (!x.getDireito().getCor()) {  // caso 3
                        x.getEsquerdo().setCor(false);
                        x.setCor(true);
                        rotacaoDireita(x);
                        x = n.getPai().getDireito();
                    }
                     // caso 4
                    x.setCor(n.getPai().getCor());
                    n.getPai().setCor(false);
                    x.getDireito().setCor(false);
                    rotacaoEsquerda(n.getPai());
                    n = raiz;
                }
            } else { 
                x = n.getPai().getEsquerdo();
               
                if (x.getCor()) {  // caso 1
                    x.setCor(false);
                    n.getPai().setCor(true);
                    rotacaoDireita(n.getPai());
                    x = n.getPai().getEsquerdo();
                }
                
                if (!x.getDireito().getCor() && !x.getEsquerdo().getCor()) {  // caso 2
                    x.setCor(true);
                    n = n.getPai();

                } else {

                    if (!x.getEsquerdo().getCor()) { // caso 3
                        x.getDireito().setCor(false);
                        x.setCor(true);
                        rotacaoEsquerda(x);
                        x = n.getPai().getEsquerdo();
                    }
                    // caso 4
                    x.setCor(n.getPai().getCor());
                    n.getPai().setCor(false);
                    x.getEsquerdo().setCor(false);
                    rotacaoDireita(n.getPai());
                    n = this.raiz;
                }
            }
        }
        n.setCor(false);
    }

   /**
    * Realiza troca entre os nós, sendo necessária ao se remover um no
    * para evitar perda de ponteiros
    * @param u
    * @param v 
    */
    private void transplant(No u, No v) {
        if (u.getPai().equals(sentinela)) {
            raiz = v;
        } else if (u.equals(u.getPai().getEsquerdo())) {
            u.getPai().setEsquerdo(v);
        } else {
            u.getPai().setDireito(v);
        }
        v.setPai(u.getPai());
    }

// ------------------------------------------------ MÉTODOS DE IMPRESSÃO -----------------------------------------------
    /**
     * Imprime em ordem
     */
    public void imprimir() {
        imprimir(this.raiz);
        
    }

    public void imprimir(No raiz) {

        if (!raiz.getEsquerdo().equals(sentinela)) {
            imprimir(raiz.getEsquerdo());
        }
        
           System.out.println(raiz.getInfo());
        

        if (!raiz.getDireito().equals(sentinela)) {
            imprimir(raiz.getDireito());
        }

    }
}
