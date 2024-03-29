package ch02_dependency_injection.data;


import ch02_dependency_injection.beans.Data;
import ch02_dependency_injection.beans.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class DataServiceImpl implements DataService {
    public List<Data> retrieveData(User user) {
        return Arrays.asList(new Data(10), new Data(20));
    }
}
