/**
 * name : kutman
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class MonthlyReport {
    HashMap<Integer, ArrayList<ItemMonthReport>> monthReport = new HashMap<>();

    public void saveMonthlyReport() {
        for (int i = 1; i < 4; i++) {
            String path = "src/m.20210" + i + ".csv";
            ArrayList<ItemMonthReport> monthItems = new ArrayList<>();
            List<String> lines = readFileContents(path);
            if (!lines.isEmpty()) {
                for (int j = 1; j < lines.size(); j++) {
                    String[] parts = lines.get(j).split(",");
                    String itemName = parts[0];
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);
                    ItemMonthReport itemMonthReport = new ItemMonthReport(itemName, isExpense, quantity, sumOfOne);
                    monthItems.add(itemMonthReport);
                }
                monthReport.put(i, monthItems);
            }
        }
    }
    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
    public int getExpensesSum(int month){
        int expenseSum = 0;
        ArrayList<ItemMonthReport> monthReports = monthReport.get(month);
        for (ItemMonthReport monthReport : monthReports) {
            if (monthReport.isExpense) {
                expenseSum += monthReport.quantity * monthReport.sumOfOne;
            }
        }
        return expenseSum;
    }

    public void getTopProduct(int monthNumber){
        String maxItemName = null;
        int maxProfit = 0;
        HashMap<String, Integer> freqs = new HashMap<>();
        ArrayList<ItemMonthReport> monthReports = monthReport.get(monthNumber);
        for(ItemMonthReport monthReport : monthReports) {
            if(monthReport.isExpense == true) {
                freqs.put(monthReport.itemName, freqs.getOrDefault(monthReport.itemName, 0) + monthReport.quantity * monthReport.sumOfOne);
            }
            if (!freqs.isEmpty()){
                for (String itemName : freqs.keySet()) {
                    if (maxItemName == null) {
                        maxItemName = itemName;
                        maxProfit = freqs.get(itemName);
                        continue;
                    }
                    if (freqs.get(maxItemName) < freqs.get(itemName)) {
                        maxItemName = itemName;
                        maxProfit = freqs.get(itemName);
                    }
                }
            }else {
                System.out.println("map пустой");
            }
        }
        System.out.println("Самый прибыльный товар в месяце " + monthNumber + ": " + maxItemName + ". Сумма: " + maxProfit);
    }
    public void printMaxExpense(int monthNumber){
        String maxItemName = null;
        int maxExpense = 0;
        HashMap<String, Integer> freqs = new HashMap<>();
        ArrayList<ItemMonthReport> monthReports = monthReport.get(monthNumber);
        for(ItemMonthReport monthReport : monthReports) {
            if(monthReport.isExpense != false) {
                freqs.put(monthReport.itemName, freqs.getOrDefault(monthReport.itemName, 0) + monthReport.quantity*monthReport.sumOfOne);

            }
        }
        if (!freqs.isEmpty()) {
            for (String itemName : freqs.keySet()) {
                if (maxItemName == null) {
                    maxItemName = itemName;
                    maxExpense = freqs.get(itemName);
                    continue;
                }
                if (freqs.get(maxItemName) < freqs.get(itemName)) {
                    maxItemName = itemName;
                    maxExpense = freqs.get(itemName);
                }
            }
        }else {
            System.out.println("map пустой");
        }
        System.out.println("Самая большая трата в месяце " + monthNumber + ": " + maxItemName + ". Сумма: " + maxExpense);
    }

}
