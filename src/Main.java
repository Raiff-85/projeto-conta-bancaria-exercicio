import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ContaBancaria conta = new ContaBancaria("Raiff",
                "0272-5", "39226-2",0.0,
                100.00);
        System.out.println("Bem-vindo(a) ao Banco Sigma");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a agência: ");
        String agencia = scanner.next();
        System.out.println("Informe a conta: ");
        String numeroConta = scanner.next();
        scanner.nextLine();

        int opcao = 0;
        boolean continuar = true;

        do {
            System.out.println("-------------Menu-------------");
            System.out.println("1 - Consulta de saldo");
            System.out.println("2 - Depósito");
            System.out.println("3 - Saques");
            System.out.println("4 - Pagamento de boletos/títulos");
            System.out.println("5 - Cheque especial");

            System.out.println("Escolha uma das opções: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    System.out.printf("Seu saldo atual é de %.2f.\n ", conta.getSaldo());
                    break;
                case 2:
                    System.out.println("Digite o valor para depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    scanner.nextLine();
                    conta.depositar(valorDeposito);
                    System.out.printf("Seu depósito foi de %.2f.\n", valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o valor para saque: ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.printf("O valor do saque é de %.2f reais.\n", valorSaque);
                    break;
                case 4:
                    System.out.println("O pagamento do boleto/título foi realizado.");
                    break;
                case 5:
                    System.out.println("Cheque especial");
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            if (opcao != 0){
                System.out.println("Deseja realizar mais ações? (Sim ou não): ");
                String resposta = scanner.nextLine();
                if(!resposta.equals("sim") && !resposta.equals("s")){
                    continuar = false;
                }
        ;    }
        } while (continuar);

        System.out.println("\nAté breve!");
        scanner.close();
    }

}
