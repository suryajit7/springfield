package com.automation.framework.util.file;


import com.automation.framework.core.annotation.LazyService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@LazyService
public class PathFinder {

    @SneakyThrows
    public Path getFilePathForFile(String filename) {
        try (Stream<Path> stream = Files.walk(Paths.get("src"))) {
            log.info("Looking for filepath for given filename: ".concat(filename));
            return stream
                    .filter(file -> !Files.isDirectory(file) && file.getFileName().startsWith(filename))
                    .findFirst().get();
        }
    }



}
