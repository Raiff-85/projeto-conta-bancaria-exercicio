import java.util.Scanner;

public static void main(String[] args) {
    ContaBancaria conta = new ContaBancaria("Raiff",
            "0272-5", "39226-2", 0.0,
            300.00);

    boolean carregamentoBemSucedido = conta.carregarSaldoDeArquivo();
    if (!carregamentoBemSucedido) {
        System.err.println("AVISO INTERNO: Não foi possível carregar" +
                " o arquivo. O saldo inicial será usado.");
    }

    System.out.println("Bem-vindo(a) ao Banco Sigma!");
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

        switch (opcao) {
            case 1:
                System.out.printf("Seu saldo atual é de %.2f.\n", conta.getSaldo());
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
                System.out.println("Digite o valor do boleto: \n" +
                        "Caso o saldo seja inferior ao valor do boleto, " +
                        "o pagamento não será efetuado.");
                double valorBoleto = scanner.nextDouble();
                scanner.nextLine();

                if (conta.pagarBoleto(valorBoleto)) {
                    System.out.printf("O valor de %.2f reais foi pago com sucesso.\n", valorBoleto);
                } else {
                    System.out.println("Saldo insuficiente. Não foi possível realizar o pagamento.");
                }
                break;
            case 5:
                System.out.println("--- Cheque Especial ---");
                double valorChequeDisponivel = conta.consultarChequeEspecial();

                if (valorChequeDisponivel > 0) {
                    System.out.printf("Você possui R$ %.2f de cheque especial disponível " +
                            "para uso (de um limite total " +
                            "de R$ %.2f).\n", valorChequeDisponivel, conta.getChequeEspecial());
                } else if (conta.getSaldo() < 0) {
                        System.out.printf("Você utilizou R$ %.2f do seu " +
                                "limite de cheque especial.\n", Math.abs(conta.getSaldo()));
                }else {
                    System.out.println("Você não possui cheque especial " +
                            "disponível ou não tem limite disponível.");
                }
                break;
            case 0:
                continuar = false;
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        if (opcao != 0) {
            System.out.println("Deseja realizar mais ações? (Sim ou não): ");
            String resposta = scanner.nextLine();
            if (!resposta.equals("sim") && !resposta.equals("s")) {
                continuar = false;
            }
            ;
        }
    } while (continuar);

    System.out.println("\nAté breve!");
    scanner.close();
    conta.salvarSaldoEmArquivo();
}
