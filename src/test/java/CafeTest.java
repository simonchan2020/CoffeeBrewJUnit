import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CafeTest {

    public static final int ESPRESSO_BEANS = CoffeeType.Espresso.getRequiredBeans();
    public static final int LATTE_BEANS = CoffeeType.Latte.getRequiredBeans();
    public static final int LATTE_MILK = CoffeeType.Latte.getRequiredMilk();
    public static final int NO_MILK = 0;
    public static final int NO_BEANS = 0;

    private Cafe cafe;

    @Before
    public void before(){

        cafe = new Cafe();
    }

    @Test
    public void canBrewEspresso(){

        //Given  clause
        withBeans();

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then clause
        Assert.assertEquals("Wrong coffee type", CoffeeType.Espresso, coffee.getCoffeeType());
        Assert.assertEquals("Wrong amount of milk", NO_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans", ESPRESSO_BEANS, coffee.getBeans());
        //another way to validate the property by using HamcCrest
        assertThat(coffee, hasProperty("beans", equalTo(ESPRESSO_BEANS)));
    }

    @Test
    public void brewingEspressoConsumesBeans(){

        //Given  clause
        withBeans();

        //When clause
        cafe.brew(CoffeeType.Espresso);

        //Then clause
        Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
    }

    @Test
    public void canBrewLatte(){

        //Given  clause
        withLatteBeans();
        cafe.restockMilk(CoffeeType.Latte.getRequiredMilk());

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        //Then clause
        Assert.assertEquals("Wrong coffee type", CoffeeType.Latte, coffee.getCoffeeType());
        Assert.assertEquals("Wrong amount of milk",LATTE_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans",LATTE_BEANS, coffee.getBeans());
        //another way to validate the property by using HamcCrest
        assertThat(coffee, hasProperty("beans", equalTo(LATTE_BEANS)));
    }

    @Test
    public void brewingLatteConsumesBeansAndMilk(){

        //Given  clause
        withLatteBeans();
        cafe.restockMilk(CoffeeType.Latte.getRequiredMilk());

        //When clause
        cafe.brew(CoffeeType.Latte);

        //Then clause
        Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
        Assert.assertEquals(NO_MILK, cafe.getMilkInStock());
    }

    //Then clause
    @Test(expected = IllegalStateException.class)
    public void lattesRequiresMilk(){

        //Given  clause
        withLatteBeans();

        //When clause
        cafe.brew(CoffeeType.Latte);
    }

    //Then clause
    @Test(expected =  IllegalArgumentException.class)
    public void mustRestockMilk(){

        //When
        cafe.restockMilk(NO_MILK);
    }

    //Then clause
    @Test(expected =  IllegalArgumentException.class)
    public void mustRestockBeans(){

        //When
        cafe.restockBeans(NO_BEANS);
    }

    private void withBeans() {
        cafe.restockBeans(ESPRESSO_BEANS);
    }

    private void withLatteBeans(){
        cafe.restockBeans(LATTE_BEANS);
    }
}
