package ae.cyberspeed.assignment.scratch.game.dto;

import ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus.*;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.standard.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Symbols {
    @JsonProperty("A")
    public A a;
    @JsonProperty("B")
    public B b;
    @JsonProperty("C")
    public C c;
    @JsonProperty("D")
    public D d;
    @JsonProperty("E")
    public E e;
    @JsonProperty("F")
    public F f;
    @JsonProperty("10x")
    public ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus._10x _10x;
    @JsonProperty("5x")
    public ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus._5x _5x;
    @JsonProperty("+1000")
    public ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus._1000 _1000;
    @JsonProperty("+500")
    public ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus._500 _500;
    @JsonProperty("miss")
    public MISS miss;

    Map<String, Double> symbolMultipierMap = new HashMap<>();
    Map<String, Double> symbolExtraMap = new HashMap<>();

    public Symbols() {
    }

    public Symbols(A a, B b, C c, D d, E e, F f, _10x _10x, _5x _5x, _1000 _1000, _500 _500, MISS miss) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this._10x = _10x;
        this._5x = _5x;
        this._1000 = _1000;
        this._500 = _500;
        this.miss = miss;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public F getF() {
        return f;
    }

    public void setF(F f) {
        this.f = f;
    }

    public _10x get_10x() {
        return _10x;
    }

    public void set_10x(_10x _10x) {
        this._10x = _10x;
    }

    public _5x get_5x() {
        return _5x;
    }

    public void set_5x(_5x _5x) {
        this._5x = _5x;
    }

    public _1000 get_1000() {
        return _1000;
    }

    public void set_1000(_1000 _1000) {
        this._1000 = _1000;
    }

    public _500 get_500() {
        return _500;
    }

    public void set_500(_500 _500) {
        this._500 = _500;
    }

    public MISS getMiss() {
        return miss;
    }

    public void setMiss(MISS miss) {
        this.miss = miss;
    }

    public Map<String, Double> getSymbolMultipierMap() {
        symbolMultipierMap.put("a", getA().getReward_multiplier());
        symbolMultipierMap.put("b", getB().getReward_multiplier());
        symbolMultipierMap.put("c", getC().getReward_multiplier());
        symbolMultipierMap.put("d", getD().getReward_multiplier());
        symbolMultipierMap.put("e", getE().getReward_multiplier());
        symbolMultipierMap.put("f", getF().getReward_multiplier());
        return symbolMultipierMap;
    }

    public Map<String, Double> getSymbolExtraMap() {
        symbolExtraMap.put("_10x", get_10x().getReward_multiplier());
        symbolExtraMap.put("10x", get_10x().getReward_multiplier());
        symbolExtraMap.put("_5x", get_5x().getReward_multiplier());
        symbolExtraMap.put("5x", get_5x().getReward_multiplier());
        symbolExtraMap.put("_1000", get_1000().extra);
        symbolExtraMap.put("+1000", get_1000().extra);
        symbolExtraMap.put("_500", get_500().extra);
        symbolExtraMap.put("+500", get_500().extra);
        symbolExtraMap.put("miss", 0D);

        return symbolExtraMap;
    }
}
