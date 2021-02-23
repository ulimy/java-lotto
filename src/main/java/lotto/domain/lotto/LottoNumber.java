package lotto.domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private static Map<Integer, LottoNumber> cacheLottoNumbers = new HashMap<>();

    private final int value;

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            cacheLottoNumbers.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        validateLottoNumber(number);
        this.value = number;
    }

    private static void validateLottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 범위를 초과했습니다.");
        }
    }

    public static LottoNumber valueOf(int index) {
        validateLottoNumber(index);
        return cacheLottoNumbers.get(index);
    }

    public static Set<Integer> getKeys() {
        return cacheLottoNumbers.keySet();
    }

    private int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return Integer.compare(this.getValue(), anotherLottoNumber.getValue());
    }
}
