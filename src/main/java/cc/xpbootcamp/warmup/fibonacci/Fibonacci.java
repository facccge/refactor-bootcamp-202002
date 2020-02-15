package cc.xpbootcamp.warmup.fibonacci;

import java.util.List;

public class Fibonacci {
    public long calculate(int position) {
        if (position < 1 || position > 50) {
            return 0L;
        }
        if (position == 1) {
            return 1L;
        }
        return calculate(position - 1) + calculate(position - 2);
    }
}
