package domain;

import static constant.LottoConstant.NUMBER_FOR_BONUS_CHECK;

public class LottoTicket {

	LottoNumbers numbers;

	public LottoTicket(LottoNumbers numbers) {
		this.numbers = numbers;
	}

	public ResultStatics calculate(AnswerLotto answerLotto) {
		int count = numbers.calculateDuplicatedCount(answerLotto.getNumbers());
		boolean bonus = false;

		if (count == NUMBER_FOR_BONUS_CHECK) {
			bonus = numbers.isExist(answerLotto.getBonusNumber());
		}

		return ResultStatics.of(count, bonus);
	}

	public LottoNumbers getNumbers() {
		return this.numbers;
	}
}
