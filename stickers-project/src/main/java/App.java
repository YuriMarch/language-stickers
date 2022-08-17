import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static java.util.Objects.isNull;

public class App {
    public static void main(String[] args) throws Exception {
        clearOutputDirectory();
        String urlLanguageAPI = "http://localhost:8080/language";

        ClientHttp httpClient = new ClientHttp();
        String json = httpClient.searchData(urlLanguageAPI);

        ContentExtractor contentExtractor = new LanguageContentExtractor();
        List<Content> contents = contentExtractor.extractContent(json);

        StickerGenerator stickerGenerator = new StickerGenerator();
        for (int i = 0; i < contents.size(); i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.urlImage()).openStream();
            String imageFileName = content.title() + ".png";
            String stickerText = isNull(content.ranking()) ? "TOP" : content.ranking();

            stickerGenerator.create(inputStream, imageFileName, "#" + stickerText);

            System.out.println("Title " + (i + 1) + ": " + content.title());
            System.out.println("Image: " + content.urlImage());
        }
    }

    public static void clearOutputDirectory() throws IOException {
        File outputDirectory = new File("src/main/resources/output");
        FileUtils.cleanDirectory(outputDirectory);
    }
}
