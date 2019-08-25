package di;

import di.beans.Data;
import di.beans.User;
import di.business.BusinessService;
import di.business.BusinessServiceImpl;
import di.data.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BusinessServiceMockitoTest {
    private static final User DUMMY_USER = new User("dummy");

    @Mock
    private DataService dataService;

    @InjectMocks
    private BusinessService service = new BusinessServiceImpl();

    @Test
    public void testCalculateSum() {
        BDDMockito.given(dataService.retrieveData(any(User.class)))
                .willReturn(Arrays.asList(new Data(10), new Data(15), new Data(25)));

        long sum = service.calculateSum(DUMMY_USER);
        assertEquals(10 + 15 + 25, sum);
    }
}