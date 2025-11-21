package theater;

/**
 * Holds derived data for a single performance, including its play.
 */
public class PerformanceData {

    private final Performance performance;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public PerformanceData(Performance performance, Play play, int amount, int volumeCredits) {
        this.performance = performance;
        this.play = play;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    public String getName() {
        return play.getName();
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public String getType() {
        return play.getType();
    }

    public Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    /**
     * Returns the amount owed for this performance, in cents.
     *
     * @return amount in cents
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the volume credits earned for this performance.
     *
     * @return volume credits
     */
    public int getVolumeCredits() {
        return volumeCredits;
    }
}
