public class ContaBancaria {
    private String titular;
    private String agencia;
    private String numeroConta;
    private double saldo;
    private double valorSaque;
    private double chequeEspecial;

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

    public double getValorSaque() {
        return valorSaque;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void consultarSaldo(){
        System.out.println("Saldo atual: " + this.saldo);
    }

    public void depositar(double valorDeposito){
        if(valorDeposito > 0){
            this.saldo += valorDeposito;
        }
    }

    public boolean sacar(double valorSaque){
        if (valorSaque > 0){
            valorSaque -= saldo;
            return false;
        } else if(this.saldo + this.chequeEspecial >= saldo){
            this.saldo -= valorSaque;
            return true;
        } else{
            return false;
        }
    }
}