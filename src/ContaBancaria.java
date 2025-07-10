import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ContaBancaria {
    private String titular;
    private String agencia;
    private String numeroConta;
    private double saldo;
    private double chequeEspecial = 300.00;

    public ContaBancaria(String titular,
                               String agencia,
                              String numeroConta,
                              double saldoInicial,
                              double chequeEspecial){
        this.titular = titular;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
        this.chequeEspecial = chequeEspecial;
    }

    public String getTitular() {
        return titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void depositar(double valorDeposito){
        if(valorDeposito > 0){
            this.saldo += valorDeposito;
        }
    }

    public boolean sacar(double valorSaque){
        if (valorSaque <= 0){
            this.saldo -= valorSaque;
            return false;
        } else if(this.saldo + this.chequeEspecial >= saldo){
            this.saldo -= valorSaque;
            return true;
        } else{
            return false;
        }
    }

    public boolean pagarBoleto(double valorBoleto) {
        if (this.saldo + this.chequeEspecial >= valorBoleto) {
            this.saldo -= valorBoleto;
            return true;
        } else {
            return false;
        }
    }

    public double consultarChequeEspecial() {
        if (this.saldo >= 0) {
            return this.chequeEspecial;
        } else {
            double chequeDisponivel = this.saldo + this.chequeEspecial;
            return Math.max(0, chequeDisponivel);
        }
    }

    public boolean salvarSaldoEmArquivo() {
        try (PrintWriter writer = new PrintWriter("saldo.txt")) {
            writer.println(this.saldo);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean carregarSaldoDeArquivo() {
        File arquivo = new File("saldo.txt");
        if(!arquivo.exists()){
            return salvarSaldoEmArquivo();
        }

        try (Scanner fileScanner = new Scanner(arquivo)) {
            if (fileScanner.hasNextDouble()) {
                this.saldo = fileScanner.nextDouble();
                return true;
            } else{
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}