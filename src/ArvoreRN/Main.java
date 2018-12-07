package ArvoreRN;




import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        Arvore a = new Arvore();

        int menu = 0;
        int num = 0;
        do {
            System.out.println("DIGITE A OPÇÃO:");
            System.out.println("  1                       ADICIONAR");
            System.out.println("  2                       EXCLUIR");
            System.out.println("  3                       ALTURA NEGRA");
            System.out.println("  4                       PROCURAR");
            System.out.println("  5                       IMPRIMIR");
            System.out.println("  0                       SAIR");
            menu = leia.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("Digite um número para adicionar");
                    num = leia.nextInt();
                    try {
                        a.adicionar(num);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Digite um número para excluir");
                    num = leia.nextInt();
                    try {
                        a.remover(num);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 3:
                    System.out.println("Altura negra da arvore é: " + a.alturaNegra());
                    break;
                case 4:
                    System.out.println("Digite um número para procurar");
                    num = leia.nextInt();
                    try {
                        No no = a.buscar(num);
                        System.out.println("Nó encontrado");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 5:
                    a.imprimir();
                    break;
                default:
                    System.out.println("CODIGO INVÁLIDO");
                    break;
            }

        } while (menu != 0);
    }
}
