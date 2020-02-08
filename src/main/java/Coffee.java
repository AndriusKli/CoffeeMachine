public enum Coffee {

    ESPRESSO(15,0),
    CAPUCCINO(20, 30),
    AMERICANO(20, 0),
    LATTE(20, 60),
    MACHIATO(20, 5),
    DOPIO(35, 0),
    PURE_CAFFEINE(200, 0),
    JUST_MILK(0, 100);

    private final int beans;
    private final int milk;

    Coffee(int beans, int milk) {
        this.beans = beans;
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getMilk() {
        return milk;
    }
}
