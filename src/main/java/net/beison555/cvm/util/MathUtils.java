package net.beison555.cvm.util;

public class MathUtils {
    /**
     * Subtracts from the provided number, but does not cross zero
     *
     * @param num the number
     * @param sub the amount to subtract
     * @return the resulting number
     */
    public static float subtractToZero(float num, float sub) {
        float erg;
        if (num < 0F) {
            erg = num + sub;
            if (erg > 0F) {
                erg = 0F;
            }
        } else {
            erg = num - sub;
            if (erg < 0F) {
                erg = 0F;
            }
        }

        return erg;
    }
}
