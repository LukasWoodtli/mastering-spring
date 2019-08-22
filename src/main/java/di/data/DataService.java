package di.data;

import di.beans.Data;
import di.beans.User;

import java.util.List;

public interface DataService {
    List<Data> retrieveData(User user);
}
