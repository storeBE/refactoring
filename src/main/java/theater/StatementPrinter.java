package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {

    private final StatementData statementData;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.statementData = new StatementData(invoice, plays);
    }

    /**
     * Returns the precomputed statement data.
     *
     * @return the statement data
     */
    protected StatementData getStatementData() {
        return statementData;
    }
    /**
     * Generates the statement string for this invoice.
     *
     * @return the formatted statement
     */

    public String statement() {
        return renderPlainText(statementData);
    }

    /**
     * Renders the statement data as plain text.
     *
     * @param data the statement data to render
     * @return the formatted text statement
     */
    protected String renderPlainText(StatementData data) {
        final StringBuilder result =
                new StringBuilder("Statement for " + data.getCustomer() + System.lineSeparator());

        for (PerformanceData performanceData : data.getPerformances()) {
            result.append(String.format("  %s: %s (%s seats)%n",
                    performanceData.getName(),
                    usd(performanceData.amountFor()),
                    performanceData.getAudience()));
        }

        result.append(String.format("Amount owed is %s%n", usd(data.totalAmount())));
        result.append(String.format("You earned %s credits%n", data.volumeCredits()));
        return result.toString();
    }

    /**
     * Formats a cent amount as US dollars.
     *
     * @param amountInCents the amount in cents
     * @return a formatted currency string
     */
    protected String usd(int amountInCents) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(amountInCents / (double) Constants.PERCENT_FACTOR);
    }
}
