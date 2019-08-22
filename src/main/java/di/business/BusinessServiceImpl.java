package di.business;

import di.beans.Data;
import di.beans.User;
import di.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private DataService dataService;

    public long calculateSum(User user) {
        long sum = 0;
        for (Data data : dataService.retrieveData(user)) {
            sum += data.getValue();
        }
        return sum;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

}
