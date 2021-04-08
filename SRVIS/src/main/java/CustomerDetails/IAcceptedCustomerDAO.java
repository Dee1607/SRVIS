package CustomerDetails;

import java.util.HashMap;
import java.util.Map;

public interface IAcceptedCustomerDAO {
    public Map<String,String> getConnection(String customerID);
}
