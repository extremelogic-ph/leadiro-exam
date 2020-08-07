package com.leadiro.starter.service.name;

import com.leadiro.starter.service.FetchDataService;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FirstNameData implements FetchDataService<String> {

    /**
     * Retrieve last name data.
     * @return List of last names
     */
    private List<String> fetchFirstNames() {
        List<String> names = null;
        try {
            // TODO Load this into a database
            InputStream s = FirstNameData.class.getClassLoader()
                    .getResourceAsStream(
                            "data" + File.separator + "firstname.txt");
            String line;
            names = new ArrayList<>();

            BufferedReader br = new BufferedReader(new InputStreamReader(s));
            while ((line = br.readLine()) != null) {
                names.add(line);
            }
            br.close();
        } catch (Exception e) {
        }
        return names;
    }

    @Override
    public List<String> fetchData() {
        return fetchFirstNames();
    }
}
