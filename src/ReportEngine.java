/**
    name : kutman
    **/
public class ReportEngine {
    public YearlyReport yearlyReport;
    public MonthlyReport monthlyReport;

    public ReportEngine(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        this.yearlyReport = yearlyReport;
        this.monthlyReport = monthlyReport;
    }

    public void check() {
        boolean isEmptyMonthReport = monthlyReport.monthReport.isEmpty();
        boolean isEmptyYearReport = yearlyReport.items.isEmpty();
        if (isEmptyYearReport || isEmptyMonthReport) {
            System.out.println("Ошибка! Перед сверкой необходимо считать годовой и месячные отчеты.");
        } else {
            for (int i = 1; i < 4; i++) {
                if ((yearlyReport.getExpenseMonth(i) != monthlyReport.getExpensesSum(i)) || (yearlyReport.getExpenseMonth(i) != monthlyReport.getExpensesSum(i))) {
                    System.out.println("Найдена ошибка! Данные за " + i + " месяц в годовом и месячном отчете не совпадают.");
                }
            }
            System.out.println("Проверка завершена. Ошибок не обнаружено!");
        }
    }
}
