/**
 * name : kutman
 **/

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
public class YearlyReport {
    public ArrayList<ItemYearReport> items = new ArrayList<>();
    public void saveYearlyReport (String path) {

        List<String> lines = readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            int monthNumber = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            ItemYearReport itemYearReport = new ItemYearReport(monthNumber, amount, isExpense);
            items.add(itemYearReport);
        }
    }
    List<String>  readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
    public int getExpenseMonth (int monthNum){
        int expenseMonth = 0;
        for (ItemYearReport item : items) {
            if (item.monthNumber == monthNum && item.isExpense) {
                expenseMonth = item.amount;
            }
        }
        return expenseMonth;
    }
//    public int getIncomeMonth (int monthNum){
//        int incomeMonth = 0;
//        for (ItemYearReport item : items) {
//            if (item.monthNumber == monthNum && !item.isExpense) {
//                incomeMonth = item.amount;
//            }
//        }
//        return incomeMonth;
//    }
    public int averageExpenses(){
        int averageExpense;
        int expensesSum = 0;
        for (ItemYearReport item : items) {
            if(item.isExpense) {
                expensesSum += item.amount;
            }
        }
        averageExpense = expensesSum / 3;
        return averageExpense;
    }
//    public int averageIncomes(){
//        int averageIncome;
//        int incomesSum = 0;
//        for (ItemYearReport item : items) {
//            if(!item.isExpense) {
//                incomesSum += item.amount;
//            }
//        }
//        averageIncome = incomesSum / 3;
//        return averageIncome;
//    }
    public int getProfitFromMonth(int monthNum){
        int profit = 0;
        for (ItemYearReport item : items) {
            if (item.monthNumber == monthNum) {

                if (!item.isExpense) {
                    profit += item.amount;
                } else {
                    profit -= item.amount;
                }
            }
        }
        return profit;
    }
}
