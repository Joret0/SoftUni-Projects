package app_test;

import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.StrategyHolder;
import app_test.fakeClassesForTests.FakeDisposable;
import app_test.fakeClassesForTests.FakeNonDisposable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

public class StrategyHolderTests {
    private StrategyHolder strategyHolder;

    private GarbageDisposalStrategy mockedStrategy;
    private Class disposableAnnotationClass;
    private Class nonDisposableAnnotationClass;

    @Before
    public void initialize() {
        this.strategyHolder = new DefaultStrategyHolder();
        this.mockedStrategy = Mockito.mock(GarbageDisposalStrategy.class);
        this.disposableAnnotationClass = FakeDisposable.class;
        this.nonDisposableAnnotationClass = FakeNonDisposable.class;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void returnAReadOnlyCollection() {
        Map<Class, GarbageDisposalStrategy> strategies = this.strategyHolder.getDisposalStrategies();
        strategies.put(this.disposableAnnotationClass, this.mockedStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfPassedNonDisposableAnnotation() {
        this.strategyHolder.addStrategy(this.nonDisposableAnnotationClass, this.mockedStrategy);
    }

    @Test
    public void returnFalseWhenPassedExistingStrategy() {
        this.strategyHolder.addStrategy(this.disposableAnnotationClass, this.mockedStrategy);
        boolean result = this.strategyHolder.addStrategy(this.disposableAnnotationClass, this.mockedStrategy);

        Assert.assertEquals("Operation returned incorrect result!", false, result);
    }

    @Test
    public void addStrategyShouldWorkCorrect() {
        Map<Class, GarbageDisposalStrategy> strategies = this.strategyHolder.getDisposalStrategies();

        this.strategyHolder.addStrategy(this.disposableAnnotationClass, this.mockedStrategy);

        GarbageDisposalStrategy result = strategies.get(this.disposableAnnotationClass);
        GarbageDisposalStrategy expected = this.mockedStrategy;
        Assert.assertEquals("Expected strategy did not match!", expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenRemovingNonDisposable() {
        this.strategyHolder.removeStrategy(this.nonDisposableAnnotationClass);
    }

    @Test
    public void shouldReturnFalseWhenRemovingFromEmptyHolder() {
        boolean result = this.strategyHolder.removeStrategy(this.disposableAnnotationClass);

        Assert.assertEquals("Operation returned incorrect result!", false, result);
    }
}
