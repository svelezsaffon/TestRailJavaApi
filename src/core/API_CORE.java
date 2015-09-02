package core;


import core.test_rail_case.Case_Helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import java.util.Map;


/**
 * Created by Santiago on 02/09/2015.
 */
public class API_CORE {


    private APIClient client;

    private boolean is_init;


    public API_CORE(){
        this.is_init=false;
    }

    public void init() throws Exception{

        this.client= new APIClient(TestRailConfig.TEST_RAIL_URL);
        this.client.setUser(TestRailConfig.TEST_RAIL_USERNAME);
        this.client.setPassword(TestRailConfig.TEST_RAIL_PASSWORD);

        this.is_init=true;
    }


    public JSONObject get_case(String test_case_id) throws Exception{
        if(this.is_init){

            StringBuilder request=new StringBuilder("get_case/"+test_case_id);

            return (JSONObject) this.client.sendGet(request.toString());

        }else{
            throw new Exception("The API requires to be initialized, use the .init() method");
        }
    }


    public JSONArray get_cases(Case_Helper caze) throws Exception{
        if(this.is_init){

            String get=caze.buildCase();

            return (JSONArray) this.client.sendGet(get);

        }else{
            throw new Exception("The API requires to be initialized, use the .init() method");
        }
    }


    private JSONObject send_post(Map data,Case_Helper caze) throws Exception{

        return (JSONObject) this.client.sendPost(caze.buildCase(),data);
    }



    public JSONObject add_result_for_case(Map data,Case_Helper caze) throws Exception {
        caze.type(2);
        return this.send_post(data,caze);
    }









}
