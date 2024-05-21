package pk.wieik.it.model;

import jakarta.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Tools {

    public static String readFile(String file, ServletContext context) throws IOException {
        StringBuilder output = new StringBuilder();
        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/view/" + file);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            while ((text = reader.readLine()) != null) {
                output.append(text).append("\n");
            }
        } else return null;

        return output.toString();
    }
    public static String getTemplate(String file, ServletContext context) throws IOException {
        String output = readFile(file, context);
        if (output == null)
            return "No file " + file;
        return output;
    }

    public static String fill(String template, String tag,
                              String file, ServletContext context) throws IOException {
        StringBuffer output = new StringBuffer("");
        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/view/" + file);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            while ((text = reader.readLine()) != null) {
                output.append(text).append("\n");
            }
        } else output.append("No " + file + " file");

        return template.replace("[[" + tag + "]]", output.toString());
    }

    public static int parseInteger(String input, int def) {
        int output = def;
        try {
            output = Integer.parseInt(input);
        } catch (NumberFormatException nfe) { // null or wrong format
            output = def;
        }
        return output;
    }

    public static String parsePage(String input, String valid)
    {
        String output = "main";
        String[] pages = valid.split(";");
        if (input==null) input="main";

        for (String proper: pages)
        {
            if (input.equals(proper)) {
                output = input;
                return output;
            }
        }
        return output;
    }

    public static String addScripts(String template, String page, ServletContext context) throws IOException {
        String scripts = readFile(page + "-scripts.html", context);
        String hooks = "";

        if (scripts == null) {
            scripts = "";
            hooks = "";
        } else {
            hooks = "hook()";
        }

        template = template.replace("[[SCRIPTS]]", scripts);
        template = template.replace("[[HOOKS]]", hooks);

        return template;
    }

}