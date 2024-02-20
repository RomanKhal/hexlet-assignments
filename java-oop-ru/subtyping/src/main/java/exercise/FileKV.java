package exercise;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static exercise.Utils.*;

// BEGIN
public class FileKV implements KeyValueStorage{
    private final String filePath;

    public FileKV(String filePath,  Map<String, String> map) {
        this.filePath = filePath;
        writeFile(filePath, serialize(map));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> unserialized = unserialize(readFile(filePath));
        unserialized.put(key, value);
        writeFile(filePath, serialize(unserialized));
    }

    @Override
    public void unset(String key) {
        Map<String, String> unserialized = unserialize(readFile(filePath));
        unserialized.remove(key);
        writeFile(filePath, serialize(unserialized));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> unserialized = unserialize(readFile(filePath));
        return unserialized.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return unserialize(readFile(filePath));
    }
}
// END
