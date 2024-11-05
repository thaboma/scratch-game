package ae.cyberspeed.assignment.scratch.game.transformer;

import ae.cyberspeed.assignment.scratch.game.dto.Symbols;
import ae.cyberspeed.assignment.scratch.game.dto.WinCombinations;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus.*;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.same.*;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.standard.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class ScratchGameTransformer {

    protected ScratchGameTransformer() {
    }

    public static Symbols mapSymbols(Symbols symbols, String clazz, String reward_multiplier, String type, String impact, String extra) {

        try {
            switch (clazz) {
                case "a":
                    A a = new A();
                    a.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    a.setType(type);
                    symbols.setA(a);
                    break;
                case "b":
                    B b = new B();
                    b.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    b.setType(type);
                    symbols.setB(b);
                    break;
                case "c":
                    C c = new C();
                    c.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    c.setType(type);
                    symbols.setC(c);
                    break;
                case "d":
                    D d = new D();
                    d.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    d.setType(type);
                    symbols.setD(d);
                    break;
                case "e":
                    E e = new E();
                    e.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    e.setType(type);
                    symbols.setE(e);

                    break;
                case "f":
                    F f = new F();
                    f.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    f.setType(type);
                    symbols.setF(f);
                    break;

                case "10x":
                    _10x _10x = new _10x();
                    _10x.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    _10x.setType(type);
                    _10x.setImpact(impact);
                    symbols.set_10x(_10x);
                    break;

                case "5x":
                    _5x _5x = new _5x();
                    _5x.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    _5x.setType(type);
                    _5x.setImpact(impact);
                    symbols.set_5x(_5x);
                    break;

                case "+1000":
                    _1000 _1000 = new _1000();
                    _1000.setExtra(tryParse(extra).doubleValue());
                    _1000.setType(type);
                    _1000.setImpact(impact);
                    symbols.set_1000(_1000);
                    break;

                case "+500":
                    _500 _500 = new _500();
                    _500.setExtra(tryParse(extra).doubleValue());
                    _500.setType(type);
                    _500.setImpact(impact);
                    symbols.set_500(_500);
                    break;

                case "miss":
                    MISS miss = new MISS();
                    miss.setType(type);
                    miss.setImpact(impact);
                    symbols.setMiss(miss);
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {

            throw new RuntimeException(e);
        }
        return symbols;
    }

    public static WinCombinations mapWinCombinations(WinCombinations winCombinations, String clazz, String reward_multiplier, String when, String count, String group) {
        return mapWinCombinations(winCombinations, clazz, reward_multiplier, when, count, group, null);
    }

    public static WinCombinations mapWinCombinations(WinCombinations winCombinations, String clazz, String reward_multiplier, String when, String count, String group, List<List<String>> covered_areas) {
        try {
            switch (clazz) {
                case "same_symbol_3_times":
                    SameSymbolNTimes sameSymbol3Times = new SameSymbol3Times();
                    sameSymbol3Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol3Times.setWhen(when);
                    sameSymbol3Times.setCount(tryParse(count).doubleValue());
                    sameSymbol3Times.setGroup(group);
                    winCombinations.setSame_symbol_3_times(sameSymbol3Times);
                    break;
                case "same_symbol_4_times":
                    SameSymbolNTimes sameSymbol4Times = new SameSymbol4Times();
                    sameSymbol4Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol4Times.setWhen(when);
                    sameSymbol4Times.setCount(tryParse(count).doubleValue());
                    sameSymbol4Times.setGroup(group);
                    winCombinations.setSame_symbol_4_times(sameSymbol4Times);
                    break;
                case "same_symbol_5_times":
                    SameSymbolNTimes sameSymbol5Times = new SameSymbol5Times();
                    sameSymbol5Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol5Times.setWhen(when);
                    sameSymbol5Times.setCount(tryParse(count).doubleValue());
                    sameSymbol5Times.setGroup(group);
                    winCombinations.setSame_symbol_5_times(sameSymbol5Times);
                    break;
                case "same_symbol_6_times":
                    SameSymbolNTimes sameSymbol6Times = new SameSymbol6Times();
                    sameSymbol6Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol6Times.setWhen(when);
                    sameSymbol6Times.setCount(tryParse(count).doubleValue());
                    sameSymbol6Times.setGroup(group);
                    winCombinations.setSame_symbol_6_times(sameSymbol6Times);
                    break;
                case "same_symbol_7_times":
                    SameSymbolNTimes sameSymbol7Times = new SameSymbol7Times();
                    sameSymbol7Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol7Times.setWhen(when);
                    sameSymbol7Times.setCount(tryParse(count).doubleValue());
                    sameSymbol7Times.setGroup(group);
                    winCombinations.setSame_symbol_7_times(sameSymbol7Times);
                    break;
                case "same_symbol_8_times":
                    SameSymbolNTimes sameSymbol8Times = new SameSymbol8Times();
                    sameSymbol8Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol8Times.setWhen(when);
                    sameSymbol8Times.setCount(tryParse(count).doubleValue());
                    sameSymbol8Times.setGroup(group);
                    winCombinations.setSame_symbol_8_times(sameSymbol8Times);
                    break;
                case "same_symbol_9_times":
                    SameSymbolNTimes sameSymbol9Times = new SameSymbol9Times();
                    sameSymbol9Times.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbol9Times.setWhen(when);
                    sameSymbol9Times.setCount(tryParse(count).doubleValue());
                    sameSymbol9Times.setGroup(group);
                    winCombinations.setSame_symbol_9_times(sameSymbol9Times);
                    break;
                case "same_symbols_horizontally":
                    SameSymbolOrientation sameSymbolsHorizontally = new SameSymbolsHorizontally();
                    sameSymbolsHorizontally.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbolsHorizontally.setWhen(when);
                    sameSymbolsHorizontally.setCovered_areas(covered_areas);
                    sameSymbolsHorizontally.setGroup(group);
                    winCombinations.setSame_symbols_horizontally(sameSymbolsHorizontally);
                    break;
                case "same_symbols_diagonally_left_to_right":
                    SameSymbolOrientation sameSymbolsDiagonallyLeftToRight = new SameSymbolsDiagonallyLeftToRight();
                    sameSymbolsDiagonallyLeftToRight.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbolsDiagonallyLeftToRight.setWhen(when);
                    sameSymbolsDiagonallyLeftToRight.setCovered_areas(covered_areas);
                    sameSymbolsDiagonallyLeftToRight.setGroup(group);
                    winCombinations.setSame_symbols_diagonally_left_to_right(sameSymbolsDiagonallyLeftToRight);
                    break;
                case "same_symbols_vertically":
                    SameSymbolOrientation sameSymbolsVertically = new SameSymbolsVertically();
                    sameSymbolsVertically.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbolsVertically.setWhen(when);
                    sameSymbolsVertically.setCovered_areas(covered_areas);
                    sameSymbolsVertically.setGroup(group);
                    winCombinations.setSame_symbols_vertically(sameSymbolsVertically);
                    break;
                case "same_symbols_diagonally_right_to_left":
                    SameSymbolOrientation sameSymbolsDiagonallyRightToLeft = new SameSymbolsDiagonallyRightToLeft();
                    sameSymbolsDiagonallyRightToLeft.setReward_multiplier(tryParse(reward_multiplier).doubleValue());
                    sameSymbolsDiagonallyRightToLeft.setWhen(when);
                    sameSymbolsDiagonallyRightToLeft.setCovered_areas(covered_areas);
                    sameSymbolsDiagonallyRightToLeft.setGroup(group);
                    winCombinations.setSame_symbols_diagonally_right_to_left(sameSymbolsDiagonallyRightToLeft);
                    break;

                default:
                    break;
            }
        } catch (Throwable e) {

            throw new RuntimeException(e);
        }
        return winCombinations;
    }

    private static Number tryParse(String input) {

        Number number = null;
        try {
            number = NumberFormat.getInstance().parse(input);
        } catch (ParseException e) {
            return 0D;
        }

        if (number == null || number.toString().length() == 0) {
            return 0D;
        }

        if (number instanceof Double) {
            return number.doubleValue();
        } else if (number instanceof Integer) {
            return number.intValue();
        }
        return number;
    }
}
