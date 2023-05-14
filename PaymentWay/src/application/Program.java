package application;

import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println();
        System.out.println("Entre com os dados do contrato:");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(),frmt);
        System.out.print("Valor do Contrato: ");
        double totalValue = sc.nextDouble();
        System.out.print("Quantidade de parcelas: ");
        int months = sc.nextInt();

        Contract contract = new Contract(number, date, totalValue);
        ContractService service = new ContractService(new PaypalService(), months);

        for (int i = 1; i < (months + 1); i++) {
           service.processContract(contract);
        }

        System.out.println();
        System.out.println("PARCELAS:");
        for (int i = 0; i < months; i++) {
            System.out.println(service.getInstallmentList().get(i));
        }

        System.out.println();
        sc.close();
    }
}
