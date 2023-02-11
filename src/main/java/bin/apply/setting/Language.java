package bin.apply.setting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    KOREAN("ㄱ-ㅎㅏ-ㅣ가-힣"),
    ENGLISH("a-zA-Z"),
    JAPANESE("ぁ-ゔァ-ヴー々〆〤"),
    CHINESE("一-龥"),
    ARABIC("أ-ي"),
    HINDI("ऀ-ॿ"),
    THAI("กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮฯะัาำิีึืฺุู฿เแโใไๅๆ็่้๊๋์ํ๎๏๐๑๒๓๔๕๖๗๘๙๚๛"),
    ROMAN("À-ÖØ-öø-ʯᴀ-ᴥᵢ-ᵥᵫ-ᵷᵹ-ᶚḀ-ỿₐ-ₔↄ-ↄ⒈-⒐✝-✝Ⱡ-ⱼⱾ-ⱿꜢ-ꝯꝱ-ꞇꞋ-ꞌꟻ-ꟿﬀ-ﬆ"),
    NUMBER("0-9");

    private final String pattern;

    public static Language matchLanguage(String language) {
        for (Language lang : Language.values()) {
            if (language.matches("[".concat(lang.getPattern()).concat("]+"))) return lang;
        }
        return null;
    }

    // return [ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]
    public static String makePattern(Language...languages) {
        StringBuilder patternBuilder = new StringBuilder("[");
        for (Language language : languages) patternBuilder.append(language.getPattern());
        return patternBuilder.append(']').toString();
    }
}
