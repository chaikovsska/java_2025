package task4;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TagAnalyzer {
    public Map<String, Integer> countTags(String urlString) throws IOException {
        Map<String, Integer> tagCounts = new HashMap<>();
        StringBuilder content = new StringBuilder();

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
        }

        Pattern pattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)[^>]*>");
        Matcher matcher = pattern.matcher(content.toString());

        while (matcher.find()) {
            String tagName = matcher.group(1).toLowerCase();
            tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);
        }

        return tagCounts;
    }
}