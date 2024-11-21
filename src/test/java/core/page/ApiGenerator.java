package core.page;

import java.util.HashMap;
import java.util.Map;

public class ApiGenerator {
    private Map<Class<? extends AbstractApi>, AbstractApi> apiCache;

    public ApiGenerator() {
        this.apiCache = new HashMap<>();
    }

    public <TApi extends AbstractApi> TApi getApi(Class<TApi> apiClass) {
        if (!this.apiCache.containsKey(apiClass)) {
            try {
                TApi api = apiClass.getDeclaredConstructor().newInstance();
                this.apiCache.put(apiClass, api);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create API instance: " + apiClass.getSimpleName(), e);
            }
        }
        return apiClass.cast(apiCache.get(apiClass));
    }
}
