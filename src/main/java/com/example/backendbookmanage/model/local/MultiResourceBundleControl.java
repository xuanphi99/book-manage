package com.example.backendbookmanage.model.local;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MultiResourceBundleControl extends ResourceBundle.Control {

    private final String baseName;

    public MultiResourceBundleControl(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseName() {
        return this.baseName;
    }

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException {
        // The below is a copy of the default implementation.
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            // Only this line is changed to make it to read properties files as UTF-8.
            try (InputStreamReader inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                bundle = new PropertyResourceBundle(inputStreamReader);
            } finally {
                stream.close();
            }
        }
        return bundle;
    }
}
