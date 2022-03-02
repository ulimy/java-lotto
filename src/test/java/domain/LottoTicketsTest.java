package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

	private static Result result;

	@BeforeAll
	static void initResult() {
		LottoTickets lottoTickets = new LottoTickets();
		AnswerLotto answerLotto = AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 6), 7);
		List<LottoNumbers> lottoNumbersList = new ArrayList<>();

		lottoNumbersList.add(LottoNumbers.of(generateNumberList(8, 9, 10, 11, 12, 13)));
		lottoNumbersList.add(LottoNumbers.of(generateNumberList(1, 2, 3, 8, 9, 10)));
		lottoNumbersList.add(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 8, 9)));
		lottoNumbersList.add(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 8)));
		lottoNumbersList.add(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 7)));
		lottoNumbersList.add(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 6)));

		lottoTickets.purchase(lottoNumbersList);

		result = lottoTickets.generateResult(answerLotto);
	}

	@Test
	@DisplayName("올바른 결과를 산출하는지 확인")
	void generateResult_results() {
		Map<ResultStatics, Integer> results = result.getResults();

		for (ResultStatics resultStatics : results.keySet()) {
			assertThat(results.get(resultStatics)).isEqualTo(1);
		}
	}

	@Test
	@DisplayName("올바른 수익률을 산출하는지 확인")
	void generateResult_profit() {
		int totalPrice = 0;
		for (ResultStatics resultStatics : ResultStatics.values()) {
			totalPrice += resultStatics.getPrice();
		}

		assertThat(result.getProfitRate()).isEqualTo((float) totalPrice / 6000);
	}

}
