package theater;

/**
 * Base class responsible for calculating amount and volume credits
 * for a single performance.
 */
public abstract class AbstractPerformanceCalculator {

    private final Performance performance;
    private final Play play;

    protected AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    protected Performance getPerformance() {
        return performance;
    }

    protected Play getPlay() {
        return play;
    }

    /**
     * Returns the amount owed for this performance, in cents.
     *
     * @return amount in cents
     */
    public abstract int amountFor();

    /**
     * Returns the base volume credits earned for this performance.
     *
     * @return volume credits
     */
    public int volumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }

    /**
     * Factory method that creates the appropriate calculator for a performance and play.
     *
     * @param performance the performance
     * @param play        the play
     * @return a calculator instance for this play type
     * @throws RuntimeException if the play type is unknown
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            default:
                throw new RuntimeException(String.format("unknown type: %s", play.getType()));
        }
    }
}
