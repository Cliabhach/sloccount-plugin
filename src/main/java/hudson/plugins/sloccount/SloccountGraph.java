package hudson.plugins.sloccount;

import hudson.util.Graph;
import org.jfree.chart.JFreeChart;

import java.util.concurrent.Callable;

import static hudson.plugins.sloccount.SloccountProjectAction.CHART_HEIGHT;
import static hudson.plugins.sloccount.SloccountProjectAction.CHART_WIDTH;

/**
 * Basic approximation of {@link hudson.tasks.junit.History.GraphImpl}, adapted for Sloccount.
 *
 * @author cliabhach
 */
public class SloccountGraph extends Graph {

    private final Callable<JFreeChart> chartCall;

    /*package-private*/ SloccountGraph(Callable<JFreeChart> chartCall) {
        super(-1, CHART_WIDTH, CHART_HEIGHT);
        this.chartCall = chartCall;
    }

    protected JFreeChart createGraph() {
        try {
            // We don't expect a checked exception to be thrown here
            return chartCall.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
