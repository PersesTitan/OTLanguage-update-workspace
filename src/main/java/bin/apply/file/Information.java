package bin.apply.file;

import bin.apply.Setting;

import java.util.Arrays;
import java.util.Locale;

public interface Information {
    // version
    String VERSION = "4.0.0";
    // 확장자
    String[] extension = {".otl", ".otlanguage"};

    static boolean extCheck(String fileName) {
        fileName = fileName.toLowerCase(Locale.ROOT);
        for (String ext : extension) {
            if (fileName.endsWith(ext)) return true;
        }
        return false;
    }

    // ex) .otl => .otlm
    static boolean extensionCheck(String fileName, String option) {
        fileName = fileName.toLowerCase(Locale.ROOT);
        for (String ext : extension) {
            if (fileName.endsWith(ext.concat(option))) return true;
        }
        return false;
    }

    static String getExtension() {
        return String.join(", ", extension);
    }

    /**
     * PATH
     */
    String SEPARATOR_LINE = System.getProperty("line.separator"); // \n \r
    String SEPARATOR_FILE = System.getProperty("file.separator"); // /
    String SEPARATOR_HOME = System.getProperty("user.home");      // /User/name

    String MODULE_EXTENSION = ".otlm";
    String SYSTEM_EXTENSION = ".otls";
    String COMPULSION = "compulsion";   // 강제
    String ALTERATION = "alteration";   // 변경
    String OPERATE = "operate";         // 동작
    String MODULE = "module";

    // /User/name/.otl
    String INSTALL_PATH = Setting.developmentMode.get()
            ? getPath(SEPARATOR_HOME, ".otl")
            : System.getenv().getOrDefault("OTL_HOME", getPath(SEPARATOR_HOME, ".otl"));
    String MODULE_PATH = getPath(INSTALL_PATH, MODULE);           // /User/name/.otl/module

    // /User/name/.otl/module/system.otls
    String SYSTEM_PATH = getPath(MODULE_PATH, "system" + SYSTEM_EXTENSION);
    static String getPath(String...line) {
        return String.join(SEPARATOR_FILE, line);
    }
}
