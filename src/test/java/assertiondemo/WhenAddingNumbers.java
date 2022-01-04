package assertiondemo;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenAddingNumbers {

    @Test
    public void shouldAddTwoIntegersCorectly()  {
        int a = 1;
        int b = 3;

        int sum = a + b;

        Assert.assertEquals(4, sum); //counter intuitive. reads backwards
        assertThat(sum).isEqualTo(4); // more readable

    }



}
