import org.junit.Assert;
import org.junit.Test;

public class CafeTest {

    public static final int ESPRESSO_BEANS = CoffeeType.Espresso.getRequiredBeans();
    public static final int LATTE_BEANS = CoffeeType.Latte.getRequiredBeans();
    public static final int LATTE_MILK = CoffeeType.Latte.getRequiredMilk();
    public static final int NO_MILK = 0;
    public static final int NO_BEANS = 0;

    @Test
    public void canBrewEspresso(){

        //Given  clause
        Cafe cafe = cafeWithBeans(ESPRESSO_BEANS);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then clause
        Assert.assertEquals("Wrong coffee type", CoffeeType.Espresso, coffee.getCoffeeType());
        Assert.assertEquals("Wrong amount of milk", NO_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans", ESPRESSO_BEANS, coffee.getBeans());
    }

    @Test
    public void brewingEspressoConsumesBeans(){

        //Given  clause
        Cafe cafe = cafeWithBeans(ESPRESSO_BEANS);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then clause
        Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
    }

    @Test
    public void canBrewLatte(){

        //Given  clause
        Cafe cafe = cafeWithBeans(LATTE_BEANS);
        cafe.restockMilk(LATTE_MILK);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        //Then clause
        Assert.assertEquals("Wrong coffee type", CoffeeType.Latte, coffee.getCoffeeType());
        Assert.assertEquals("Wrong amount of milk",LATTE_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans",LATTE_BEANS, coffee.getBeans());
    }

    @Test
    public void brewingLatteConsumesBeansAndMilk(){

        //Given  clause
        Cafe cafe = cafeWithBeans(LATTE_BEANS);
        cafe.restockMilk(CoffeeType.Latte.getRequiredMilk());

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        //Then clause
        Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
        Assert.assertEquals(NO_MILK, cafe.getMilkInStock());
    }

    //Then clause
    @Test(expected = IllegalStateException.class)
    public void lattesRequiresMilk(){

        //Given  clause
        Cafe cafe = cafeWithBeans(LATTE_BEANS);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Latte);
    }

    //Then clause
    @Test(expected =  IllegalArgumentException.class)
    public void mustRestockMilk(){

        //Given
        Cafe cafe = new Cafe();

        //When
        cafe.restockMilk(NO_MILK);
    }

    //Then clause
    @Test(expected =  IllegalArgumentException.class)
    public void mustRestockBeans(){

        //Given
        Cafe cafe = cafeWithBeans(NO_BEANS);
    }

    private Cafe cafeWithBeans(int i) {
        Cafe cafe = new Cafe();
        cafe.restockBeans(i);
        return cafe;
    }
}
