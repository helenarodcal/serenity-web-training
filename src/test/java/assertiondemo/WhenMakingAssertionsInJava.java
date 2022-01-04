package assertiondemo;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class WhenMakingAssertionsInJava {

    private int age = 21;
    private List<Integer> ages = Arrays.asList(10, 20, 21, 30);

    /**
     * Test using junit assertions
     */
    @Test
    public void traditionalAssertions() {

        assertEquals(21, age); // reads badly
        assertTrue(ages.contains(age)); //message is not clear: does not state the reason for failing
        assertTrue("The list of ages should contain " + age, ages.contains(age)); //message can be added to have a clear report. but it requires maintenance
    }

    /**
     * Test using assertj assertions
     * More types of assertions, depending on the object to be checked
     * Very strong collections' assertion capabilities (e.g. allMatch)
     * Assertions can be chained
     * More clear messages
     * Soft Assertions available
     */
    @Test
    public void assertJAssertions() {
        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(age).isEqualTo(21);
        softAssert.assertThat(age).isGreaterThanOrEqualTo(10);

        //collections
        softAssert.assertThat(ages).contains(age); //very explicative message
        softAssert.assertThat(ages)
                .hasSize(4)
                .allMatch(
                a -> a >= 0 && a <= 100
        );

        softAssert.assertAll();
    }
}
