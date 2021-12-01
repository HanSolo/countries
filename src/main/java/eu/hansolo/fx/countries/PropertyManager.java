/*
 * Copyright (c) 2021 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static eu.hansolo.fx.countries.tools.OperatingSystem.LINUX;
import static eu.hansolo.fx.countries.tools.OperatingSystem.MACOS;
import static eu.hansolo.fx.countries.tools.OperatingSystem.WINDOWS;


public enum PropertyManager {
    INSTANCE;

    public  static final String     VERSION_PROPERTIES   = "version.properties";
    public  static final String     FXMODULES_PROPERTIES = "fxmodules.properties";
    public  static final String     VERSION              = "version";
    public  static final String     OPERATING_SYSTEM     = "operating_system";
    private              Properties properties;
    private              Properties versionProperties;


    // ******************** Constructors **************************************
    PropertyManager() {
        properties = new Properties();
        // Load properties
        final String propertiesFilePath = new StringBuilder(System.getProperty("user.home")).append(File.separator).append(FXMODULES_PROPERTIES).toString();

        // Create properties file if not exists
        Path path = Paths.get(propertiesFilePath);
        if (!Files.exists(path)) { createProperties(properties); }

        // Load properties file
        try (FileInputStream propertiesFile = new FileInputStream(propertiesFilePath)) {
            properties.load(propertiesFile);
        } catch (IOException ex) {
            System.out.println("Error reading properties file. " + ex);
        }

        // If properties empty, fill with default values
        if (properties.isEmpty()) { createProperties(properties); }

        // Version number properties
        versionProperties = new Properties();
        try {
            versionProperties.load(DemoRegionPane.class.getResourceAsStream(VERSION_PROPERTIES));
        } catch (IOException ex) {
            System.out.println("Error reading version properties file. " + ex);
        }

    }


    // ******************** Methods *******************************************
    public Properties getProperties() { return properties; }

    public Object get(final String KEY) { return properties.getOrDefault(KEY, ""); }
    public void set(final String KEY, final String VALUE) {
        properties.setProperty(KEY, VALUE);
        try {
            properties.store(new FileOutputStream(String.join(File.separator, System.getProperty("user.dir"), FXMODULES_PROPERTIES)), null);
        } catch (IOException exception) {
            System.out.println("Error writing properties file: " + exception);
        }
    }

    public String getString(final String key) { return properties.getOrDefault(key, "").toString(); }

    public double getDouble(final String key) { return Double.parseDouble(properties.getOrDefault(key, "0").toString()); }

    public float getFloat(final String key) { return Float.parseFloat(properties.getOrDefault(key, "0").toString()); }

    public int getInt(final String key) { return Integer.parseInt(properties.getOrDefault(key, "0").toString()); }

    public long getLong(final String key) { return Long.parseLong(properties.getOrDefault(key, "0").toString()); }

    public boolean getBoolean(final String key) { return Boolean.parseBoolean(properties.getOrDefault(key, Boolean.FALSE).toString()); }

    public boolean hasKey(final String key) { return properties.containsKey(key); }

    public void storeProperties() {
        if (null == properties) { return; }
        final String propFilePath = new StringBuilder(System.getProperty("user.home")).append(File.separator).append(FXMODULES_PROPERTIES).toString();
        try (OutputStream output = new FileOutputStream(propFilePath)) {
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void resetProperties() {
        final String propFilePath = new StringBuilder(System.getProperty("user.home")).append(File.separator).append(FXMODULES_PROPERTIES).toString();
        try (OutputStream output = new FileOutputStream(propFilePath)) {
            final String operatingSystem;
            switch(Helper.getOperatingSystem()) {
                case MACOS   -> operatingSystem = MACOS.getApiString();
                case WINDOWS -> operatingSystem = WINDOWS.getApiString();
                case LINUX   -> operatingSystem = LINUX.getApiString();
                default      -> operatingSystem = "";
            }
            properties.put(OPERATING_SYSTEM, operatingSystem);
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getVersionNumber() {
        return versionProperties.getProperty(VERSION);
    }


    // ******************** Properties ****************************************
    private void createProperties(Properties properties) {
        final String propFilePath = new StringBuilder(System.getProperty("user.home")).append(File.separator).append(FXMODULES_PROPERTIES).toString();
        try (OutputStream output = new FileOutputStream(propFilePath)) {
            final String operatingSystem;
            switch(Helper.getOperatingSystem()) {
                case MACOS   -> operatingSystem = MACOS.getApiString();
                case WINDOWS -> operatingSystem = WINDOWS.getApiString();
                case LINUX   -> operatingSystem = LINUX.getApiString();
                default      -> operatingSystem = "";
            }
            properties.put(OPERATING_SYSTEM, operatingSystem);
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
