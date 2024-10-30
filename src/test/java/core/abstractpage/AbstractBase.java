package core.abstractpage;

import core.report.Report;

public abstract class AbstractBase {

    public AbstractBase() {
        // nothing for now
    }

    public void sleep(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            this.println(e.getMessage());
        }
    }

    public void sleepInSecond(int seconds) {
        this.sleep(seconds * 1000);
    }

    public void println(String log) {
        Report.println(log);
    }
}