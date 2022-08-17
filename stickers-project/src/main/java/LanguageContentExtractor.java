import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LanguageContentExtractor implements ContentExtractor {

    public List<Content> extractContent(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> content = new ArrayList<>();

        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String image = attributes.get("image");
            String ranking = attributes.get("ranking");
            content.add(new Content(title, image, ranking));
        }
        return content;
    }
}
