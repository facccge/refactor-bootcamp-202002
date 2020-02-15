package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    @Test
    void should_return_0_when_calculate_given_position_is_less_than_1() {
        Fibonacci fibonacci = new Fibonacci();

        long output = fibonacci.calculate(0);

        assertEquals(output, 0L);
    }

    @Test
    void should_return_0_when_calculate_given_position_is_greater_than_50() {
        Fibonacci fibonacci = new Fibonacci();

        long output = fibonacci.calculate(51);

        assertEquals(output, 0L);
    }

    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        Fibonacci fibonacci = new Fibonacci();

        long output = fibonacci.calculate(1);

        assertEquals(output, 1L);
    }

    @Test
    void should_return_1_when_calculate_given_position_is_2() {
        Fibonacci fibonacci = new Fibonacci();

        long output = fibonacci.calculate(2);

        assertEquals(output, 1L);
    }

    @Test
    void should_return_55_when_calculate_given_position_is_10() {
        Fibonacci fibonacci = new Fibonacci();

        long output = fibonacci.calculate(10);

        assertEquals(output, 55L);
    }

    @Test
    void should_return_12586269025L_when_calculate_given_position_is_50() {
        Fibonacci fibonacci = new Fibonacci();

        long output = fibonacci.calculate(50);

        assertEquals(output, 12586269025L);
    }
}
