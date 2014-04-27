package fr.xebia.app.prettify;

import android.text.Html;
import fr.xebia.app.prettify.syntaxhighlight.ParseResult;
import fr.xebia.app.prettify.syntaxhighlight.Parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PrettifyHighlighter {
    private static final Map<String, String> COLORS = buildColorsMap();

    private static final String FONT_PATTERN = "<font color=\"#%s\">%s</font>";

    private static final Parser parser = new PrettifyParser();

    private PrettifyHighlighter() {
    }

    public static String highlight(String fileExtension, String sourceCode) {
        StringBuilder highlighted = new StringBuilder();
        List<ParseResult> results = parser.parse(fileExtension, sourceCode);
        String sourceCodeCpy = String.copyValueOf(sourceCode.toCharArray());
        for (ParseResult result : results) {
            String type = result.getStyleKeys().get(0);
            String content = sourceCode.substring(result.getOffset(), result.getOffset() + result.getLength());
            String newContent = String.format(FONT_PATTERN, getColor(type), Html.escapeHtml(content));

            newContent = newContent.replaceAll("&#13;&#10;", "<br>");
            newContent = newContent.replaceAll("<font color=\"#ffffff\"><br></font>", "<br>");

            highlighted.append(newContent);

        }

        return highlighted.toString();
    }

    private static String getColor(String type) {
        return COLORS.containsKey(type) ? COLORS.get(type) : COLORS.get("pln");
    }

    private static Map<String, String> buildColorsMap() {
        Map<String, String> map = new HashMap<>();
        map.put("typ", "87cefa");
        map.put("kwd", "00ff00");
        map.put("lit", "ffff00");
        map.put("com", "999999");
        map.put("str", "ff4500");
        map.put("pun", "eeeeee");
        map.put("pln", "ffffff");
        map.put("tag", "d68a4e");
        return map;
    }
}