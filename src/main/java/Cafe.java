/**
 *  A coffee shop that can brew coffee and maintains an internal stock of beans and milk
 */
public final class Cafe {

    private int beansInStock = 0;
    private int milkInStock = 0;

    public Coffee brew(CoffeeType coffeeType){
        return brew(coffeeType, 1);
    }

    public Coffee brew(CoffeeType coffeeType, int strength){

        requirePositive(strength);
        int requireBeans = coffeeType.getRequiredBeans() * strength;
        int requireMilk = coffeeType.getRequiredMilk() * strength;
        if(requireBeans > beansInStock || requireMilk > milkInStock){
            throw new IllegalStateException();
        }

        beansInStock -= requireBeans;
        milkInStock -= requireMilk;
        return new Coffee(coffeeType, requireBeans, requireMilk);
    }

    private void requirePositive(int value) {
        if(value < 1){
            throw new IllegalArgumentException();
        }
    }

    public void restockBeans(int weightInGrams){
        requirePositive(weightInGrams);
        beansInStock += weightInGrams;
    }

    public void restockMilk(int weightInGrams){
        requirePositive(weightInGrams);
        milkInStock += weightInGrams;
    }

    public int getBeansInStock(){
        return beansInStock;
    }

    public int getMilkInStock(){
        return milkInStock;
    }
}
