/**
 * name : kutman
 **/
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner (System.in);

        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        ReportEngine reportEngine = new ReportEngine(yearlyReport, monthlyReport);
        String checkMonth = "";
        String checkYear = "";
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReport.saveMonthlyReport();
                System.out.println("Данные месячных отчетов считаны успешно");
                checkMonth="yes";
            } else if (userInput == 2) {
                yearlyReport.saveYearlyReport("src/y.2021.csv");
                System.out.println("Данные годового отчета считаны успешно");
                checkYear="yes";
            } else if (userInput == 3) {
                reportEngine.check();
            } else if (userInput == 4) {
                if(checkMonth.equals("yes")) {
                    System.out.println("Информация о месячных отчетах:");
                    for (int i = 1; i < 4; i++) {
                        monthlyReport.getTopProduct(i);
                        monthlyReport.printMaxExpense(i);
                    }
                }else {
                    System.out.println("Данные месячных отчетов не считаны ");
                }
            } else if (userInput == 5) {
                if(checkYear.equals("yes")) {
                    System.out.println("Средний расход за все месяцы в году составил: " + yearlyReport.averageExpenses());
                    System.out.println("Средний доход за все месяцы в году составил: " + yearlyReport.averageExpenses());
                    for (int i = 1; i < 4; i++) {
                        System.out.println("Прибыль за " + i + " месяц сотавила " + yearlyReport.getProfitFromMonth(i));
                    }
                }else {
                    System.out.println("Данные годового отчета не считаны ");
                }
            } else if (userInput == 0) {
                System.out.println("Выход");
                scanner.close();
                return;
            } else {
                System.out.println("Такой команды нет");
            }

        }

    }

    static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход из программы");
    }
}