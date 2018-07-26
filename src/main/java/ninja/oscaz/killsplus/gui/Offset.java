package ninja.oscaz.killsplus.gui;

import java.math.BigDecimal;

public enum Offset {

    INCREASE_HUNDREDTH(1, BigDecimal.valueOf(0.01D)), INCREASE_TENTH(3, BigDecimal.valueOf(0.1D)), INCREASE_ONE(5, BigDecimal.valueOf(1.0D)), INCREASE_TEN(7, BigDecimal.valueOf(10.0D)),
    DECREASE_HUNDREDTH(19, BigDecimal.valueOf(-0.01D)), DECREASE_TENTH(21, BigDecimal.valueOf(-0.1D)), DECREASE_ONE(23, BigDecimal.valueOf(-1.0D)), DECREASE_TEN(25, BigDecimal.valueOf(-10.0D));

    Offset(int slot, BigDecimal amount) {
        this.slot = slot;
        this.amount = amount;
    }

    private final int slot;
    private final BigDecimal amount;

    public int getSlot() { return slot; }

    public BigDecimal getAmount() { return amount; }

}
