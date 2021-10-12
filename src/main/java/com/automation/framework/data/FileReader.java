package com.automation.framework.data;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class FileReader {

    public Path getFilePathForFile(String filename) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get("src"))) {
            log.info("Looking for filepath for given filename: ".concat(filename));
            return stream
                    .filter(file -> !Files.isDirectory(file) && file.getFileName().startsWith(filename))
                    .findFirst().get();
        }
    }

    public void readFile(String filename) throws IOException {

        Files.readAllLines(getFilePathForFile(filename))
                .forEach(System.out::println);

    }


}
