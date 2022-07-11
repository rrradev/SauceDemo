package steps.config;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

public class GlobalHooks extends BaseSteps implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, setup);
        eventPublisher.registerHandlerFor(TestRunFinished.class, teardown);
    }

    private EventHandler<TestRunStarted> setup = event -> {
        beforeAll();
    };

    private EventHandler<TestRunFinished> teardown = event -> {
        afterAll();
    };

    private void beforeAll() {
        setupDriver();
    }

    private void afterAll() {
        driver.quit();
    }
}
