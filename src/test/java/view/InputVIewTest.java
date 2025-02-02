package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputVIewTest {

	@Test
	@DisplayName("구매금액 : 문자를 입력 한 경우 예외 발생")
	void MoneyMustBeInteger() {
		setInput("wooteco");
		assertThatThrownBy(InputView::inputMoney)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 양의 정수만 허용됩니다.");
	}

	@Test
	@DisplayName("구매금액 : 빈 문자를 입력 한 경우 예외 발생")
	void MoneyMustNotBeEmpty() {
		setInput("\n");
		assertThatThrownBy(InputView::inputMoney)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 빈 입력일 수 없습니다.");
	}

	@Test
	@DisplayName("수동으로 구매할 로또 수 : 문자를 입력 한 경우 예외 발생")
	void manualCountMustBeInteger() {
		setInput("wooteco");
		assertThatThrownBy(InputView::inputmanualCount)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 양의 정수만 허용됩니다.");
	}

	@Test
	@DisplayName("수동으로 구매할 로또 수 : 빈 문자를 입력 한 경우 예외 발생")
	void manualCountMustNotBeEmpty() {
		setInput("\n");
		assertThatThrownBy(InputView::inputmanualCount)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 빈 입력일 수 없습니다.");
	}

	@Test
	@DisplayName("수동으로 구매할 로또 번호 : 빈 문자를 입력 한 경우 예외 발생")
	void manualMustNotBeEmpty() {
		setInput("\n");
		assertThatThrownBy(() -> InputView.inputManualNumbers(1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 빈 입력일 수 없습니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,6", "1-2-3-4-5-6", "1 2 3 4 5 6"})
	@DisplayName("수동으로 구매할 로또 번호 : 입력 형식과 다르게 입력 한 경우 예외 발생")
	void manualMustNotBeEmpty(String input) {
		setInput(input);
		assertThatThrownBy(() -> InputView.inputManualNumbers(1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력 형식에 맞춰 입력해주세요.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"wooteco", "-100"})
	@DisplayName("지난주 당첨번호 : 0이상의 정수를 입력하지 않은 경우 예외 발생")
	void answerNumberMustBeInteger(String input) {
		String inputs = "1, 2, 3, 4, 5, " + input;
		setInput(inputs);
		assertThatThrownBy(InputView::inputAnswerNumbers)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 양의 정수만 허용됩니다.");
	}

	@Test
	@DisplayName("지난주 당첨 번호 : 빈 문자를 입력 한 경우 예외 발생")
	void answerNumberMustNotBeEmpty() {
		setInput("\n");
		assertThatThrownBy(InputView::inputAnswerNumbers)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 빈 입력일 수 없습니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,6", "1-2-3-4-5-6", "1 2 3 4 5 6"})
	@DisplayName("지난주 당첨 번호 : 입력 형식과 다르게 입력 한 경우 예외 발생")
	void wrongStandardOfAnswerNumbers(String input) {
		setInput(input);
		assertThatThrownBy(InputView::inputAnswerNumbers)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력 형식에 맞춰 입력해주세요.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"wooteco", "-100"})
	@DisplayName("보너스 번호 : 0 이상의 정수를 입력하지 않은 경우 예외 발생")
	void bonusNumberMustBeInteger(String input) {
		setInput(input);
		assertThatThrownBy(InputView::inputBonusNumber)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 양의 정수만 허용됩니다.");
	}

	@Test
	@DisplayName("보너스 번호 : 빈 문자를 입력 한 경우 예외 발생")
	void bonusNumberMustNotBeEmpty() {
		setInput("\n");
		assertThatThrownBy(InputView::inputBonusNumber)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("입력은 빈 입력일 수 없습니다.");
	}

	void setInput(String input) {
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
	}
}
