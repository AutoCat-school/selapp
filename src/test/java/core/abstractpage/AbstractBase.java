package core.abstractpage;

import core.report.Report;
import core.utilities.Utils;

public abstract class AbstractBase {

    public void sleep(int miliSeconds) {
        Utils.sleep(miliSeconds);
    }

    public void sleepInSecond(int seconds) {
        this.sleep(seconds * 1000);
    }

    public void println(String log) {
        Report.println(log);
    }
}