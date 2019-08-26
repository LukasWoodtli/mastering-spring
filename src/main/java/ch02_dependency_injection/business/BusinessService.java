package ch02_dependency_injection.business;

import ch02_dependency_injection.beans.User;

public interface BusinessService {
    long calculateSum(User user);
}
