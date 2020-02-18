/**
 *  An individual coffee that has been served
 */
public class Coffee {

    private final CoffeeType coffeeType;
    private final int beans;
    private final int milk;

    public Coffee(CoffeeType coffeeType, int beans, int milk) {
        this.coffeeType = coffeeType;
        this.beans = beans;
        this.milk = milk;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public int getBeans() {
        return beans;
    }

    public int getMilk() {
        return milk;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "coffeeType=" + coffeeType +
                ", beans=" + beans +
                ", milk=" + milk +
                "}";
    }
}
