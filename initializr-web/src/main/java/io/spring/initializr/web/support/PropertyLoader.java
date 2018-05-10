/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.initializr.web.support;

import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyLoader {

    private String filename;
    private Properties properties;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public PropertyLoader(@NonNull String filename) {
        this.filename = filename;
    }

    private void initializeProperties() {
        InputStream inStream = null;

        try {
            inStream = PropertyLoader.class.getResourceAsStream(this.filename);

            if (inStream != null) {
                this.properties = new Properties();
                this.properties.load(inStream);
            }
        } catch (IOException e) {
            log.warning(String.format("Failed to open file %s, will omit IOException.", this.filename));
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    log.warning(String.format("Unable to close file %s, will omit IOException.", this.filename));
                }
            }
        }
    }

    public String getPropertyValue(@NonNull String propertyName) {
        String value = "Unknown-Property";

        if (this.properties == null) {
            this.initializeProperties();
        }

        // initializeProperties may failure and leave this.properties still null.
        if (this.properties != null) {
            value = this.properties.getProperty(propertyName);
        }

        return value;
    }
}
