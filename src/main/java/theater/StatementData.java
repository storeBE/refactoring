package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Holds all derived data needed to render a statement for an invoice.
 */
public class StatementData {

    private final String customer;
    private final List<PerformanceData> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        this.performances = new ArrayList<>();
        for (Performance performance : invoice.getPerformances()) {
            final Play play = plays.get(performance.getPlayID());
            final AbstractPerformanceCalculator calculator =
                    AbstractPerformanceCalculator.createPerformanceCalculator(performance, play);
            this.performances.add(new PerformanceData(
                    performance,
                    play,
                    calculator.amountFor(),
                    calculator.volumeCredits()));
        }
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Computes the total amount across all performances.
     *
     * @return total amount in cents
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getAmount();
        }
        return result;
    }

    /**
     * Computes the total volume credits across all performances.
     *
     * @return total volume credits
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getVolumeCredits();
        }
        return result;
    }
}
