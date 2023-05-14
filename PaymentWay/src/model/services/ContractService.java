package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractService {
    private int months;
    OnlinePaymentService paymentService;
    List<Installment> installmentList = new ArrayList<>();

    public ContractService(OnlinePaymentService paymentService, int months) {
        this.paymentService = paymentService;
        this.months = months;
    }

    public List<Installment> getInstallmentList() {
        return installmentList;
    }

    public void processContract(Contract contract){
        double valueMonths = contract.getTotalValue() / months;
        int month = installmentList.size() + 1;
        double interest = paymentService.interest(valueMonths, month );
        double fee = paymentService.paymentFee(interest + valueMonths);

        installmentList.add(new Installment(contract.getDate().plusMonths(month)
                ,(valueMonths + interest + fee)));
    }
}
