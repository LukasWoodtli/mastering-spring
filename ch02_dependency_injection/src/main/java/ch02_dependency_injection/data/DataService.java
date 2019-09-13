package ch02_dependency_injection.data;

import ch02_dependency_injection.beans.Data;
import ch02_dependency_injection.beans.User;

import java.util.List;

public interface DataService {
    List<Data> retrieveData(User user);
}
