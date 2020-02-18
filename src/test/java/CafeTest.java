import org.junit.Assert;
import org.junit.Test;

public class CafeTest {

    @Test
    public void canBrewEspresso(){

        //Given  clause
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then clause
        Assert.assertEquals(CoffeeType.Espresso, coffee.getCoffeeType());
        Assert.assertEquals(0, coffee.getMilk());
        Assert.assertEquals(7, coffee.getBeans());
    }

    @Test
    public void brewingEspressoConsumesBeans(){

        //Given  clause
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        //Then clause
        Assert.assertEquals(0, cafe.getBeansInStock());

    }

    @Test
    public void canBrewLatte(){

        //Given  clause
        Cafe cafe = new Cafe();
        cafe.restockBeans(8);
        cafe.restockMilk(225);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        //Then clause
        Assert.assertEquals(CoffeeType.Latte, coffee.getCoffeeType());
        Assert.assertEquals(225, coffee.getMilk());
        Assert.assertEquals(8, coffee.getBeans());
    }
    @Test
    public void brewingLatteConsumesBeansAndMilk(){

        //Given  clause
        Cafe cafe = new Cafe();
        cafe.restockBeans(8);
        cafe.restockMilk(225);

        //When clause
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        //Then clause
        Assert.assertEquals(0, cafe.getBeansInStock());
        Assert.assertEquals(0, cafe.getMilkInStock());
    }

}
