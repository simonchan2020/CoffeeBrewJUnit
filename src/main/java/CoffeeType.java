/**
 *  Describe the type of coffee that we're going to serve
 */
public enum CoffeeType {
    Espresso(11, 0),
    Latte(6, 225),
    FilterCoffee(10, 0),
    Cappuccino(8, 100);

    private final int requiredBeans;
    private final int requiredMilk;

    CoffeeType(int requiredBeans, int requiredMilk){
        this.requiredBeans = requiredBeans;
        this.requiredMilk = requiredMilk;
    }

    public int getRequiredBeans() {
        return requiredBeans;
    }

    public int getRequiredMilk() {
        return requiredMilk;
    }
}
