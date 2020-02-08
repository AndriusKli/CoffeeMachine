public class CoffeeMachine {
    private int beans;
    private int milk;
    private boolean on;
    private final int DEFAULT_BEANS = 500;
    private final int DEFAULT_MILK = 350;
    private int needsCleaningIn = 5;


    public CoffeeMachine() {
        this.beans = DEFAULT_BEANS;
        this.milk = DEFAULT_MILK;
        this.on = false;
    }

    public void togglePower() {
        on = !on;
    }

    public int getBeans() {
        return beans;
    }

    public int getMilk() {
        return milk;
    }

    public boolean isOn() {
        return on;
    }

    public int getDEFAULT_BEANS() {
        return DEFAULT_BEANS;
    }

    public int getDEFAULT_MILK() {
        return DEFAULT_MILK;
    }

    public void refillBeans() {
        this.beans = DEFAULT_BEANS;
    }

    public void refillMilk() {
        this.milk = DEFAULT_MILK;
    }

    public void clean() {
        this.needsCleaningIn = 5;
    }

    public boolean makeCoffee(Coffee coffee) {
        if (on) {
            if (needsCleaningIn > 0) {
                if (this.milk >= coffee.getMilk() && this.getBeans() >= coffee.getBeans()) {
                    this.milk -= coffee.getMilk();
                    this.beans -= coffee.getBeans();
                    System.out.println("Ding! Your " + coffee.toString().toLowerCase() + " is ready.");
                    needsCleaningIn--;
                    return true;
                }
                throw new InsuficientBeansOrMilkException();
            } else {
                throw new NeedsCleaningException();
            }
        } else {
            throw new IllegalStateException("The coffee machine is not on!");
        }
    }
}
