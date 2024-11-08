package runners.BrowserStack;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;
import scripts_techniques.Config;
import scripts_textengine.TextEngine;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;
@ExtendWith(MyTestWatcher.class)
public class BstackRunner {
    public static WebDriver driver;
    public static String userName, accessKey;
    public static Map<String, Object> browserStackYamlMap;

    public BstackRunner() {
        File file = new File(Config.tunnel_path +"/browserstack.yml");
        this.browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
    }

   


    @AfterEach
    public void end() {
    	TextEngine.getSoftAssertions().assertAll();
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try {
            InputStream inputStream = Files.newInputStream(yamlFile.toPath());
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            map.putAll(config);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }
}
