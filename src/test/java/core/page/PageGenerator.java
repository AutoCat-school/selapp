package core.page;

import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

public class PageGenerator {
    private WebDriver driver;
    private Map<Class<? extends AbstractDriverIO>, AbstractDriverIO> pageCache;

    public PageGenerator(WebDriver driver) {
        this.driver = driver;
        this.pageCache = new HashMap<>();
    }

    public <TPage extends AbstractDriverIO> TPage getPage(Class<TPage> pageClass) {
        if (!pageCache.containsKey(pageClass)) {
            try {
                TPage page = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
                this.pageCache.put(pageClass, page);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create page instance: " + pageClass.getSimpleName(), e);
            }
        }
        return pageClass.cast(pageCache.get(pageClass));
    }
}
