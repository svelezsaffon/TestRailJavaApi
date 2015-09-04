package core;


import core.test_rail_case.CaseCodes;
import core.test_rail_case.Case_Helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;

import java.util.ArrayList;
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

    public void connect() throws Exception{

        this.client= new APIClient(TestRailConfig.TEST_RAIL_URL);
        this.client.setUser(TestRailConfig.TEST_RAIL_USERNAME);
        this.client.setPassword(TestRailConfig.TEST_RAIL_PASSWORD);
        this.is_init=true;
    }

    public void set_url(String url){
        TestRailConfig.TEST_RAIL_URL=url;
    }

    public void set_password(String password){
        TestRailConfig.TEST_RAIL_PASSWORD=password;
    }

    public void set_username(String username){
        TestRailConfig.TEST_RAIL_USERNAME=username;
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

        if(caze.has_deffects()){
            StringBuilder deffects=new StringBuilder();
            ArrayList def=caze.get_deffects();
            for(int i=0;i<def.size();i++){
                if(i!=0){
                    deffects.append(",");
                }
                deffects.append(def.get(i));
            }

            data.put(CaseCodes.DEFFECTS,deffects.toString());
        }



        return this.send_post(data,caze);
    }

    public JSONObject get_run(int run_id) throws Exception{
        StringBuilder uri=new StringBuilder("get_run/");
        uri.append(run_id);
        return (JSONObject) this.client.sendGet(uri.toString());
    }

    public JSONArray get_tests_in_run(int run_id) throws Exception{
        StringBuilder uri=new StringBuilder("get_run/");
        uri.append(run_id);

        this.client.sendGet(uri.toString());

        uri.setLength(0);

        uri.append("get_tests/");
        uri.append(run_id);

        return (JSONArray) this.client.sendGet(uri.toString());
    }





}
