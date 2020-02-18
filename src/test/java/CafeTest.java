import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CafeTest {

    public static final int ESPRESSO_BEANS = CoffeeType.Espresso.getRequiredBeans();
    public static final int LATTE_BEANS = CoffeeType.Latte.getRequiredBeans();
    public static final int LATTE_MILK = CoffeeType.Latte.getRequiredMilk();
    public static final int CAPPUCCINO_BEANS = CoffeeType.Cappuccino.getRequiredBeans();
    public static final int CAPPUCCINO_MILK = CoffeeType.Cappuccino.getRequiredMilk();
    public static final int TEA_MILK = CoffeeType.Tea.getRequiredMilk();
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
        withEspressoBeans();

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
        withEspressoBeans();

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

    @Test
    public void canBrewCappuccino(){

        //Given  clause
        withCappuccinoBeans();
        cafe.restockMilk(CoffeeType.Cappuccino.getRequiredMilk());

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Cappuccino);

        //Then clause
        Assert.assertEquals("Wrong coffee type", CoffeeType.Cappuccino, coffee.getCoffeeType());
        Assert.assertEquals("Wrong amount of milk", CAPPUCCINO_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans", CAPPUCCINO_BEANS, coffee.getBeans());
        //another way to validate the property by using HamcCrest
        assertThat(coffee, hasProperty("beans", equalTo(CAPPUCCINO_BEANS)));
    }

    @Test
    public void canBrewTea(){

        //Given  clause
        cafe.restockMilk(CoffeeType.Tea.getRequiredMilk());

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Tea);

        //Then clause
        Assert.assertEquals("Wrong coffee type", CoffeeType.Tea, coffee.getCoffeeType());
        Assert.assertEquals("Wrong amount of milk", TEA_MILK, coffee.getMilk());
        Assert.assertEquals("Wrong number of beans", NO_BEANS, coffee.getBeans());
        //another way to validate the property by using HamcCrest
        assertThat(coffee, hasProperty("beans", equalTo(NO_BEANS)));
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

    private void withEspressoBeans() {
        cafe.restockBeans(ESPRESSO_BEANS);
    }

    private void withLatteBeans(){
        cafe.restockBeans(LATTE_BEANS);
    }

    private void withCappuccinoBeans(){
        cafe.restockBeans(CAPPUCCINO_BEANS);
    }
}
