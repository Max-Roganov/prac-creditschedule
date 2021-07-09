package ru.neoflex.roganov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.roganov.model.CreditParams;
import ru.neoflex.roganov.model.CreditPayment;
import ru.neoflex.roganov.model.CreditPayments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("schedule")
public class ServiceController {
    @GetMapping("annuity")
    public CreditPayments getAnnuitySchedule(@RequestBody CreditParams creditParams) {
        List<CreditPayment> schedule = new ArrayList<>();
        double ratePerMonth = creditParams.getRate() / 100.0 / 12;
        double paymentPerMoth = creditParams.getSum() * (ratePerMonth + ratePerMonth / (Math.pow(1 + ratePerMonth, creditParams.getTerm()) - 1));

        CreditPayment creditPayment = new CreditPayment(
                1,
                new GregorianCalendar(
                        creditParams.getDate().get(Calendar.YEAR),
                        creditParams.getDate().get(Calendar.MONTH),
                        creditParams.getDate().get(Calendar.DAY_OF_MONTH)),
                paymentPerMoth,
                paymentPerMoth - creditParams.getSum() * ratePerMonth,
                creditParams.getSum() * ratePerMonth,
                creditParams.getSum() - (paymentPerMoth - creditParams.getSum() * ratePerMonth)
        );
        creditPayment.getDate().add(Calendar.MONTH, 1);
        creditPayment.getDate().add(Calendar.DAY_OF_MONTH, 1);
        schedule.add(creditPayment);

        for (int i = 2; i <= 24; ++i) {
            creditPayment = new CreditPayment(
                    i,
                    new GregorianCalendar(
                            schedule.get(i - 2).getDate().get(Calendar.YEAR),
                            schedule.get(i - 2).getDate().get(Calendar.MONTH),
                            schedule.get(i - 2).getDate().get(Calendar.DAY_OF_MONTH)),
                    paymentPerMoth,
                    paymentPerMoth - schedule.get(i - 2).getBalance() * ratePerMonth,
                    schedule.get(i - 2).getBalance() * ratePerMonth,
                    schedule.get(i - 2).getBalance() - (paymentPerMoth - schedule.get(i - 2).getBalance() * ratePerMonth)
            );
            creditPayment.getDate().add(Calendar.MONTH, 1);
            schedule.add(creditPayment);
        }
        return new CreditPayments(schedule);
    }

    @GetMapping("differential")
    public CreditPayments getDifferentialSchedule(@RequestBody CreditParams creditParams) {
        List<CreditPayment> schedule = new ArrayList<>();
        double ratePerMonth = creditParams.getRate() / 100.0 / 12;
        double principalPerMoth = creditParams.getSum() / (double)creditParams.getTerm();

        CreditPayment creditPayment = new CreditPayment(
                1,
                new GregorianCalendar(
                        creditParams.getDate().get(Calendar.YEAR),
                        creditParams.getDate().get(Calendar.MONTH),
                        creditParams.getDate().get(Calendar.DAY_OF_MONTH)),
                principalPerMoth + creditParams.getSum() * ratePerMonth,
                principalPerMoth,
                creditParams.getSum() * ratePerMonth,
                creditParams.getSum() - principalPerMoth
        );
        creditPayment.getDate().add(Calendar.MONTH, 1);
        creditPayment.getDate().add(Calendar.DAY_OF_MONTH, 1); 


        schedule.add(creditPayment);

        for (int i = 2; i <= 24; ++i) {
            creditPayment = new CreditPayment(
                    i,
                    new GregorianCalendar(
                            creditParams.getDate().get(Calendar.YEAR),
                            creditParams.getDate().get(Calendar.MONTH),
                            creditParams.getDate().get(Calendar.DAY_OF_MONTH)),
                    principalPerMoth + schedule.get(i - 2).getBalance() * ratePerMonth,
                    principalPerMoth,
                    schedule.get(i - 2).getBalance() * ratePerMonth,
                    schedule.get(i - 2).getBalance() - principalPerMoth
            );
            creditPayment.getDate().add(Calendar.MONTH, 1);
            schedule.add(creditPayment);
        }
        return new CreditPayments(schedule);
    }
}
