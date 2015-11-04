package core;




import core.test_rail.APIClient;
import core.test_rail_case.StaticCodes;
import core.test_rail_case.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.ArrayList;
import java.util.Map;


/**
 * This class provides the core functionalities of the API.
 */
public class API_CORE {


    private APIClient client;

    private boolean is_init;

    /**
     * Constructor for the core class of the API.
     */
    public API_CORE(){
        this.is_init=false;
    }


    /**
     * Initiates the API core engine with the current user credentials.
     * @throws Exception
     */
    public void connect() throws Exception{
        this.client= new APIClient(TestRailConfig.TEST_RAIL_URL);
        this.client.setUser(TestRailConfig.TEST_RAIL_USERNAME);
        this.client.setPassword(TestRailConfig.TEST_RAIL_PASSWORD);
        this.is_init=true;
    }


    /**
     * Sets the TestRail server URL.
     * @param url
     */
    public void set_url(String url){
        TestRailConfig.TEST_RAIL_URL=url;
    }

    /**
     * Sets the password credential for the TestRail server
     * @param password
     */
    public void set_password(String password){
        TestRailConfig.TEST_RAIL_PASSWORD=password;
    }

    /**
     * Sets the username credential for the TestRail server.
     * @param username
     */
    public void set_username(String username){
        TestRailConfig.TEST_RAIL_USERNAME=username;
    }


    /**
     * Obtains the information related to a specific test case.
     * @param test_case_id Test case Identifier in the TestRail Server
     * @return Test case information as a JSON object <br> <p>Example</p>
     * {
            "created_by": 5, <br>
            "created_on": 1392300984, <br>
            "custom_expected": "..", <br>
            "custom_preconds": "..", <br>
            "custom_steps": "..", <br>
            "custom_steps_separated": [<br>
                {<br>
                    "content": "Step 1",<br>
                    "expected": "Expected Result 1"<br>
                },<br>
                {<br>
                    "content": "Step 2",<br>
                    "expected": "Expected Result 2"<br>
                }<br>
            ],<br>
            "estimate": "1m 5s",<br>
            "estimate_forecast": null,<br>
            "id": 1,<br>
            "milestone_id": 7,<br>
            "priority_id": 2,<br>
            "refs": "RF-1, RF-2",<br>
            "section_id": 1,<br>
            "suite_id": 1,<br>
            "title": "Change document attributes (author, title, organization)",<br>
            "type_id": 4,<br>
            "updated_by": 1,<br>
            "updated_on": 1393586511<br>
        }<br>
     * @throws Exception when the requested test case does not exists
     */
    public JSONObject get_case(String test_case_id) throws Exception{
        if(this.is_init){

            StringBuilder request=new StringBuilder("get_case/"+test_case_id);

            return (JSONObject) this.client.sendGet(request.toString());

        }else{
            throw new Exception("The API requires to be initialized, use the .init() method");
        }
    }


    /**
     * Retrieves the
     * @param case_id
     * @return
     * @throws Exception
     */
    public JSONArray get_cases(TestCase case_id) throws Exception{
        if(this.is_init){

            String get=case_id.buildCase();

            return (JSONArray) this.client.sendGet(get);

        }else{
            throw new Exception("The API requires to be initialized, use the .connect() method");
        }
    }


    /**
     * Raw method to send the test case result information to the TestRail server
     * @param data Option key map regarding the information of the test case result
     * @param case_id
     * @see TestCase
     * @return
     * @throws Exception
     */
    private JSONObject send_post(Map data,TestCase case_id) throws Exception{

        return (JSONObject) this.client.sendPost(case_id.buildCase(),data);
    }


    /**
     * Sends the test case result information to the TestRail server
     * @param data Option key map regarding the information of the test case result
     * @param case_id  Auxiliary class to help build the Test case see
     * @see TestCase
     * @return JSONObject containing the result of sending the information to the TestRail server
     * @see <a href="http://www.json.org/javadoc/org/json/JSONObject.html">JSONObject</a>
     * @throws Exception
     */
    public JSONObject add_result_for_case(Map data,TestCase case_id) throws Exception {
        case_id.type(2);

        if(case_id.has_defects()){
            StringBuilder deffects=new StringBuilder();
            ArrayList def=case_id.get_deffects();
            for(int i=0;i<def.size();i++){
                if(i!=0){
                    deffects.append(",");
                }
                deffects.append(def.get(i));
            }

            data.put(StaticCodes.DEFFECTS,deffects.toString());
        }



        return this.send_post(data,case_id);
    }

    /**
     * Pulls the information related to a test run that is stored in TestRail server
     * @param run_id
     * @return The requested test run represented as a <a href="http://www.json.org/javadoc/org/json/JSONObject.html">JSONObject</a>
     * @throws Exception
     * @see <a href="http://www.json.org/javadoc/org/json/JSONObject.html">JSONObject</a>
     */
    public JSONObject get_test_run(int run_id) throws Exception{
        StringBuilder uri=new StringBuilder("get_run/");
        uri.append(run_id);
        return (JSONObject) this.client.sendGet(uri.toString());
    }


    /**
     * @param run_id Test run identifier
     * @return  all the test cases contained in the requested tst run, this is represented as a <a href="http://www.json.org/javadoc/org/json/JSONArray.html">JSONArray</a>
     * @throws Exception
     * @see <a href="http://www.json.org/javadoc/org/json/JSONArray.html">JSONArray</a>
     */
    public JSONArray get_tests_cases_in_run(int run_id) throws Exception{
        StringBuilder uri=new StringBuilder("get_run/");
        uri.append(run_id);

        this.client.sendGet(uri.toString());

        uri.setLength(0);

        uri.append("get_tests/");
        uri.append(run_id);

        return (JSONArray) this.client.sendGet(uri.toString());
    }





}
