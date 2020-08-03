package com.leadiro.starter.service;

import java.util.List;

public interface FetchDataService<T> {
    // TODO this exercise intentionally fetches from local, but
    //  in real situation it should be placed in the database.
    /**
     * Fetches the remote data.
     * @return Object representation of the retrieved data
     */
    List<T> fetchData();
}
