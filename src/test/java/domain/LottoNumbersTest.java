package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumbersTest {

	private LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

	@Test
	@DisplayName("로또 번호 : 6개가 아닌 개수의 번호를 입력 한 경우 예외 발생")
	void countOfNumbersMustBeSix() {
		List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7);
		assertThatThrownBy(() -> new LottoNumbers(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개의 숫자만 허용됩니다.");
	}

	@Test
	@DisplayName("로또 번호 : 중복되는 숫자를 입력 한 경우 예외 발생")
	void duplicateInNumbers() {
		assertThatThrownBy(() -> new LottoNumbers(List.of(1, 2, 3, 4, 5, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("숫자들은 중복일 수 없습니다.");
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 0개")
	void calculateDuplicatedCount_zero() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(7, 8, 9, 10, 11, 12));
		assertThat(count).isEqualTo(0);
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 1개")
	void calculateDuplicatedCount_one() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(1, 7, 8, 9, 10, 11));
		assertThat(count).isEqualTo(1);
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 2개")
	void calculateDuplicatedCount_two() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(1, 2, 7, 8, 9, 10));
		assertThat(count).isEqualTo(2);
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 3개")
	void calculateDuplicatedCount_three() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(1, 2, 3, 7, 8, 9));
		assertThat(count).isEqualTo(3);
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 4개")
	void calculateDuplicatedCount_four() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(1, 2, 3, 4, 7, 8));
		assertThat(count).isEqualTo(4);
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 5개")
	void calculateDuplicatedCount_five() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(1, 2, 3, 4, 5, 7));
		assertThat(count).isEqualTo(5);
	}

	@Test
	@DisplayName("올바른 중복 개수를 산출하는지 확인 - 6개")
	void calculateDuplicatedCount_six() {
		int count = lottoNumbers.calculateDuplicatedCount(generateLottoNumberSet(1, 2, 3, 4, 5, 6));
		assertThat(count).isEqualTo(6);
	}

	public static Set<LottoNumber> generateLottoNumberSet(int n1, int n2, int n3, int n4, int n5, int n6) {
		return Set.of(
			new LottoNumber(n1),
			new LottoNumber(n2),
			new LottoNumber(n3),
			new LottoNumber(n4),
			new LottoNumber(n5),
			new LottoNumber(n6)
		);
	}

}
