package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    public static final String PROMPT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    public static final String PROMPT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public static int inputPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);
        int money = Integer.parseInt(Console.readLine());
        return money;
    }

    public static String inputWinningNumbers() {
        System.out.println();
        System.out.println(PROMPT_WINNING_NUMBERS);
        return Console.readLine();
    }

    public static String inputBonusNumber() {
        System.out.println();
        System.out.println(PROMPT_BONUS_NUMBER);
        return Console.readLine();
    }

}
