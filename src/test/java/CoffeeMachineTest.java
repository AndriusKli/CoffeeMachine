import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CoffeeMachineTest {

    private CoffeeMachine coffee;

    @BeforeEach
    public void init() {
        this.coffee = new CoffeeMachine();
    }

    @Test
    public void testOn() {
        assertThat(coffee.isOn(), is(false));
        coffee.togglePower();
        assertThat(coffee.isOn(), is(true));
        coffee.togglePower();
        assertThat(coffee.isOn(), is(false));
    }

    @Test
    public void testCantMakeCoffeeWhileOff() {
        assertThrows(IllegalStateException.class, () -> coffee.makeCoffee(Coffee.ESPRESSO));
        coffee.togglePower();
        assertThat(coffee.makeCoffee(Coffee.ESPRESSO), is(true));
    }

    @Test
    public void testRemovesBeansAndMilk() {
        coffee.togglePower();
        coffee.makeCoffee(Coffee.ESPRESSO);
        assertThat(coffee.getBeans(), is(equalTo(485)));
        assertThat(coffee.getMilk(), is(equalTo(350)));
        coffee.makeCoffee(Coffee.LATTE);
        assertThat(coffee.getBeans(), is(equalTo(465)));
        assertThat(coffee.getMilk(), is(equalTo(290)));
    }

    @Test
    public void testRefillWorks() {
        coffee.togglePower();
        coffee.makeCoffee(Coffee.LATTE);
        coffee.makeCoffee(Coffee.LATTE);
        coffee.refillBeans();
        coffee.refillMilk();
        assertThat(coffee.getBeans(), is(equalTo(coffee.getDEFAULT_BEANS())));
        assertThat(coffee.getMilk(), is(equalTo(coffee.getDEFAULT_MILK())));
    }

    @Test
    public void testEnoughMilkAndBeans() {
        coffee.togglePower();
        coffee.makeCoffee(Coffee.PURE_CAFFEINE);
        coffee.makeCoffee(Coffee.PURE_CAFFEINE);
        assertThrows(InsuficientBeansOrMilkException.class, () -> coffee.makeCoffee(Coffee.PURE_CAFFEINE));
        coffee.clean();
        coffee.makeCoffee(Coffee.JUST_MILK);
        coffee.makeCoffee(Coffee.JUST_MILK);
        coffee.makeCoffee(Coffee.JUST_MILK);
        assertThrows(InsuficientBeansOrMilkException.class, () -> coffee.makeCoffee(Coffee.JUST_MILK));
    }

    @Test
    public void testNeedsCleaning() {
        coffee.togglePower();
        coffee.makeCoffee(Coffee.ESPRESSO);
        coffee.makeCoffee(Coffee.ESPRESSO);
        coffee.makeCoffee(Coffee.ESPRESSO);
        coffee.makeCoffee(Coffee.ESPRESSO);
        coffee.makeCoffee(Coffee.ESPRESSO);
        assertThrows(NeedsCleaningException.class, () ->  coffee.makeCoffee(Coffee.ESPRESSO));
    }
}

