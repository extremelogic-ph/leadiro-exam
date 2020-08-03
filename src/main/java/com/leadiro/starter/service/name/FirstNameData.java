package com.leadiro.starter.service.name;

import com.leadiro.starter.service.FetchDataService;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstNameData implements FetchDataService<String> {

    /**
     * Retrieve last name data.
     * @return List of last names
     */
    private List<String> fetchFirstNames() {
        List<String> names = null;
        try {
            // TODO Load this into a database
            File file = ResourceUtils.getFile("classpath:data/firstname.txt");
            try (Stream<String> stream = Files.lines(
                    Paths.get(file.getPath()))) {
                names = stream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
        return names;
    }

    @Override
    public List<String> fetchData() {
        return fetchFirstNames();
    }
}
