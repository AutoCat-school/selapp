package core.page;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class PageGenerator {

    private WebDriver driver;
    private Map<Class<? extends AbstractDriverIO>, AbstractDriverIO> pageCache;

    // Constructor
    public PageGenerator(WebDriver driver) {
        this.driver = driver;
        this.pageCache = new HashMap<>();
    }

    // Generic method to initialize or retrieve a page from cache
    public <TPage extends AbstractDriverIO> TPage getPage(Class<TPage> pageClass) {
        if (!pageCache.containsKey(pageClass)) {
            try {
                // Create a new instance of the page and cache it
                TPage page = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
                pageCache.put(pageClass, page);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create page instance: " + pageClass.getSimpleName(), e);
            }
        }
        // Return the cached page
        return pageClass.cast(pageCache.get(pageClass));
    }
}
