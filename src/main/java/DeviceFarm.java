import com.amazonaws.services.devicefarm.AWSDeviceFarm;
import com.amazonaws.services.devicefarm.AWSDeviceFarmClientBuilder;
import com.amazonaws.services.devicefarm.model.ListProjectsRequest;
import com.amazonaws.services.devicefarm.model.ListProjectsResult;

public class DeviceFarm {
    public static void main(String[] args){
        AWSDeviceFarm client = AWSDeviceFarmClientBuilder.standard().build();
        ListProjectsRequest request = new ListProjectsRequest();
        ListProjectsResult response = client.listProjects(request);
        System.out.println(response.toString());
    }
}
