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

package eu.hansolo.fx.countries.tools;

import java.util.Arrays;
import java.util.List;


public enum OperatingSystem implements Api {
    LINUX("Linux", "linux"),
    MACOS("Mac OS", "macos"),
    WINDOWS("Windows", "windows"),
    SOLARIS("Solaris", "solaris"),
    NONE("-", ""),
    NOT_FOUND("", "");

    private final String uiString;
    private final String apiString;

    // ******************** Constructors **************************************
    OperatingSystem(String uiString, String apiString) {
        this.uiString  = uiString;
        this.apiString = apiString;
    }


    // ******************** Methods *******************************************
    @Override public String getUiString() {
        return this.uiString;
    }

    @Override public String getApiString() {  return this.apiString; }

    @Override public OperatingSystem getDefault() { return NONE; }

    @Override public OperatingSystem getNotFound() { return NOT_FOUND; }

    @Override public OperatingSystem[] getAll() { return values(); }

    public static OperatingSystem fromText(String text) {
        if (null == text) return NOT_FOUND;
        return switch (text) {
            case "-linux", "linux", "Linux", "LINUX" -> LINUX;
            case "-solaris", "solaris", "SOLARIS", "Solaris" -> SOLARIS;
            case "darwin", "-darwin", "-macosx", "-MACOSX", "MacOS", "Mac OS", "mac_os", "Mac_OS", "mac-os", "Mac-OS", "mac", "MAC", "macos", "MACOS", "osx", "OSX", "macosx", "MACOSX", "Mac OSX", "mac osx" -> MACOS;
            case "-win", "windows", "Windows", "WINDOWS", "win", "Win", "WIN"-> WINDOWS;
            default -> NOT_FOUND;
        };
    }

    public static List<OperatingSystem> getAsList() { return Arrays.asList(values()); }

    public String toString() { return this.uiString; }
}
