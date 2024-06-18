package com.winter24.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс ConfigReader предоставляет статический метод для доступа к значениям из файла свойств app.properties.
 */
public class ConfigReader {

    private static Properties properties;

    // Приватный конструктор для реализации синглтона
    private ConfigReader() {
    }

    // Статический блок инициализации для загрузки файла свойств
    static {
        try {
            String path = "src/main/resources/app.properties";
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл app.properties не найден", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла app.properties", e);
        }
    }

    /**
     * Получить значение по ключу из файла app.properties.
     *
     * @param key Ключ для поиска значения.
     * @return Значение, соответствующее ключу.
     */
    public static String getValue(String key) {
        return properties.getProperty(key.trim());
    }

}